package com.company;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TJ on 10/22/15.
 */
public class Course {
    private String courseName;
    private List<String> preReqs;

    public Course(String name, List<String> preReq)
    {
        this.courseName = name;
        this.preReqs = preReq;
    }
}
