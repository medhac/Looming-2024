package com.example.loomdemo.util;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import javax.swing.*;

public class BarChart extends JFrame {

    public BarChart() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(15, "Product 1", "Q1");
        dataset.addValue(30, "Product 1", "Q2");
        dataset.addValue(60, "Product 1", "Q3");
        dataset.addValue(120, "Product 1", "Q4");
        dataset.addValue(20, "Product 2", "Q1");
        dataset.addValue(35, "Product 2", "Q2");
        dataset.addValue(75, "Product 2", "Q3");
        dataset.addValue(150, "Product 2", "Q4");

        JFreeChart chart = ChartFactory.createBarChart(
                "Product Sales",
                "Quarter",
                "Sales ($)",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        ChartPanel panel = new ChartPanel(chart);
        setContentPane(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BarChart example = new BarChart();
            example.setSize(800, 400);
            example.setLocationRelativeTo(null);
            example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            example.setVisible(true);
        });
    }
}
