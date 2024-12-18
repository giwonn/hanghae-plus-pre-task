package com.example.hanghaepluspretask.common;

import lombok.Data;

@Data
public class CommonResponse<T> {

	private String message;
	private T data;

	public CommonResponse(String message, T data) {
		this.message = message;
		this.data = data;
	}

	public static <T> CommonResponse<T> ok(T data) {
		return new CommonResponse<>(ResponseStatus.OK.getMessage(), data);
	}

	public static <T> CommonResponse<T> deleted() {
		return new CommonResponse<>(ResponseStatus.OK.getMessage(), null);
	}
}
