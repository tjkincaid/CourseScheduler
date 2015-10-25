package com.company;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TJ on 10/22/15.
 */
public class Course implements Comparable<Course>{
    private String courseName;
    private List<String> preReqs;

    public String getCourseName(){
        return courseName;
    }

    public void setCourseName(String courseName){
        this.courseName = courseName;
    }

    public List<String> getPreReqs(){
        return preReqs;
    }

    public void setPreReqs(List<String> preReqs){
        this.preReqs = preReqs;
    }


    public Course(String name, List<String> preReq)
    {
        this.courseName = name;
        this.preReqs = preReq;
    }


    public int compareTo(Course n) {
        int lastCmp = courseName.compareTo(n.courseName);
        return lastCmp;
    }
}
