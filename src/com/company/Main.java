package com.company;
import java.io.*;
import java.nio.file.Files;
import java.security.PublicKey;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;
import java.nio.file.Paths;
import java.util.stream.Collectors;

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

            // run through the rest of the children and add them to a new list
            for (int j = 1; j < courseAndPreReq.length; j++) {

                PreReq.add(courseAndPreReq[j]);


            }
            System.out.println("Your Child Courses are " + PreReq);


            CourseCatalog.add(new Course(ParentCourse, PreReq));







        }
        System.out.println("Here is the Catalog!");
        System.out.println(CourseCatalog);


        return courseList;
    }

    public static List<Course> makeList(String [] parentCourse, String[] courseAndPreReq){
        List<Course> CourseList = new ArrayList<>();
        // take ParentCourse, and courseAndPreReq and build objects to add to a List for work




        return CourseList;
    }


}


