package com.trongdv.enterpise.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseData<T> implements Serializable {

    private int status;

    private String message;

    private T data;
}
