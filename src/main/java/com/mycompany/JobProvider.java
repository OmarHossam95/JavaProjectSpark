package com.mycompany;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

public class JobProvider {
    public JavaRDD<String> jobDataFrame(String path, JavaSparkContext sparkContext){
        return sparkContext.textFile(path);
    }
}
