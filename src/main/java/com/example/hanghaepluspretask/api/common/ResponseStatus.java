package com.example.hanghaepluspretask.api.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ResponseStatus {

	OK("SUCCESS"),
	FAIL("FAIL");

	private final String message;
}
