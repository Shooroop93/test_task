package com.example.test_task.controller;

import com.example.test_task.dto.ErrorDTO;
import com.example.test_task.dto.TestTaskResponseDTO;
import com.example.test_task.service.MainService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class MainController {

    private final MainService mainService;

    @PostMapping("/getMaxNumber")
    public ResponseEntity<TestTaskResponseDTO> testTaskController(@RequestParam("file") MultipartFile file,
                                                                  @RequestParam("serial_number") Integer serialNumber) {
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