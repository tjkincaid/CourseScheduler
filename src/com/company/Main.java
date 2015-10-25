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

        List<Course> courseCatalog = new ArrayList<>();
        for (int i = 0; i < courseList.length; i++) {
            List<String> preReq = new ArrayList<>();

            // isolate the Parent course to a string
            String[] courseAndPreReq = courseList[i].split(":");
            String parentCourse = courseAndPreReq[0];
            parentCourse.replace(":", "");
            //System.out.println("Your Parent Course is " + ParentCourse);

            //split prereqs by " "
            String[] preReqSplit;
            if (courseAndPreReq.length > 1) {
                preReqSplit = courseAndPreReq[1].split(" ");
                //System.out.println(PreReqSplit);
            }else {preReqSplit =courseAndPreReq[0].split(" ");}

            // run through the rest of the children and add them to a new list
            for (int j = 1; j < preReqSplit.length; j++) {
                preReq.add(preReqSplit[j]);
            }
            //System.out.println("Your Child Courses are " + PreReq);
            courseCatalog.add(new Course(parentCourse, preReq));
        }

        String[] scheduledCourses = sequenceCourses(courseCatalog);

        return scheduledCourses;
    }

    /**
     *
     * @param courseCatalog
     * @return string[] CourseListRet
     * Takes in a string[] of course and their preReqs.
     * Places the data in a List of Course/s
     * Traverse the list and export a String[] with the most efficient Course path
     * IF there is an error then out the error to String[]
     */
    public static String[] sequenceCourses(List<Course> courseCatalog){
        List<String> courseListOut = new ArrayList<String>();
        //String[] CourseList;
        //List<Course> workCatalog ;
        //workCatalog = CourseCatalog;
        String popCourse = null;



//      Pop the first CourseName without a PreReq, Add to list and remove from class
        // order this by COURSE so that the FIRST course without a PREREQ is the one to use
        Collections.sort(courseCatalog);
//        for (int i = 0; i < workCatalog.size(); i++) {
//            System.out.println(i + " COURSE = " + (workCatalog.get(i)).courseName);
//            System.out.println(i + " PREREQS = " + (workCatalog.get(i)).preReqs.toString());
//
//        }

        // Loop and pop the first empty repreq
        // @PETE I looked to refactor this but it has 2 elements it would need to either return or globally change
        // @PETE so I didn't touch it. Design Ideas?
        int originalSize = courseCatalog.size();
        for (int j = 0; j < originalSize; j++) {
            for (int i = 0; i < courseCatalog.size(); i++) {
                if ((courseCatalog.get(i)).getPreReqs().size() == 0) {
                    //Get the course to be popped to the a variable
                    popCourse = (courseCatalog.get(i)).getCourseName();
                    //System.out.println("Your First Class is " + popCourse);
                    //Remove the course object
                    courseCatalog.remove(i);
                    courseListOut.add(popCourse);
                    break;
                }
            }
            // Call Method to remove preReq courses that match the course name being popped
            courseCatalog = removeReqs(courseCatalog, popCourse);

        }

        // Start again from the top wash rinse repeat
        String[] courseListRet = new String[courseListOut.size()];
        courseListRet = courseListOut.toArray(courseListRet);

        return courseListRet;
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
            for (int k = 0; k < courseCatalog.get(j).getPreReqs().size(); k++) {
                if ((courseCatalog.get(j).getPreReqs().get(k)).equals(pCourse)) {
                    //System.out.println("This is the Prereq you are looking at " + WorkCatalog.get(j).preReqs.get(k));
                    //System.out.println(WorkCatalog.get(j).preReqs.get(k) + " REMOVED from LIST");
                    courseCatalog.get(j).getPreReqs().remove(k);

                }
            }
        }
        return courseCatalog;
    }
}
