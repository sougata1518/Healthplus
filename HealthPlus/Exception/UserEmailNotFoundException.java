package com.Api.HealthPlus.Exception;

import lombok.Data;

@Data
public class UserEmailNotFoundException extends RuntimeException {

    String resourceName;
    String fieldName;
    String fieldValue;

    public UserEmailNotFoundException(String resourceName,String fieldName,String fieldValue){
        super(String.format("%s not found with %s : %s",resourceName,fieldName,fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
