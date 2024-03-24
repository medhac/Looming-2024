package com.example.loomdemo;

import com.example.loomdemo.threadcompare.CreatePlatformThread;
import com.example.loomdemo.threadcompare.CreateVirtualThread;
import com.example.loomdemo.util.GenerateChart;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import javax.swing.*;
import java.util.*;

@SpringBootApplication
public class LoomDemoApplication {

	@Autowired
	private CreateVirtualThread createVirtualThread;

	@Autowired
	private CreatePlatformThread createThread;

	public static void main(String[] args) {
		new SpringApplicationBuilder(LoomDemoApplication.class)
				.headless(false).run(args);
	}

	@PostConstruct
	public void init() {
		String  numberOfThreadPerRun = System.getProperty("numberOfThreadPerRun");
		List<Integer> noOfThreadPerRunList = getNumberOfThreadPerRunList(numberOfThreadPerRun);
		Map<Integer,Double> virtualThreadTimeMap = new TreeMap<>();
		Map<Integer,Double> platformThreadTimeMap = new TreeMap<>();
		for (Integer integer : noOfThreadPerRunList) {
			platformThreadTimeMap = createThread.createPlatformThread(integer);
			virtualThreadTimeMap = createVirtualThread.createVirtualThread(integer);
        }

		Map<Integer, Double> finalVirtualThreadTimeMap = virtualThreadTimeMap;
		Map<Integer, Double> finalPlatformThreadTimeMap = platformThreadTimeMap;
		SwingUtilities.invokeLater(() -> {
			GenerateChart chart = new GenerateChart();
			chart.createComparisonReport(finalVirtualThreadTimeMap, finalPlatformThreadTimeMap);
			chart.setSize(800, 400);
			chart.setLocationRelativeTo(null);
			chart.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			chart.setVisible(true);
		});
	}

	List<Integer>getNumberOfThreadPerRunList(String numberOfThreadPerRun) {
		String[] noOfThreadPerRunArray = numberOfThreadPerRun.split(",");
		List<Integer> noOfThreadPerRunList = new ArrayList<>();
		for (String noOfThreadPerRun : noOfThreadPerRunArray) {
			noOfThreadPerRunList.add(Integer.parseInt(noOfThreadPerRun));
		}
		return noOfThreadPerRunList;
	}
}
//https://github.com/hakdogan/loom-examples/blob/main/virtual-threads/README.md