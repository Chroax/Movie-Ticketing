package com.binar.kampusmerdeka.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageModel
{
    private Integer status;
    private String message;
    private Object data;
}
