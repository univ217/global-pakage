package com.hansung.likelion.global.response;

import com.hansung.likelion.global.response.code.BaseResponseCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@RequiredArgsConstructor
@ToString
public class BaseResponse {
    private final Boolean isSuccess;
    private final String code;
    private final String message;
    private final String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

    // 정적 팩토리 메소드
    // 성공여부, baseResponseCode를 기반으로 넘겨줌
    public static BaseResponse of(Boolean isSuccess, BaseResponseCode baseResponseCode){
        return new BaseResponse(isSuccess, baseResponseCode.getCode(), baseResponseCode.getMessage());
    }

    // 성공여부, baseResponseCode, 메세지를 넘겨줌
    public static BaseResponse of(Boolean isSuccess, BaseResponseCode baseResponseCode, String message){
        return new BaseResponse(isSuccess, baseResponseCode.getCode(), message);
    }

    // 성공여부, 코드, 메세지 모두 커스텀해서 사용하는 방식
    public static BaseResponse of(Boolean isSuccess, String code, String message) {
        return new BaseResponse(isSuccess, code, message);
    }
}
