package com.world.alfs.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    /* 400 BAD_REQUEST */
    ACCESS_TOKEN_INVALID(HttpStatus.BAD_REQUEST, "Access Token 유효하지 않은 토큰입니다."),
    FILE_CONVERT_FAIL(HttpStatus.BAD_REQUEST, "error: MultipartFile -> File convert fail"),

    /* 401 UNAUTHORIZED */


    /* 403 FORBIDDEN : 페이지 접근 거부 */

    /* 404 NOT_FOUND : Resource 를 찾을 수 없음 */
    PRODUCT_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 상품을 찾을 수 없습니다."),
    INGREDIENT_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 원재료가 존재하지 않습니다."),
    SUPERVISOR_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 관리자를 찾을 수 없습니다."),

    /* 409 CONFLICT : Resource 의 현재 상태와 충돌. 보통 중복된 데이터 존재 */


    /* 500 INTERNAL_SERVER_ERROR : 서버 내부 로직 에러 */
    DUPLICATE_SPECIAL_ID(HttpStatus.INTERNAL_SERVER_ERROR, "상품 아이디가 중복됩니다."),
    SUPERVISOR_ID_MISMATCH(HttpStatus.FORBIDDEN, "관리자 아이디가 일치하지 않습니다.");

    private final HttpStatus httpStatus;
    private final String description;
}
