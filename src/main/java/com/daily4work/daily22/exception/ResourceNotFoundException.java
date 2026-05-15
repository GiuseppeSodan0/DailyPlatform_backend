package com.daily4work.daily22.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String resource, Integer id) {
        super(resource + " not found with id: " + id);
    }

    public ResourceNotFoundException(String resource, String field, String value) {
        super(resource + " not found with " + field + ": " + value);
    }
}
