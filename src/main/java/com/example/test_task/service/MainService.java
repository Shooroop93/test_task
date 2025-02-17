package com.example.test_task.service;

import com.example.test_task.dto.ErrorDTO;
import com.example.test_task.dto.TestTaskResponseDTO;
import com.example.test_task.utils.FileUtil;
import com.example.test_task.utils.SortingUtil;
import com.example.test_task.utils.XlsxUtil;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class MainService {

    public TestTaskResponseDTO getMaxNumberForXlsxFile(MultipartFile file, Integer serialNumber) {
        TestTaskResponseDTO response = TestTaskResponseDTO.builder().build();

        if (!FileUtil.checkFileType(file, ".xlsx")) {
            response.setStatusCode(HttpStatus.BAD_REQUEST.value());
            response.setError(new ErrorDTO("Передан некорректный тип файла: .xlsx"));
            return response;
        }

        try {
            Map<Integer, List<String>> sheetData = XlsxUtil.getSheetData(file.getInputStream());
            List<Integer> arrayAllNumbers = XlsxUtil.getArrayAllNumbers(sheetData);
            List<Integer> sortingNumbers = SortingUtil.sortingArrayNumber(arrayAllNumbers);

            if (Objects.isNull(serialNumber)) {
                int position = 0;
                response.setPositionNumber(position);
                response.setMaximumNumber(sortingNumbers.get(position));
            } else {
                response.setPositionNumber(serialNumber);
                response.setMaximumNumber(sortingNumbers.get(serialNumber));
            }

            response.setStatusCode(HttpStatus.OK.value());
        } catch (Exception e) {
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setError(new ErrorDTO(e.getMessage()));
        }
        return response;
    }
}