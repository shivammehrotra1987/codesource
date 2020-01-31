package com.designpatterns.threads;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class TestExecutorService implements Runnable {

	static int randomInteger;
	static BlockingQueue<Integer> queue = new LinkedBlockingDeque<>();

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		randomInteger= new Random().nextInt(10);
		try {
			Thread.sleep(7000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(randomInteger);
		queue.add(randomInteger);

	}

	public static void main(String args[]) throws InterruptedException, ExecutionException {

		ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();

		boolean flag = true;
	    service.scheduleAtFixedRate(new TestExecutorService(),0, 5, TimeUnit.SECONDS);
	    
	    service.scheduleAtFixedRate(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				randomInteger= new Random().nextInt(10);
				try {
					Thread.sleep(7000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(randomInteger);
				queue.add(randomInteger);
			}
	    	
	    },5, 5, TimeUnit.SECONDS);

	
		
		//System.out.println(randomInteger);
		long timeout = System.currentTimeMillis() + 30000;
		int num = 0;
		while (flag) {
			if (System.currentTimeMillis() <= timeout) {
			//	ScheduledFuture<Object> random=service.schedule(new TestExecutorService(), 5, TimeUnit.SECONDS);
				
				if(!queue.isEmpty()){
					 num=queue.poll();
					System.out.println("num="+num);
				}
				if ( num == 8) {
					flag = false;
					service.shutdown();
				}
			}else{
				flag=false;
				service.shutdown();
			}
		}
		
		System.out.println("code ended");
	}
}	

	


