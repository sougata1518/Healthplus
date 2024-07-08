package com.Api.HealthPlus.Exception;

import lombok.Data;

@Data
public class ResourseNotFoundException extends RuntimeException{

    String resourceName;
    String fieldName;
    int fieldValue;

    public ResourseNotFoundException(String resourceName,String fieldName,int fieldValue){
        super(String.format("%s not found with %s : %d",resourceName,fieldName,fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
