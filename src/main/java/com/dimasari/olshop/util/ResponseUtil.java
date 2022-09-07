package com.dimasari.olshop.util;

import com.dimasari.olshop.dto.BaseResponse;

public class ResponseUtil {
	public static <T> BaseResponse<T> constructBaseResponse(T data) {
		var response = new BaseResponse<>(data);
		response.success();
		
		return response;
	}
}
