package com.uim.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Radhouene Rouached
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Response {
    private int status;
    private String message;
}
