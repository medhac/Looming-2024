package com.example.loomdemo.util;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.util.Map;

@Component
public class GenerateChart extends JFrame {

    public GenerateChart() {
        super("Platform vs Virtual Threads");
    }
    public void createComparisonReport(Map<Integer, Double> virtualThreadTimeMap, Map<Integer, Double> platformThreadTimeMap) {
        XYDataset dataset = createDataset(virtualThreadTimeMap, platformThreadTimeMap);
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Platform vs Virtual Threads",
                "No Of Threads",
                "Time Taken in Seconds",
                dataset,
                org.jfree.chart.plot.PlotOrientation.VERTICAL,
                true,
                true,
                false
        );
        ChartPanel panel = new ChartPanel(chart);
        setContentPane(panel);
    }

    private XYDataset createDataset(Map<Integer, Double> virtualThreadTimeMap, Map<Integer, Double> platformThreadTimeMap) {
        XYSeriesCollection dataset = new XYSeriesCollection();
        XYSeries virtualThread = new XYSeries(ThreadType.VIRTUAL);
        virtualThreadTimeMap.forEach(virtualThread::add);
        dataset.addSeries(virtualThread);

        XYSeries platformThread = new XYSeries(ThreadType.PLATFORM);
        platformThreadTimeMap.forEach(platformThread::add);
        dataset.addSeries(platformThread);
        return dataset;
    }
}
