/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathbuddyserver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.util.Pair;

/**
 *
 * @author Reese
 */
public class DBConnection {
    
    //the connection variable used in all methods below
    private Connection con;
    
    //the statement variable used in all prepared statements
    private Statement stmt;
    
    private PreparedStatement prestmt;
    
    //the latestresultset of the connection
    private ResultSet rs;
    
    //the string that is the host Name
    private String severHostName;
    
    //the port number the connection is in
    private int portNum;
    
    /**
     * makes a superadminConnection object
     * @param hostName the name of the server you are connecting to
     * @param portNum the port number the connection is on
     */
    public DBConnection(String hostName, int portNum){
        this.con = null;
        this.stmt = null;
        this.prestmt = null;
        this.rs=null;
        this.severHostName = hostName;
        this.portNum = portNum;
    }
    
    /**
     * addes a user with the following creditentals to the user table
     * @param userName
     * @param email
     * @param userType 
     */
    public void addUser(String userName, String email, int userType) throws SQLException{
        this.initConnection();
        String command = "INSERT INTO mathbuddydb.users (userName, userEmail, UserType) "
                            + "VALUES (?,?,?);";
        this.prestmt = this.con.prepareStatement(command);
        this.prestmt.setString(1, userName);
        this.prestmt.setString(2, email);
        this.prestmt.setInt(3, userType);
        this.prestmt.executeUpdate();
        this.closeConnection();
    }
    
    /**
     * deletes all records relating to the user in the DB
     * either UserID or email can be null if not known
     * make userID 0 to make null
     * make email null
     * userType must be known
     * @param userID //UserID of person to be deleted
     * @param email 
     * email of person to be deleted
     * @param userType
     * @throws SQLException 
     */
    public void deleteUser(int userID, String email, int userType) throws SQLException{
        if(userID != 0){
            this.initConnection();
            String command = "DELETE FROM mathbuddydb.users WHERE userID=?;";
            this.prestmt = this.con.prepareStatement(command);
            this.prestmt.setInt(1, userID);
            this.prestmt.executeUpdate();
            this.closeConnection();
            if(userType==1){
                this.initConnection();
                command = "DELETE FROM mathbuddydb.activities WHERE userID=?;";
                this.prestmt = this.con.prepareStatement(command);
                this.prestmt.setInt(1, userID);
                this.prestmt.executeUpdate();
                this.closeConnection();
            }
            else if(userType==2){
                ArrayList<Integer> classes = this.getTeachersClasses(userID);
                while(!classes.isEmpty()){
                    this.deleteClassroom(classes.remove(0));
                }
            }
            //right here add the SGL stuff
        }
        else if(email != null){
            int id = this.getUserIDFromEmail(email);
            if(id==0)
                return; //means the email was not found
            this.deleteUser(id, email, userType);
        }
        else
            return; ////means both values null
    }
    
    
    
    /**
     * creates the class with the specified teacher as the makes
     * make want to make another feild like name will do in v2.0
     * returns the classID of the new class if it returns -1 then class was not made
     * @param teacher
     * @throws SQLException 
     */
    public int createClassroom(int teacherID, String classroomName)throws SQLException{ 
        this.initConnection();
        int out = -1;
        String command = "INSERT INTO mathbuddydb.classrooms (teacherID, classroomName)" 
                            + "VALUES (?, ?);";
        this.prestmt = this.con.prepareStatement(command);
        this.prestmt.setInt(1, teacherID);
        this.prestmt.setString(2, classroomName);
        this.prestmt.executeUpdate();
        this.closeConnection();
        this.initConnection();
        command = "SELECT MAX(classroomID) FROM mathbuddydb.classrooms " 
                            + "WHERE TeacherID=? AND classroomName=?;";
        this.prestmt = this.con.prepareStatement(command);
        this.prestmt.setInt(1, teacherID);
        this.prestmt.setString(2, classroomName);
        rs=prestmt.executeQuery();
        while(rs.next()){
            out =rs.getInt(1);
        }
        this.closeConnection();
        return out;
    }
    
    
    
    public void deleteClassroom(int classroomID) throws SQLException{
        //delete the class activities first
        this.initConnection();
        String command = "DELETE FROM mathbuddydb.activities WHERE classroomID=?;";
        this.prestmt = this.con.prepareStatement(command);
        this.prestmt.setInt(1, classroomID);
        this.prestmt.executeUpdate();
        this.closeConnection();
        //now delete from the classroom table
        this.initConnection();
        command = "DELETE FROM mathbuddydb.classrooms WHERE classroomID=?;";
        this.prestmt = this.con.prepareStatement(command);
        this.prestmt.setInt(1, classroomID);
        this.prestmt.executeUpdate();
        this.closeConnection();
    }
    
    public void addStudentToClass(int classID, String email) throws SQLException{
        int studentID = this.getUserIDFromEmail(email);
        this.initConnection();
        String command = "INSERT INTO mathbuddydb.activities "
                + "(classroomID, userID, activityType) "
                + "VALUES (?,?,?);";
        this.prestmt = this.con.prepareStatement(command);
        this.prestmt.setInt(1, classID);
        this.prestmt.setInt(2, studentID);
        this.prestmt.setInt(3, 1); //activity of 1 the activity of being in the class
        this.prestmt.executeUpdate();
        this.closeConnection();
        //want to return now the 
    }
    
    public void removeStudentFromClass(int classroomID, String studentEmail) throws SQLException{
        int studentID = this.getUserIDFromEmail(studentEmail);
        ArrayList<Integer> studActs;
        studActs = this.getStudentsActivities(studentID, classroomID);
        while(!studActs.isEmpty()){
            this.deleteSpecificActivity(studActs.remove(0));
        }
    }
    
    
    //gives all students in the class currently a quiz
    public void createActivity(int classroomID, int activityTypeID, int numProblems, int seed) throws SQLException{//may need a bigger value for seed
        ArrayList<Integer> students = this.getStudentsInClass(classroomID);
        while(!students.isEmpty()){
            this.initConnection();
            String command = "INSERT INTO mathbuddydb.activities (classroomID, userID, activityType, numProblems, seed)"
                    + "VALUES (?, ?, ?, ?, ?);";
            this.prestmt = this.con.prepareStatement(command);
            this.prestmt.setInt(1, classroomID);
            this.prestmt.setInt(2, students.remove(0));
            this.prestmt.setInt(3, activityTypeID);
            this.prestmt.setInt(4, numProblems);
            this.prestmt.setInt(5, seed);
            this.prestmt.executeUpdate();
            this.closeConnection();
        }
    }
    
    public void deleteSpecificActivity(int activityID) throws SQLException{
        this.initConnection();
        String command = "DELETE FROM mathbuddydb.activities WHERE activityID=?;";
        this.prestmt = this.con.prepareStatement(command);
        this.prestmt.setInt(1, activityID);
        this.prestmt.executeUpdate();
        this.closeConnection();
    }
        
    public void recordActivityScore(int activityID, int score) throws SQLException{
        this.initConnection();
        String command = "UPDATE mathbuddydb.activities"
                + " SET score=? WHERE activityID=?;";
        this.prestmt = this.con.prepareStatement(command);
        this.prestmt.setInt(1, score);
        this.prestmt.setInt(2, activityID);
        this.prestmt.executeUpdate();
        this.closeConnection();
    }
    
    //returns an arrayList of the userID of all students in the database
    public ArrayList<Integer> getStudentsInClass(int classID) throws SQLException{
        ArrayList<Integer> out = new ArrayList();
        this.initConnection();
        String command = "SELECT userID FROM mathbuddydb.activities "
                + "WHERE classroomID=? AND activityType=?;";
        this.prestmt = this.con.prepareStatement(command);
        this.prestmt.setInt(1, classID);
        this.prestmt.setInt(2, 1);
        rs=prestmt.executeQuery();
        while(rs.next()){
            out.add(rs.getInt(1));
        }
        this.closeConnection();
        return out;
    }
    
    //returns an arraylist of the classID's that the student is in
    public ArrayList<Integer> getStudentsClasses(int studentID) throws SQLException{
        ArrayList<Integer> out = new ArrayList();
        this.initConnection();
        String command = "SELECT classroomID FROM mathbuddydb.activities " +
                "WHERE userID=? AND activityType=?;";
        this.prestmt = this.con.prepareStatement(command);
        this.prestmt.setInt(1, studentID);
        this.prestmt.setInt(2, 1);
        rs=prestmt.executeQuery();
        while(rs.next()){
            out.add(rs.getInt(1));
        }
        this.closeConnection();
        return out;
    }
    
    
    //returns a pair where they key is the className and the value is the teachers Name
    public ArrayList<Pair<String, String>> getClassAndTeacherNames(ArrayList<Integer> classroomIDs) throws SQLException{
        ArrayList<Pair<String, String>> out = new ArrayList();
        ArrayList<String> classNameholder = new ArrayList();
        ArrayList<Integer> teacherIDholder = new ArrayList();
        for(int i = 0; i < classroomIDs.size(); i++){
            this.initConnection();
            String command = "SELECT classroomName, TeacherID FROM mathbuddydb.classrooms " +
                "WHERE classroomID=?;";
            this.prestmt = this.con.prepareStatement(command);
            this.prestmt.setInt(1, classroomIDs.get(i));
            rs=prestmt.executeQuery();
            while(rs.next()){
                classNameholder.add(rs.getString(1));
                teacherIDholder.add(rs.getInt(2));
                
            }
            this.closeConnection();
        }
        while(!classNameholder.isEmpty()){
           Pair<String, String> pair = new Pair(classNameholder.remove(0), this.getUserNameFromID(teacherIDholder.remove(0))); 
           out.add(pair);
        }
        return out;
    }
    
    
    /**
     * returns a very long array List that will be numofActivities*5 of length
     * a -1 score value means the score was null meaning that the quiz was not taken
     * itll be 
     * @param studentID
     * @param classroomID
     * @return
     * @throws SQLException 
     */
    public ArrayList<Integer> getStudentsActivities(int studentID, int classroomID) throws SQLException{
        ArrayList<Integer> out = new ArrayList();
        this.initConnection();
        String command = "SELECT activityID, activityType, score, seed, numProblems FROM mathbuddydb.activities " +
                "WHERE classroomID=? AND userID=?;";
        this.prestmt = this.con.prepareStatement(command);
        this.prestmt.setInt(1, classroomID);
        this.prestmt.setInt(2, studentID);
        rs=prestmt.executeQuery();
        while(rs.next()){
            out.add(rs.getInt(1));
            out.add(rs.getInt(2));
            int holder = rs.getInt(3);
            if(rs.wasNull()){
                out.add(-1);
            }
            else{
                out.add(holder);
            }
            out.add(rs.getInt(4));
            out.add(rs.getInt(5));   
        }
        this.closeConnection();
        return out;
    }
    
    
    
    public ArrayList<Integer> getTeachersClasses(int teacherID) throws SQLException{
        ArrayList<Integer> out = new ArrayList();
        this.initConnection();
        String command = "SELECT classroomID FROM mathbuddydb.classrooms " +
                "WHERE teacherID=?;";
        this.prestmt = this.con.prepareStatement(command);
        this.prestmt.setInt(1, teacherID);
        rs=prestmt.executeQuery();
        while(rs.next()){
            out.add(rs.getInt(1));
        }
        this.closeConnection();
        return out;
    }
    
    
    
////////////////////////////////////////////////////////////////////////////////
///                 methods that were made with the server in mind           ///
////////////////////////////////////////////////////////////////////////////////


    
public int getUserIDFromEmail(String email) throws SQLException{
        int out = 0;
        this.initConnection();
        String command = "SELECT userID FROM mathbuddydb.users " +
                "WHERE userEmail=?;";
        this.prestmt = this.con.prepareStatement(command);
        this.prestmt.setString(1, email);
        rs=prestmt.executeQuery();
        while(rs.next()){
            out=rs.getInt(1);
        }
        this.closeConnection();
        return out;
    }


public int getUserType(int userID) throws SQLException{
    int out = 0;
    this.initConnection();
    String command = "SELECT userType FROM mathbuddydb.users " +
            "WHERE userID=?;";
    this.prestmt = this.con.prepareStatement(command);
    this.prestmt.setInt(1, userID);
    rs=prestmt.executeQuery();
    while(rs.next()){
        out=rs.getInt(1);
    }
    this.closeConnection();
    return out;
}

public String getUserNameFromID(int userID) throws SQLException{
        String out = "";
        this.initConnection();
        String command = "SELECT userName FROM mathbuddydb.users " +
                "WHERE userID=?;";
        this.prestmt = this.con.prepareStatement(command);
        this.prestmt.setInt(1, userID);
        rs=prestmt.executeQuery();
        while(rs.next()){
            out=rs.getString(1);
        }
        this.closeConnection();
        return out;
    }

public String getClassroomNameFromClassroomID(int classroomID) throws SQLException{
    String out = "";
        this.initConnection();
        String command = "SELECT classroomName FROM mathbuddydb.classrooms " +
                "WHERE classroomID=?;";
        this.prestmt = this.con.prepareStatement(command);
        this.prestmt.setInt(1, classroomID);
        rs=prestmt.executeQuery();
        while(rs.next()){
            out=rs.getString(1);
        }
        this.closeConnection();
        return out;
}

public int getActivitySeed(int activityID) throws SQLException{
    int out = 0;
    this.initConnection();
    String command = "SELECT seed FROM mathbuddydb.activities " +
                "WHERE activityID=?;";
    this.prestmt = this.con.prepareStatement(command);
        this.prestmt.setInt(1, activityID);
        rs=prestmt.executeQuery();
    while(rs.next()){
            out=rs.getInt(1);
        }
    this.closeConnection();
    return out;
}

//need a get class activity method for students and teachers
//need to know the score and if they attempted them




    

////////////////////////////////////////////////////////////////////////////////
///                 TEST METHODS                                             ///
/// FOLLOWING METHODS ARE FOR PURELY TESTING PURPOSES                        ///
/// you are just supsoed to call the demo methods that carry out the private ///
///                 methods                                                  ///
////////////////////////////////////////////////////////////////////////////////
    



private void clearTables() throws SQLException{
    this.initConnection();
    String command = "DELETE FROM mathbuddydb.activities;";
    this.prestmt = this.con.prepareStatement(command);
    this.prestmt.executeUpdate();
    this.closeConnection();
    System.out.println("cleared activites");
    this.initConnection();
    command = "DELETE FROM mathbuddydb.classrooms;";
    this.prestmt = this.con.prepareStatement(command);
    this.prestmt.executeUpdate();
    this.closeConnection();
    System.out.println("cleared classrooms");
    this.initConnection();
    command = "DELETE FROM mathbuddydb.users;";
    this.prestmt = this.con.prepareStatement(command);
    this.prestmt.executeUpdate();
    this.closeConnection();
    System.out.println("cleared users");
    
    
    
}


private void printTables() throws SQLException{
    this.initConnection();
    String command = "SELECT * FROM mathbuddydb.users;";
    this.prestmt = this.con.prepareStatement(command);
    rs=prestmt.executeQuery();
    System.out.println("PRINTING THE USERTABLE");
    while(rs.next()){
        System.out.printf("%d,\t%s,\t%s\t,%d\n", rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
    }
    this.closeConnection();
    System.out.println("PRINTING THE CLASSROOM TABLE");
    this.initConnection();
    command = "SELECT * FROM mathbuddydb.classrooms;";
    this.prestmt = this.con.prepareStatement(command);
    rs=prestmt.executeQuery();
    while(rs.next()){
        System.out.printf("%d,\t%s,\t%d\n", rs.getInt(1), rs.getString(2), rs.getInt(3));
    }
    this.closeConnection();
    System.out.println("PRINTING THE ACTIVITIES TABLE;");
    this.initConnection();
    command = "SELECT * FROM mathbuddydb.activities;";
    this.prestmt = this.con.prepareStatement(command);
    rs=prestmt.executeQuery();
    while(rs.next()){
        System.out.printf("%d,\t%d,\t%d\t,%d\t%d,\t%d,\t%d,\n", rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getInt(7));
    }
    this.closeConnection();
}


private void openEmptyConnection(){
    System.out.println("About to open a blank connection");
    this.initConnection();
    System.out.println("OPENED THE CONNECTION");
    this.closeConnection();
    System.out.println("CLOSED THE CONNECTION");
}

private void fillTheTableSome() throws SQLException{
    System.out.println("about to make 3 teachers");
    this.addUser("Teacher1", "email1@teacher.com", 2);
    System.out.println("Made 1 teacher");
    this.addUser("Teacher2", "email2@teacher.com", 2);
    System.out.println("Made 2 teacher");
    this.addUser("Teacher3", "email3@teacher.com", 2);
    System.out.println("Made 3 teacher");
    System.out.println("ABOUT to make 3 students");
    this.addUser("student1", "email1@student.com", 1);
    this.addUser("student2", "email2@student.com", 1);
    this.addUser("student3", "email3@student.com", 1);
    System.out.println("students made");
}

//must be done on a blank table after fill in table some was called
private void createClassrooms() throws SQLException{
    int teacherNum = this.getUserIDFromEmail("email1@teacher.com");
    System.out.println("Teacher1 about to make a class");
    this.createClassroom(teacherNum, "C1T1");
    System.out.println("Teacher1 finished making classes");
    teacherNum = this.getUserIDFromEmail("email2@teacher.com");
    System.out.println("teacher2 about to make 2 clases");
    this.createClassroom(teacherNum, "C1T2");
    this.createClassroom(teacherNum, "C2T2");
    System.out.println("teacher2 finished making classes");
    teacherNum = this.getUserIDFromEmail("email3@teacher.com");
    System.out.println("Teacher3 about to make 3 classes");
    this.createClassroom(teacherNum, "C1T3");
    this.createClassroom(teacherNum, "C2T3");
    this.createClassroom(teacherNum, "C3T3");
    System.out.println("teacher3 finished making classes");
}





    ////////////////////////////////////////////////////////////////////////////
    ///                    HELPER METHODS                                    ///
    /// METHODS THAT ARE PRIVATE AND ONLY OTHER METHODS USE                  ///
    ////////////////////////////////////////////////////////////////////////////
    
    //gets the userID from an email
    //if it returns 0 that means email cannot be found
    
    
    private int getClassroomIDFromName(String classroomName) throws SQLException{
        int out = 0;
        this.initConnection();
        String command = "SELECT classroomID FROM mathbuddydb.classrooms " +
                "WHERE classroomName=?;";
        this.prestmt = this.con.prepareStatement(command);
        this.prestmt.setString(1, classroomName);
        rs=prestmt.executeQuery();
        while(rs.next()){
            out=rs.getInt(1);
        }
        this.closeConnection();
        return out;
    }
    
    
    
    
    
    
    /**
     * Just makes the connection URL from the objects variables
     * if Database name changes change it here
     * Make need better way of keeping track of DB name than hard coding it
     * @return 
     */
    private String makeURL(){
        return "jdbc:mysql://"+severHostName+":"+portNum+"/mathbuddydb?useSSL=false&allowPublicKeyRetrieval=true";
    }
    
    /**
     * initalizes the con variable must be done before interaction with the DB 
     * ALWAYS CLOSE THE CONNECTION AFTER OPENING IT
     * if your admin username and pass is different please change
     * NetBeans is the userName and beans is the password
     * these are just for testing clearly
     * 
     */
    private void initConnection(){
        try{
                con = DriverManager.getConnection(this.makeURL(), "MathBuddyAdmin", "H2Q4rt17"); //"netBeans", "beans"
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Just makes the connection Null do after all initConnections
     * goes through all the global variables make sure results are taken care of
     */
    private void closeConnection(){
        if (rs != null) try { rs.close(); } catch(Exception e) {}
        if (prestmt != null) try { prestmt.close(); } catch(Exception e) {}
        if (stmt != null) try { stmt.close(); } catch(Exception e) {}
        if (con != null) try { con.close(); } catch(Exception e) {}
    }
    
}
