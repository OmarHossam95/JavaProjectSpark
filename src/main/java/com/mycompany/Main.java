package com.mycompany;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import java.util.List;
import java.util.Map;


public class Main {
    public static void main(String[] args){
        Logger.getLogger("org").setLevel(Level.ERROR);
        SparkConf conf = new SparkConf().setAppName("JavaProjectSpark").setMaster("local[3]");
        JavaSparkContext sparkContext = new JavaSparkContext(conf);

        JobProvider jobProvider = new JobProvider();
        JavaRDD<String> Jobs = jobProvider.jobDataFrame("src/main/resources/Wuzzuf_Jobs.csv", sparkContext);

        List<Map.Entry> sortedCompaniesCount = Operations.getJobCountPerCompany(Jobs);
        for (Map.Entry<String, Long> entry: sortedCompaniesCount){
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        List<Map.Entry> sortedJobsCount = Operations.getMostPopularJobs(Jobs);
        for (Map.Entry<String, Long> entry: sortedJobsCount){
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        List<Map.Entry> sortedAreasCount = Operations.getMostPopularAreas(Jobs);
        for (Map.Entry<String, Long> entry: sortedAreasCount){
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }


        List<Map.Entry> sortedSkillsCount = Operations.getMostPopularSkills(Jobs);
        for (Map.Entry<String, Long> entry: sortedSkillsCount){
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }


        Charts.jobsCountBarChart(sortedJobsCount);
        Charts.areaCountBarChart(sortedAreasCount);
        Charts.jobsPerCompanyPieChart(sortedCompaniesCount);


    }
}
