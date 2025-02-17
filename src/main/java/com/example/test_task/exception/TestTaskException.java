package com.example.test_task.exception;

public class TestTaskException extends Exception {

    public TestTaskException() {
    }

    public TestTaskException(String message) {
        super(message);
    }

    public TestTaskException(String message, Throwable cause) {
        super(message, cause);
    }

    public TestTaskException(Throwable cause) {
        super(cause);
    }
}