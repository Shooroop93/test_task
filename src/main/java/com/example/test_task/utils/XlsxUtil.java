package com.example.test_task.utils;

import com.example.test_task.exception.TestTaskException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class XlsxUtil {

    public static Map<Integer, List<String>> getSheetData(InputStream inputStream) throws TestTaskException {
        Map<Integer, List<String>> data = new HashMap<>();

        try (Workbook workbook = WorkbookFactory.create(inputStream)) {
            Sheet sheet = workbook.getSheetAt(0);
            int rowIndex = 0;

            for (Row row : sheet) {
                List<String> rowData = new ArrayList<>();
                for (Cell cell : row) {
                    if (Objects.requireNonNull(cell.getCellType()) == CellType.NUMERIC) {
                        rowData.add(String.valueOf(cell.getNumericCellValue()));
                    }
                }
                data.put(rowIndex, rowData);
                rowIndex++;
            }
        } catch (IOException e) {
            throw new TestTaskException("Проблема при парсинге файла");
        }
        return data;
    }

    public static List<Integer> getArrayAllNumbers(Map<Integer, List<String>> sheetData) {
        return sheetData.values().stream()
                .flatMap(List::stream)
                .map(s -> (int) Double.parseDouble(s))
                .collect(Collectors.toList());
    }
}