package com.example.hanghaepluspretask.util;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class DateUtil {
	public static Instant now() {
		return Instant.now();
	}

	public static Instant after(long afterTime, ChronoUnit unit) {
		return Instant.now().plus(afterTime, unit);
	}

	public static Instant after(long afterTime, ChronoUnit unit, Instant time) {
		return time.plus(afterTime, unit);
	}
}
