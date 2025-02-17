package com.example.test_task.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorDTO {

    private String description;

    public ErrorDTO(String description) {
        this.description = description;
    }
}