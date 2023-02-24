package org.example.my;

import feign.RetryableException;
import feign.Retryer;

public class MyRetryer implements Retryer {

	private int maxAttempts;
	private long maxDelay;
	private long backoff;

	public MyRetryer() {
		this( 5, 5000, 500 );
	}

	public MyRetryer( int maxAttempts, long maxDelay, long backoff ) {
		this.maxAttempts = maxAttempts;
		this.maxDelay = maxDelay;
		this.backoff = backoff;
	}

	@Override
	public void continueOrPropagate( RetryableException e ) {

		// example interface - Retryer
		try {

			Thread.sleep( 1000L );
		} catch ( InterruptedException ex ) {
			Thread.currentThread().interrupt();
			throw e;
		}

	}

	@Override
	public Retryer clone() {
		return new MyRetryer( maxAttempts, maxDelay, backoff );
	}
}
