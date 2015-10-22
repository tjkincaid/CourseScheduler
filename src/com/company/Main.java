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

        String filePath = "./data/testfile1.txt";
        String courses = fileToList(filePath);
        scheduleCourses(courses);


    }

    public static String scheduleCourses(String courseList){
        System.out.println("Your in Scheduled Courses");
        System.out.println(courseList);

        return courseList;
    }

    public static String fileToList(String filePath) throws FileNotFoundException {
        System.out.println("Printing the List with Scanner");
        String content = new Scanner(new File(filePath)).useDelimiter("\\Z").next();
        System.out.println(content);
        return content;
    }


}


