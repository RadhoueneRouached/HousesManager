package com.uim.exceptions;

import lombok.Builder;
import lombok.Data;

/**
 * @author Radhouene Rouached
 */
@Data
public class HouseException extends Exception {
    private String errorMessage;

    public HouseException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }
}