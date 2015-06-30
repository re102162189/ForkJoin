package com.test.power;

import static java.lang.Math.pow;
import static java.lang.Math.log10;
import static java.lang.System.out;
import static java.lang.System.nanoTime;

import java.util.concurrent.ForkJoinPool;

public class App {

	public static void main(String[] args) {

		ForkJoinPool forkJoinPool = new ForkJoinPool(20);
		long base = 3;
		long mod = (long) pow(10, 5);
		long startLoop = 0;
		long result = 0;
		long elapse = 0;
		
		for (long power = 10; power <= pow(10, 17); power *= 10) {
			out.println(String.format("==========power: 10^%.0f==========", log10(power)));

			RecursivePowerTask recursiveTask = new RecursivePowerTask(base, power, mod);
			startLoop = nanoTime();
			result = forkJoinPool.invoke(recursiveTask);
			elapse = nanoTime() - startLoop;
			out.println(String.format("1. result: %05d, elapse(nano): %d", result, elapse));

			PowerTask powerTask = new PowerTask(base, power, mod);
			startLoop = nanoTime();
			result = powerTask.compute();
			elapse = nanoTime() - startLoop;
			out.println(String.format("2. result: %05d, elapse(nano): %d", result, elapse));
		}
	}
}