package org.swmaestro.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.swmaestro.demo.response.ErrorResponse;
import org.swmaestro.demo.response.SuccessResponse;

import java.util.List;
import java.util.Map;

/**
 * BaseRestController
 *
 * @since	2022-06-29
 * @author	ywkim
 */
@Slf4j
public class BaseRestController {

    protected ResponseEntity<?> getResponse(HttpStatus httpStatus, Object result, int size) {
        if (size >= 0)
            log.info("httpStatus={}, result={}", httpStatus, (result != null) ? result.toString() : "null");
        else
            log.info("httpStatus={}, result={}", httpStatus, size);

        return new ResponseEntity<>(result, httpStatus);
    }

    protected ResponseEntity<?> getSuccessResponse(HttpStatus httpStatus, Object result, int size) {
        SuccessResponse response = new SuccessResponse();
        response.setData(result);
        return getResponse(httpStatus, response, size);
    }

    protected ResponseEntity<?> getOkResponse(Object result) {
        return getSuccessResponse(HttpStatus.OK, result, -1);
    }

    protected ResponseEntity<?> getOkResponse(Map resultMap) {
        return getSuccessResponse(HttpStatus.OK, resultMap, resultMap.size());
    }

    protected ResponseEntity<?> getOkListResponse(List resultList) {
        int size = 0;
        if (resultList != null)
            size = resultList.size();

        return getSuccessResponse(HttpStatus.OK, resultList, size);
    }

    protected ResponseEntity<?> getCreatedResponse(Object result) {
        return getSuccessResponse(HttpStatus.CREATED, result, -1);
    }

    protected ResponseEntity<?> getErrorResponse(String errorMessage) {
        log.error(errorMessage);
        ErrorResponse response = new ErrorResponse();
        response.setMessage(errorMessage);
        return getResponse(HttpStatus.BAD_REQUEST, response, -1);
    }


}
