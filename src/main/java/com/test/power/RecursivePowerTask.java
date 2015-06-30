package com.test.power;

import java.util.concurrent.RecursiveTask;

public class RecursivePowerTask extends RecursiveTask<Long> {

	private static final long serialVersionUID = 1L;

	private long base;

	private long power;

	private long mod;

	public RecursivePowerTask(long base, long power, long mod) {
		this.base = base;
		this.power = power;
		this.mod = mod;
	}

	@Override
	protected Long compute() {
		
		if (power == 1) {
			return base;
		}

		RecursivePowerTask task = new RecursivePowerTask(base, power >> 1, mod);
		task.fork();
		long result = task.join();
		result *= result;
		
		if (power % 2 != 0) {
			result *= base;
		}

		return result % mod;
	}
}