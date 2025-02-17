package com.example.test_task.service;

import com.example.test_task.dto.ErrorDTO;
import com.example.test_task.dto.TestTaskResponseDTO;
import com.example.test_task.exception.TestTaskException;
import com.example.test_task.utils.FileUtil;
import com.example.test_task.utils.SortingUtil;
import com.example.test_task.utils.XlsxUtil;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@Service
public class MainService {

    public TestTaskResponseDTO getMaxNumber(MultipartFile file, Integer serialNumber) {
        TestTaskResponseDTO response = TestTaskResponseDTO.builder().build();

        if (!FileUtil.checkFileType(file, ".xlsx")) {
            response.setStatusCode(HttpStatus.BAD_REQUEST.value());
            response.setError(new ErrorDTO("Передан некорректный тип файла: .xlsx"));
            return response;
        }

        try {
            int[] allNumberForXlsx = XlsxUtil.getAllNumberForXlsx(file);
            int[] sortingNumbers = SortingUtil.sortingArrayNumber(allNumberForXlsx);

            if (Objects.isNull(serialNumber)) {
                int position = 0;
                response.setPositionNumber(position);
                response.setMaximumNumber(sortingNumbers[position]);
            } else {
                response.setPositionNumber(serialNumber);
                response.setMaximumNumber(sortingNumbers[serialNumber]);
            }
        } catch (TestTaskException e) {
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setError(new ErrorDTO(e.getMessage()));
        }
        return response;
    }
}