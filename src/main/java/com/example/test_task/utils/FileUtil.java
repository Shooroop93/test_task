package com.example.test_task.utils;

import org.springframework.web.multipart.MultipartFile;

public class FileUtil {

    public static boolean checkFileType(MultipartFile file, String endWith) {
        String fileName = file.getOriginalFilename();
        if (fileName != null) {
            return fileName.toLowerCase().endsWith(endWith);
        }
        return false;
    }
}