package org.garvk.product_service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ErrorResponseDTO {
    private String timeStamp;
    private int status;
    private String error;
    private String message;
    private String path;
    private String service;
}
