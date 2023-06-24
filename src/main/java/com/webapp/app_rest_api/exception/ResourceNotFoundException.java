package com.webapp.app_rest_api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
//зробити один ексепшн для всіх
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{
    private String resourceName;
    private String fieldName;
    private String fieldValue;

    public ResourceNotFoundException(String resourceName, String fieldName, String fieldValue){
        super(String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    public ResourceNotFoundException(String resourceName1, String fieldName1, String fieldValue1, String resourceName2, String fieldName2, String fieldValue2){
        super(String.format("%s with %s : '%s' or %s with %s: '%s' not found", resourceName1, fieldName1, fieldValue1, resourceName2, fieldName2, fieldValue2));
        this.fieldName = resourceName1;
        this.fieldValue = fieldValue1;
    }
    public String getResourceName() {
        return resourceName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getFieldValue() {
        return fieldValue;
    }


}
