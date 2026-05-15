package com.daily4work.daily22.exception;

public class DuplicateRoleException extends RuntimeException {

    public DuplicateRoleException(String name) {
        super("Role already exists: " + name);
    }
}
