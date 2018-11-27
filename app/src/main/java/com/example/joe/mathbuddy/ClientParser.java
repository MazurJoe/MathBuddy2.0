
package com.example.joe.mathbuddy;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Reese
 */










/**
/*a class full of static methods to be called to turn the string
/*the client methods return into data structs 
/*userdata, classdata, and activityData
/*T0 refers to requestType0 methods and so on
**/
public class ClientParser {
    
    static public UserData parseT0(String in){
        UserData out = new UserData();
        Scanner s = new Scanner(in);
        String type = s.next();
        if(type.equals("new")){
            out.userID = -1;
            return out;
        }
        out.userType = Integer.parseInt(type);
        out.userName = s.next();
        ArrayList<ClassData> classList = new ArrayList(); //the class list for the userData structure
        if(out.userType == 1){ //returning data for a student
            while(s.hasNext()){
                ClassData temp = new ClassData();
                temp.classID = s.nextInt();
                temp.className = s.next();
                temp.teacherName = s.next();
                classList.add(temp);
            }
        }
        else{ //a teacher user
            while(s.hasNext()){
                ClassData temp = new ClassData();
                temp.classID = s.nextInt();
                temp.className = s.next();
                int numStudents = s.nextInt();
                ArrayList<UserData> studentList = new ArrayList();
                for(int i = 0; i < numStudents; i++){
                    UserData tempStu = new UserData();
                    tempStu.userID = s.nextInt();
                    tempStu.userName = s.next();
                    studentList.add(tempStu);
                }
                temp.students = studentList;
                classList.add(temp);
            }
        }
        out.classes = classList;
        return out;
    }

    public static int parseT1(String in){
        Scanner s = new Scanner(in);
        return s.nextInt();
    }

    public static int parseT2(String in){
        Scanner s = new Scanner(in);
        int out = -1;
        if(s.hasNext()){
            out = s.nextInt();
        }
        return out;
    }


    public static ArrayList<ActivityData> parseT3(String in){
        Scanner s = new Scanner(in);
        ArrayList<ActivityData> out = new ArrayList();
        while(s.hasNext()){
            ActivityData temp = new ActivityData();
            temp.ActID = s.nextInt();
            temp.ActType = s.nextInt();
            temp.score = s.nextInt();
            temp.seed = s.nextInt();
            temp.numProbs = s.nextInt();
            out.add(temp);
        }
        return out;
    }

    public static UserData parseT4(String in){
        Scanner s = new Scanner(in);
        UserData out = new UserData();
        String firstLine = s.next();
        if(!firstLine.equals("d")){
            //Means the request was a request to add, if the above was true
            // then a null userData will be returned just
            out.userID = Integer.parseInt(firstLine);
            out.userName = s.next();
        }
        return out;
    }
    /**
    public static ArrayList<userData> parseT4(String in){
        Scanner s = new Scanner(in);
        ArrayList<userData> out = new ArrayList();
        while(s.hasNext()){
            
        }
    }
    **/
    
    
    
}
