package com.gscore.gscore.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseDTO {
    private Object data;
    private String message;
    private String detail;
}
