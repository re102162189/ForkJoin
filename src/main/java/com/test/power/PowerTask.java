package com.test.power;

public class PowerTask {

	private long base;

	private long power;

	private long mod;

	public PowerTask(long base, long power, long mod) {
		this.base = base;
		this.power = power;
		this.mod = mod;
	}

	public long compute() {
		
		long result = 1;
		
		for (; power > 0; base = base * base % mod, power >>= 1) {
			if ((power & 1) == 1) {
				result = result * base % mod;
			}
		}
		
		return result;
	}
}
