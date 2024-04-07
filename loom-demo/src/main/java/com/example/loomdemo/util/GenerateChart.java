package com.example.loomdemo.util;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYBarDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
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
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        ChartPanel panel = new ChartPanel(chart);
        setContentPane(panel);
    }

    public void createCPUBoundComparisonReport(Map<Integer, Double> virtualThreadTimeMap, Map<Integer, Double> platformThreadTimeMap) {
        XYBarDataset virtualTDataMap = createDataset(virtualThreadTimeMap, ThreadType.VIRTUAL);
        XYBarDataset platformTDataMap = createDataset(platformThreadTimeMap, ThreadType.PLATFORM);
        JFreeChart virtualChart = ChartFactory.createXYBarChart(
                "Virtual Thread Per Task Executor",
                "Thread Id",
                false,
                "Time in secs",
                virtualTDataMap,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );
        ChartPanel vPanel = new ChartPanel(virtualChart);

        JFreeChart platformChart = ChartFactory.createXYBarChart(
                "Platform Cached Thread Pool",
                "Thread Id",
                false,
                "Time in secs",
                platformTDataMap,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );
        ChartPanel pPanel = new ChartPanel(platformChart);

        JPanel chartPanel = new JPanel(new GridLayout(2, 1));
        chartPanel.add(vPanel);
        chartPanel.add(pPanel);

        setContentPane(chartPanel);
}

private XYBarDataset createDataset(Map<Integer, Double> timeMap, ThreadType threadType) {
        XYSeriesCollection dataset = new XYSeriesCollection();
        XYSeries thread = new XYSeries(threadType.name());
        timeMap.forEach(thread::add);
        dataset.addSeries(thread);
    return new XYBarDataset(dataset, 0.5);
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
