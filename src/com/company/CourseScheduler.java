package com.company;
import java.io.*;
import java.util.*;

public class CourseScheduler {

    public static void main(String[] args) throws IOException{
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

        /* The method should return:
        {"INTR100","CSE101","CSE111","ECE111","ECE201","MATH210","CSE254","CSE221","CSE2
            43","CSE244","CSE258"}*/

        //load a new list with individual courses and their prereqs
        CourseScheduler cs = new CourseScheduler();
        String[] WorkList = cs.scheduleCourses(allCourses);
        if (WorkList.length != allCourses.length) {
            System.out.println("There was a problem with your list");
        }else {
            System.out.println("Your Course Work List looks like this: " + Arrays.toString(WorkList));
        }
    }
    /**
     *
     * @param courseList
     * @return scheduledCourses
     *  The engine that generates an efficient course list for study
     */
    public String[] scheduleCourses(String[] courseList){

        // process parent course and pre reqs to add to a list
        List<Course> courseCatalog = new ArrayList<>();
        for (int i = 0; i < courseList.length; i++) {
            List<String> preReq = new ArrayList<>();

            // isolate the Parent course to a string
            String[] courseAndPreReq = courseList[i].split(":");
            String parentCourse = courseAndPreReq[0];

            // split off the alpha
            String[] parentCourseNum = parentCourse.split("[a-zA-Z]+");
            String pCourseNum = parentCourseNum[1];

            //split off the numberic
            String[] parentCourseName = parentCourse.split("\\d+");
            String pCourseName = parentCourseName[0];

            //split prereqs by " "
            String[] preReqSplit;
            if (courseAndPreReq.length > 1) {
                preReqSplit = courseAndPreReq[1].split(" ");
            }else {preReqSplit =courseAndPreReq[0].split(" ");}

            // run through the rest of the children and add them to a new list
            for (int j = 1; j < preReqSplit.length; j++) {
                preReq.add(preReqSplit[j]);
            }
            courseCatalog.add(new Course(parentCourse, pCourseNum, preReq));
        }
        return sequenceCourses(courseCatalog);
    }
    /**
     * @param courseCatalog
     * @return string[] CourseListRet
     *  Takes in a string[] of course and their preReqs.
     * Places the data in a List of Course/s
     * Traverse the list and export a String[] with the most efficient Course path
     * IF there is an error then out the error to String[]
     */
    public static String[] sequenceCourses(List<Course> courseCatalog){
        List<String> courseListOut = new ArrayList<>();
        String popCourse = null;

        //Pop the first CourseName without a PreReq, Add to list and remove from class
        // order this by COURSE so that the FIRST course without a PREREQ is the one to use
        doubleSort(courseCatalog);

        // Loop and pop the first empty repreq
        int originalSize = courseCatalog.size();
        for (int j = 0; j < originalSize; j++) {
            for (int i = 0; i < courseCatalog.size(); i++) {
                if ((courseCatalog.get(i)).getPreReqs().size() == 0) {
                    //Get the course to be popped to the a variable
                    popCourse = (courseCatalog.get(i)).getCourseName();
                    //Remove the course object
                    courseCatalog.remove(i);
                    courseListOut.add(popCourse);
                    break;
                }
            }
            // Call Method to remove preReq courses that match the course name being popped
            removeReqs(courseCatalog, popCourse);
        }
        // Start again from the top wash rinse repeat
        String[] courseListRet = new String[courseListOut.size()];
        courseListRet = courseListOut.toArray(courseListRet);
        return courseListRet;
    }
    /**
     *
     * @param courseCatalog
     *  sorts on course ID and then name
     */
    public static void doubleSort(List<Course> courseCatalog){
        Collections.sort(courseCatalog, new Comparator<Course>() {
            @Override
            public int compare(Course o1, Course o2) {
                // sort CourseNumber
                String x1 = (o1).getCourseNumber();
                String x2 = (o2).getCourseNumber();
                int sComp = x1.compareTo(x2);
                if (sComp != 0) {
                    return sComp;
                }else{
                    //Sort CourseName
                    String y1 = (o1).getCourseName();
                    String y2 = (o2).getCourseName();
                    return y1.compareTo(y2);
                }
            }
        });
    }
    /**
     *
     * @param workCatalog
     * @param popCourse
     *  courseCatalog
     *  Method is used to remove a course from the preReqs of each existing course
     *
     */
    public static void removeReqs(List<Course> workCatalog, String popCourse) {

        for (int j = 0; j < workCatalog.size(); j++) {
            // Check each listed requirement
            for (int k = 0; k < workCatalog.get(j).getPreReqs().size(); k++) {
                if ((workCatalog.get(j).getPreReqs().get(k)).equals(popCourse)) {
                    workCatalog.get(j).getPreReqs().remove(k);
                }
            }
        }
    }
}
