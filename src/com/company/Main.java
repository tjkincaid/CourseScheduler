package com.company;
import com.sun.source.tree.WhileLoopTree;
import com.sun.tools.javac.comp.Annotate;
import sun.tools.tree.WhileStatement;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException{
        // write your code here


        /*
        String filePath = "./data/testfile1.txt";
        String courses = fileToList(filePath);
        scheduleCourses(courses);
         */

        // test string
        String[] allCourses={
                "CSE258: CSE244 CSE243 INTR100",
                "CSE221: CSE254 INTR100",
                "CSE254: CSE111 MATH210 INTR100",
                "CSE244: CSE243 MATH210 INTR100",
                "MATH210: INTR100",
                "CSE101: INTR100",
                "CSE111: INTR100",
                "ECE201: CSE111 INTR100",
                "ECE111: INTR100",
                "CSE243: CSE254",
                "INTR100:"
        };

/*        The method should return:
        {"INTR100","CSE101","CSE111","ECE111","ECE201","MATH210","CSE254","CSE221","CSE2
            43","CSE244","CSE258"}*/

        //load a new list with individual courses and their prereqs
        String[] WorkList = scheduleCourses(allCourses);
        System.out.println("Back from scheduledCourses");
        System.out.println(Arrays.toString(WorkList));

    }

    public static String[] scheduleCourses(String[] courseList){
        System.out.println("Your in Scheduled Courses");
        //System.out.println(Arrays.toString(courseList));
        // process parent course and pre reqs to add to a list

        List<Course> CourseCatalog = new ArrayList<>();
        for (int i = 0; i < courseList.length; i++) {
            //for (String i: courseList) {
            List<String> PreReq = new ArrayList<>();


            // isolate the Parent course to a string
            String[] courseAndPreReq = courseList[i].split(":");
            String ParentCourse = courseAndPreReq[0];
            ParentCourse.replace(":", "");
            System.out.println("Your Parent Course is " + ParentCourse);

            //split prereqs by " "
            String[] PreReqSplit;
            if (courseAndPreReq.length > 1) {
                PreReqSplit = courseAndPreReq[1].split(" ");
                System.out.println(PreReqSplit);
            }else {PreReqSplit =courseAndPreReq[0].split(" ");}


            // run through the rest of the children and add them to a new list
            for (int j = 1; j < PreReqSplit.length; j++) {
                PreReq.add(PreReqSplit[j]);
            }
            System.out.println("Your Child Courses are " + PreReq);
            CourseCatalog.add(new Course(ParentCourse, PreReq));

        }
//        System.out.println("Here is the Catalog!");
//        System.out.println(CourseCatalog);
        courseList = makeOutputList(CourseCatalog);

        return courseList;
    }

    public static String[] makeOutputList(List<Course> CourseCatalog){
        String[] CourseList;
        List<Course> WorkCatalog ;
        WorkCatalog = CourseCatalog;

//        Sort the WorkCatalog by CourseName, Do we really even need to do this?

//        Pop the first CourseName without a PreReq, Add to list and remove from class
        int noReqCount = 0;

        // order this by COURSE so that the FIRST course without a PREREQ is the one to use

        Collections.sort(WorkCatalog);


        for (int i = 0; i < WorkCatalog.size(); i++) {
            System.out.println(i + " COURSE = " + ( WorkCatalog.get(i)).courseName);
            System.out.println(i + " PREREQS = " + ( WorkCatalog.get(i)).preReqs.toString());

        }


        // Loop and pop the first empty repreq

            for (int i = 0; i < WorkCatalog.size(); i++) {
                if ((WorkCatalog.get(i)).preReqs.size()==0) {
                    System.out.println("Your First Class is " + (WorkCatalog.get(i)).courseName);

                    // delete that popped course from the rest of the preReqs
                    //for each course
                    for (int j = 0; j < WorkCatalog.size(); j++) {
                        // Check each listed requirement
                        for (int k = 0; k < (WorkCatalog.get(k)).preReqs.size(); k++) {

                            /*THIS IS BROKE NEED TO LOOK AT AGAIN*/
                            System.out.println("Removing from lists " + (WorkCatalog.get(j)).courseName);
                            if ((WorkCatalog.get(j)).preReqs.toString().equals (WorkCatalog.get(i).courseName)) {
                                (WorkCatalog.get(j)).preReqs.remove(k);
                                System.out.println(" Removed "+ (WorkCatalog.get(j)).preReqs.remove(k) +
                                " from " + (WorkCatalog.get(j)).courseName);
                            }
                        }
                    }

                }
            }




        // Start again from the top wash rinse repeat

        CourseList = null;
        return CourseList;
    }


}
