package com.company;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by TJ on 10/22/15.
 *
 */
public class Course {
    private String courseName;
    private String courseNumber;
    private List<String> preReqs;

    public String getCourseName(){
        return courseName;
    }

    public void setCourseName(String courseName){
        this.courseName = courseName;
    }

    public String getCourseNumber(){
        return this.courseNumber;
    }

    public void setCourseNumber(String courseNumber){
        this.courseNumber = courseNumber;
    }

    public List<String> getPreReqs(){
        return preReqs;
    }

    public void setPreReqs(List<String> preReqs){
        this.preReqs = preReqs;
    }


    public Course(String name, String num, List<String> preReq)
    {
        this.courseName = name;
        this.courseNumber = num;
        this.preReqs = preReq;
    }

}
