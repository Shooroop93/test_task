package com.example.test_task.controller;

import com.example.test_task.dto.ErrorDTO;
import com.example.test_task.dto.TestTaskResponseDTO;
import com.example.test_task.service.MainService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
@Tag(name = "Test Task", description = "API для поиска максимального числа в XLSX файле")
public class MainController {

    private final MainService mainService;

    @Operation(
            summary = "Получение максимального числа из XLSX файла",
            description = "Принимает файл XLSX и опциональный параметр serial_number. Если параметр не передан, по умолчанию используется 0."
    )
    @PostMapping(value = "/getMaxNumber", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<TestTaskResponseDTO> testTaskController(
            @Parameter(description = "Файл XLSX", required = true, content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA_VALUE, schema = @Schema(type = "string", format = "binary")))
            @RequestParam("file") MultipartFile file,
            @Parameter(description = "Индекс числа (необязательный, по умолчанию 0)")
            @RequestParam(name = "serial_number", required = false, defaultValue = "0")
            Integer serialNumber) {
        if (file == null || file.isEmpty()) {
            TestTaskResponseDTO response = TestTaskResponseDTO.builder()
                    .error(new ErrorDTO("Файл не передан или пуст"))
                    .build();
            return ResponseEntity.status(response.getStatusCode()).body(response);
        }

        TestTaskResponseDTO response = mainService.getMaxNumberForXlsxFile(file, serialNumber);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
}