package com.company;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TJ on 10/22/15.
 */
public class Course implements Comparable<Course>{
    public String courseName;
    public List<String> preReqs;

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
