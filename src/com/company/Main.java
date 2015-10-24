package com.company;
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException{
        // write your code here

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

    /**
     *
     * @param courseList
     * @return
     */
    public static String[] scheduleCourses(String[] courseList){
        //System.out.println("Your in Scheduled Courses");
        //System.out.println(Arrays.toString(courseList));
        // process parent course and pre reqs to add to a list

        List<Course> CourseCatalog = new ArrayList<>();
        for (int i = 0; i < courseList.length; i++) {
            List<String> PreReq = new ArrayList<>();

            // isolate the Parent course to a string
            String[] courseAndPreReq = courseList[i].split(":");
            String ParentCourse = courseAndPreReq[0];
            ParentCourse.replace(":", "");
            //System.out.println("Your Parent Course is " + ParentCourse);

            //split prereqs by " "
            String[] PreReqSplit;
            if (courseAndPreReq.length > 1) {
                PreReqSplit = courseAndPreReq[1].split(" ");
                //System.out.println(PreReqSplit);
            }else {PreReqSplit =courseAndPreReq[0].split(" ");}

            // run through the rest of the children and add them to a new list
            for (int j = 1; j < PreReqSplit.length; j++) {
                PreReq.add(PreReqSplit[j]);
            }
            //System.out.println("Your Child Courses are " + PreReq);
            CourseCatalog.add(new Course(ParentCourse, PreReq));
        }

        courseList = makeOutputList(CourseCatalog);

        return courseList;
    }

    /**
     *
     * @param CourseCatalog
     * @return string[] CourseListRet
     * Takes in a string[] of course and their preReqs.
     * Places the data in a List of Course/s
     * Traverse the list and export a String[] with the most efficient Course path
     * IF there is an error then out the error to String[]
     */
    public static String[] makeOutputList(List<Course> CourseCatalog){
        List<String> courseListOut = new ArrayList<String>();
        //String[] CourseList;
        List<Course> workCatalog ;
        workCatalog = CourseCatalog;
        String popCourse= "popCourse";



//      Pop the first CourseName without a PreReq, Add to list and remove from class
        int noReqCount = 0;

        // order this by COURSE so that the FIRST course without a PREREQ is the one to use
        Collections.sort(workCatalog);
//        for (int i = 0; i < workCatalog.size(); i++) {
//            System.out.println(i + " COURSE = " + (workCatalog.get(i)).courseName);
//            System.out.println(i + " PREREQS = " + (workCatalog.get(i)).preReqs.toString());
//
//        }

        // Loop and pop the first empty repreq
        // @PETE I looked to refactor this but it has 2 elements it would need to either return or globally change
        // @PETE so I didn't touch it. Design Ideas?
        int originalSize = workCatalog.size();
        for (int j = 0; j < originalSize; j++) {
            for (int i = 0; i < workCatalog.size(); i++) {
                if ((workCatalog.get(i)).preReqs.size() == 0) {
                    //Get the course to be popped to the a variable
                    popCourse = (workCatalog.get(i)).courseName;
                    //System.out.println("Your First Class is " + popCourse);
                    //Remove the course object
                    workCatalog.remove(i);
                    courseListOut.add(popCourse);
                    break;
                }
            }
            // Call Method to remove preReq courses that match the course name being popped
            workCatalog = removeReqs(workCatalog, popCourse);

        }

        // Start again from the top wash rinse repeat
        String[] CourseListRet = new String[courseListOut.size()];
        CourseListRet = courseListOut.toArray(CourseListRet);
        return CourseListRet;
    }


    /**
     *
     * @param WorkCatalog
     * @param PopCourse
     * @return courseCatalog
     * Method is used to remove a course from the preReqs of each existing course
     */
    public static List<Course> removeReqs(List<Course> WorkCatalog, String PopCourse) {

        List<Course> courseCatalog = new ArrayList<>(WorkCatalog);
        String pCourse = PopCourse;
        for (int j = 0; j < courseCatalog.size(); j++) {
            // Check each listed requirement
            //System.out.println("This is the Course that you are checking Prereqs " + (courseCatalog.get(j).courseName));
            for (int k = 0; k < courseCatalog.get(j).preReqs.size(); k++) {
                if ((courseCatalog.get(j).preReqs.get(k)).equals(pCourse)) {
                    //System.out.println("This is the Prereq you are looking at " + WorkCatalog.get(j).preReqs.get(k));
                    //System.out.println(WorkCatalog.get(j).preReqs.get(k) + " REMOVED from LIST");
                    courseCatalog.get(j).preReqs.remove(k);

                }
            }
        }
        return courseCatalog;
    }
}
