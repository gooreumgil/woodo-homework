package com.woodo.homework.api.constants;

public class ValidationExceptionConstants {

    public static final String NAME_NOT_BE_NULL = "이름은 필수항목입니다.";
    public static final String PHONE_NUMBER_NOT_BE_NULL = "핸드폰 번호는 필수항목입니다.";
    public static final String EMAIL_NOT_BE_NULL = "이메일은 필수 항목입니다.";
    public static final String EMAIL_NOT_VALID = "올바르지 않은 이메일입니다.";
    public static final String PHONE_NUMBER_NOT_VALID = "올바르지 않은 핸드폰 번호입니다.";
    public static final String PASSWORD_MUST_NOT_NULL = "패스워드는 필수항목입니다.";
    public static final String PASSWORD_LENGTH_NOT_VALID = "패스워드는 6~10자로 해 주세요.";
    public static final String PASSWORD_NOT_VALID = "영문 소문자, 대문자, 숫자 중 최소 두 가지 이상을 조합해주세요.";

    public static final String CONSIGNED_BOOK_NAME_NOT_BE_NULL = "도서명은 필수항목입니다.";
    public static final String CONSIGNED_BOOK_ISBN_NOT_BE_NULL = "ISBN은 필수항목입니다.";
    public static final String CONSIGNED_BOOK_ISBN_NOT_VALID = "ISBN 형식이 옳지 않습니다.";
    public static final String RENTAL_PRICE_MINIMUM = "대여 가격은 1,000원 이상 이어야합니다.";
    public static final String RENTAL_PRICE_NOT_BE_NULL = "대여 가격은 필수 항목입니다.";

};
