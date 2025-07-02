package com.hansung.likelion.global.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.hansung.likelion.global.response.code.BaseResponseCode;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonPropertyOrder({"isSuccess", "timestamp", "code", "httpStatus", "message", "data"}) // 필드들이 순서대로 제공될 수 있도록 함
public class ErrorResponse<T> extends BaseResponse {
    private final int httpStatus;
    private final T data; // 응답데이터, T는 제네릭: 데이터의 어느타입이 올지 모를 때

    @Builder
    public ErrorResponse(T data, BaseResponseCode baseResponseCode) {
        super(false, baseResponseCode.getCode(), baseResponseCode.getMessage());
        this.httpStatus = baseResponseCode.getHttpStatus();
        this.data = data;
    }

    // 메세지 직접 커스텀 가능
    @Builder
    public ErrorResponse(T data, BaseResponseCode baseResponseCode, String message) {
        super(false, baseResponseCode.getCode(), message);
        this.httpStatus = baseResponseCode.getHttpStatus();
        this.data = data;
    }

    // 정적 팩토리 메소드
    // 얼마든지 에러 반환 코드 커스텀 가능
    // 데이터X, baseResponseCode만 반환
    public static ErrorResponse<?> from(BaseResponseCode baseResponseCode) {
        return new ErrorResponse<>(null, baseResponseCode);
    }

    // 데이터X, 메세지는 직접 커스텀하고 싶을 때
    public static ErrorResponse<?> of(BaseResponseCode baseResponseCode, String message) {
        return new ErrorResponse<>(null, baseResponseCode, message);
    }

    // 데이터O, baseResponseCode 활용
    public static <T> ErrorResponse<T> of(BaseResponseCode baseResponseCode, T data) {
        return new ErrorResponse<>(data, baseResponseCode);
    }

    // 데이터O, baseResponseCode 활용, 커스텀 메세지 사용하고 싶을 경우
    public static <T> ErrorResponse<T> of(BaseResponseCode baseResponseCode, T data, String message) {
        return new ErrorResponse<>(data, baseResponseCode, message);
    }
}
