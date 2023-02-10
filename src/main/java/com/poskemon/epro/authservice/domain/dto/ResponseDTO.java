package com.poskemon.epro.authservice.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * HTTP 응답 시 body로 전달할 데이터를 담는 DTO 클래스
 *
 * 응답 데이터는 data 필드에 담고, message가 필요한 경우 msg에 담는다.
 * 다양한 DTO 클래스를 응답 데이터로 처리하기 위해 Generic을 사용함
 *
 * @author isohyeon
 * @param <T> HTTP 응답으로 반환할 데이터, 데이터 타입 상관 없음
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL) // null 필드 생략
public class ResponseDTO<T> {
	@JsonProperty("RETURN_MSG")
	private String msg;
	@JsonProperty("RETURN_DATA")
	private Object data;
}
