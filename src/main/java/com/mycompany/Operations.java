package com.mycompany;

import org.apache.spark.api.java.JavaRDD;

import java.util.*;
import java.util.stream.Collectors;

public class Operations {

    //Extract company field from data
    public static String extractCompany(String df){
        try{
            return df.split(",")[1];
        }
        catch (ArrayIndexOutOfBoundsException e){
            return "";
        }
    }

    //Extract jobs field from data
    public static String extractJobs(String df){
        try{
            return df.split(",")[0];
        }
        catch (ArrayIndexOutOfBoundsException e){
            return "";
        }
    }

    //Extract areas field from data
    public static String extractAreas(String df){
        try{
            return df.split(",")[2];
        }
        catch (ArrayIndexOutOfBoundsException e){
            return "";
        }
    }

    //Extract skills field from data
    public static String extractSkillsColumn(String df){
        try{
            String data[] = df.split(",");
            String skills = String.join(",", Arrays.copyOfRange(data, 7, data.length-1));
            return skills;
        }
        catch (ArrayIndexOutOfBoundsException e){
            return null;
        }
    }


    //Function Responsible to get count of jobs per each company
    public static List<Map.Entry> getJobCountPerCompany(JavaRDD<String> df){
        JavaRDD<String> companies = df.map(Operations::extractCompany);
        Map<String, Long> companiesCount = companies.countByValue();
        List<Map.Entry> sortedCompaniesCount = companiesCount.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).collect(Collectors.toList());
        return sortedCompaniesCount;
    }

    //Function responsible to get count of each job
    public static List<Map.Entry> getMostPopularJobs(JavaRDD<String> df){
        JavaRDD<String> jobs = df.map(Operations::extractJobs);
        Map<String, Long> jobsCount = jobs.countByValue();
        List<Map.Entry> sortedJobsCount = jobsCount.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).collect(Collectors.toList());
        return sortedJobsCount;
    }

    //Function responsible to get count of each area
    public static List<Map.Entry> getMostPopularAreas(JavaRDD<String> df){
        JavaRDD<String> areas = df.map(Operations::extractAreas);
        Map<String, Long> areasCount = areas.countByValue();
        List<Map.Entry> sortedAreasCount = areasCount.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).collect(Collectors.toList());
        return sortedAreasCount;
    }

    //Function responsible to get count of each skill
    public static List<Map.Entry> getMostPopularSkills(JavaRDD<String> df){
        JavaRDD<String> skills = df.map(Operations::extractSkillsColumn);
        skills = skills.flatMap(skill -> Arrays.asList(skill.split(",")).iterator());
        Map<String, Long> skillsCount = skills.countByValue();
        List<Map.Entry> sortedSkillsCount = skillsCount.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).collect(Collectors.toList());
        return sortedSkillsCount;
    }
}
