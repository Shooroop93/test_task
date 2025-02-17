package com.example.test_task.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class TestTaskResponseDTO {

    @JsonProperty("position_number")
    private int positionNumber;
    @JsonProperty("number")
    private int maximumNumber;
    private int statusCode;
    private ErrorDTO error;
}