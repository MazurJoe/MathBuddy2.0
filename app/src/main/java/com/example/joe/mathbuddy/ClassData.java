/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.joe.mathbuddy;

import java.util.ArrayList;

/**
 *data Struct used to easily store data from database
 * @author Reese
 */
public class ClassData {
    public int classID;
    public int teacherID;
    public String teacherName;
    public String className;
    public ArrayList<UserData> students;
}
