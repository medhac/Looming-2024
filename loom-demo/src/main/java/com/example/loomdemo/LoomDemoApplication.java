package com.example.loomdemo;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class LoomDemoApplication {

	@Autowired
	private CreateVirtualThread createVirtualThread;

	@Autowired
	private CreateThread createThread;

	public static void main(String[] args) {
		SpringApplication.run(LoomDemoApplication.class, args);
	}
	@PostConstruct
	public void init() {
		String  numberOfThreadPerRun = System.getProperty("numberOfThreadPerRun");
		List<Integer> noOfThreadPerRunList = getNumberOfThreadPerRunList(numberOfThreadPerRun);
	       for(int i = 0; i < noOfThreadPerRunList.size(); i++) {
			   createVirtualThread.readFileWithVirtualThread(noOfThreadPerRunList.get(i));
			   createThread.createThreadOldWay(noOfThreadPerRunList.get(i));
		   }
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
