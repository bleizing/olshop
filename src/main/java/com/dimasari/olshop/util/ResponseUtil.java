package com.dimasari.olshop.util;

import com.dimasari.olshop.dto.BaseResponse;
import com.dimasari.olshop.dto.BaseResponsePagination;

public class ResponseUtil {
	public static <T> BaseResponse<T> constructBaseResponse(T data) {
		var response = new BaseResponse<>(data);
		response.success();
		
		return response;
	}
	
	public static BaseResponsePagination constructBaseResponsePagination(int currentPage, int numberOfElements, long totalElements, int totalPage, BaseResponsePagination baseResponsePagination) {
		baseResponsePagination.setCurrentPage(currentPage);
		baseResponsePagination.setNumberOfElements(numberOfElements);
		baseResponsePagination.setTotalElements(totalElements);
		baseResponsePagination.setTotalPage(totalPage);
		
		return baseResponsePagination;
	}
}
