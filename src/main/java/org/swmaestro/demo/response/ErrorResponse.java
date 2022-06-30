package org.swmaestro.demo.response;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * Error Response
 *
 * @since	2022-06-29
 * @author	ywkim
 */
@Slf4j
@Data
public class ErrorResponse {
    private String message;
}
