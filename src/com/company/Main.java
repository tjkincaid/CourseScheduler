package com.company;
import com.sun.corba.se.spi.orbutil.threadpool.Work;
import com.sun.org.apache.bcel.internal.generic.POP;
import com.sun.org.apache.bcel.internal.generic.RET;
import com.sun.source.tree.WhileLoopTree;
import com.sun.tools.javac.comp.Annotate;
import com.sun.xml.internal.ws.api.pipe.FiberContextSwitchInterceptor;
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
        System.out.println("Your Course Work List looks like this: " + Arrays.toString(WorkList));

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
        List<String> courseListOut = new ArrayList<String>();
        //String[] CourseList;
        List<Course> WorkCatalog ;
        WorkCatalog = CourseCatalog;
        String PopCourse= "PopCourse";

//        Sort the WorkCatalog by CourseName, Do we really even need to do this?

//        Pop the first CourseName without a PreReq, Add to list and remove from class
        int noReqCount = 0;

        // order this by COURSE so that the FIRST course without a PREREQ is the one to use

        Collections.sort(WorkCatalog);


        for (int i = 0; i < WorkCatalog.size(); i++) {
            System.out.println(i + " COURSE = " + (WorkCatalog.get(i)).courseName);
            System.out.println(i + " PREREQS = " + (WorkCatalog.get(i)).preReqs.toString());

        }

        // Loop and pop the first empty repreq

            //for (int i = 0; i < WorkCatalog.size(); i++) {

            for (int i = 0; i < WorkCatalog.size(); i++) {
                if ((WorkCatalog.get(i)).preReqs.size() == 0) {
                    //Get the course to be popped to the a variable
                    PopCourse = (WorkCatalog.get(i)).courseName;
                    System.out.println("Your First Class is " + PopCourse);
                    //Remove the course object
                    WorkCatalog.remove(i);
                    courseListOut.add(PopCourse);
                }
            }

        WorkCatalog = removeReqs(WorkCatalog, PopCourse);
           // delete that popped course from the rest of the preReqs
            //for each course
//            for (int j = 0; j < WorkCatalog.size(); j++) {
//                // Check each listed requirement
//                System.out.println("This is the Course that you are checking Prereqs "+ (WorkCatalog.get(j).courseName));
//                for (int k = 0; k < WorkCatalog.get(j).preReqs.size(); k++) {
//                    if ((WorkCatalog.get(j).preReqs.get(k)).equals(PopCourse)) {
//                        System.out.println("This is the Prereq you are looking at " + WorkCatalog.get(j).preReqs.get(k));
//                        System.out.println( WorkCatalog.get(j).preReqs.get(k) + " REMOVED from LIST");
//                        WorkCatalog.get(j).preReqs.remove(k);
//
//                    }
//
//                }

            //}



        // Start again from the top wash rinse repeat

        String[] CourseListRet = new String[courseListOut.size()];
        CourseListRet = courseListOut.toArray(CourseListRet);
        return CourseListRet;
    }

    public static List<Course> removeReqs(List<Course> WorkCatalog, String PopCourse) {

        List<Course> courseCatalog = new ArrayList<>(WorkCatalog);
        for (int j = 0; j < courseCatalog.size(); j++) {
            // Check each listed requirement
            System.out.println("This is the Course that you are checking Prereqs " + (courseCatalog.get(j).courseName));
            for (int k = 0; k < courseCatalog.get(j).preReqs.size(); k++) {
                if ((courseCatalog.get(j).preReqs.get(k)).equals(PopCourse)) {
                    System.out.println("This is the Prereq you are looking at " + WorkCatalog.get(j).preReqs.get(k));
                    System.out.println(WorkCatalog.get(j).preReqs.get(k) + " REMOVED from LIST");
                    courseCatalog.get(j).preReqs.remove(k);

                }

            }
        }
        return courseCatalog;
    }
}
