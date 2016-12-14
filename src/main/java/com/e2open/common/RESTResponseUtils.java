package com.e2open.common;

import com.e2open.common.jaxb.ErrorMessage;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by abhishek on 5/8/16.
 */
public class RESTResponseUtils {

    public static final String ALREADY_EXIST = "Already exist.";

    public static final Long NOTHING_DATA_AVAILABLE = 1000L;
    public static final Long NO_USER_AVAILABLE = 1001L;
    public static final Long USER_EMAIL_EMPTY = 1002L;
    public static final Long SOMETHING_WENT_WRONG = 1003L;
    public static final Long COUNTRY_FIELD_EMPTY = 1004L;
    public static final Long MATCHING_DATA_ALREADY_EXIST = 1005L;
    public static final Long CODE_ACCESS_TOKEN_MISSING = 0xB00002L;




    public static final String ACCESS_TOKEN_MISSING = "The accessToken is missing";
    public static final String NO_USER_AVAILABLE_MSG = "No User available";
    public static final String NOTHING_DATA_AVAILABLE_MSG = "Nothing to save";
    public static final String USER_EMAIL_EMPTY_MSG ="User with empty emailId";
    public static final String SOMETHING_WENT_WRONG_MSG = "Internal server error happened";
    public static final String COUNTRY_FIELD_EMPTY_MSG = "Country field is empty";
    public static final String MATCHING_DATA_ALREADY_EXIST_MSG = "Matching data already exist";


    public static Response getErrorResponse(Integer status, String errorCode, String errorMessage) {
        ErrorMessage temp = new ErrorMessage();
        temp.setErrorCode(errorCode);
        temp.setErrorMessage(errorMessage);
        return Response.status(status).entity(temp).
                type(MediaType.APPLICATION_JSON_TYPE).build();
    }

    public static Response getErrorResponse(Integer status, Long errorCode, String errorMessage) {
        return getErrorResponse(status,"0x"+Long.toHexString(errorCode).toUpperCase(),errorMessage);
    }
}
