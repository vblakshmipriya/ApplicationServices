package com.comcast.technucleus.application.cache;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorFactory {
	
	private static ExecutorService executorService;
	
	private ExecutorFactory() {
		
	}
	
	public static ExecutorService getExecutorServiceInstance() {
		
		String threadPoolSize = "10";
		
		if(null == executorService) {
			executorService = Executors.newFixedThreadPool(Integer.parseInt(threadPoolSize));
		}
			return executorService;
	}
}
