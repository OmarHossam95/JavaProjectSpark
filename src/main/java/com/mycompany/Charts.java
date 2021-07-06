package com.mycompany;

import org.knowm.xchart.*;
import org.knowm.xchart.style.Styler;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Charts {
    // Function responsible to draw Pie Chart of count of jobs per company
    public static void jobsPerCompanyPieChart(List<Map.Entry> jobsPerCompany){
        PieChart chart = new PieChartBuilder().width(800).height(600).title("Jobs Per Company").build();

        Color[] sliceColors = new Color[]{new Color(0, 0, 128), new Color(0, 128, 0), new Color(128, 0, 0)};
        chart.getStyler().setSeriesColors(sliceColors);

        for (int i = 0; i <= 5; i++){
            chart.addSeries((String) jobsPerCompany.get(i).getKey(), (Long) jobsPerCompany.get(i).getValue());
        }

        new SwingWrapper(chart).displayChart();


    }

    // Function responsible to draw Bar Chart of count of jobs
    public static void jobsCountBarChart(List<Map.Entry> jobsCount) {
        CategoryChart chart = new CategoryChartBuilder().width(1024).height(768).title("Most Popular Jobs").xAxisTitle("Jobs").yAxisTitle("Count").build();
        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNW);
        chart.getStyler().setHasAnnotations(true);
        chart.getStyler().setStacked(true);

        ArrayList<String> jobsNames = new ArrayList<String>();
        ArrayList<Long> counts = new ArrayList<Long>();
        for (Map.Entry<String, Long> entry: jobsCount){
            jobsNames.add(entry.getKey());
            counts.add(entry.getValue());
        }


        chart.addSeries("Jobs Count", jobsNames.subList(0, 10), counts.subList(0, 10));
        new SwingWrapper(chart).displayChart();
    }

    // Function responsible to draw Bar Chart of count of areas
    public static void areaCountBarChart(List<Map.Entry> areaCount) {
        CategoryChart chart = new CategoryChartBuilder().width(1024).height(768).title("Most Popular Areas").xAxisTitle("Areas").yAxisTitle("Count").build();
        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNW);
        chart.getStyler().setHasAnnotations(true);
        chart.getStyler().setStacked(true);
        ArrayList<String> areaNames = new ArrayList<String>();
        ArrayList<Long> counts = new ArrayList<Long>();
        for (Map.Entry<String, Long> entry: areaCount){
            areaNames.add(entry.getKey());
            counts.add(entry.getValue());
        }

        chart.addSeries("Jobs Count", areaNames.subList(0, 10), counts.subList(0, 10));
        new SwingWrapper(chart).displayChart();
    }
}
