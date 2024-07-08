package com.Api.HealthPlus.Exception;

import lombok.Data;

@Data
public class ResourseAlreadyExit extends RuntimeException{

    String fieldName;
    String fieldValue;

    public ResourseAlreadyExit(String fieldName,String fieldValue){
        super(String.format("This %s is already exit = %s",fieldName,fieldValue));
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
