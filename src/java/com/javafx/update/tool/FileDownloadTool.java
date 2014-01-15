/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javafx.update.tool;

import com.javafx.update.model.FileData;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Richard
 */
public class FileDownloadTool {

    public static FileData getFileData(String filePath, String localFolder, String projectRealPath) {
        long fileSize = 0;
        String fileName = "";
        String fileAbsPath;

        fileAbsPath = projectRealPath + filePath;
        FileData fileData = new FileData();

        fileSize = getFileSzie(fileAbsPath);
        if (fileSize == 0) {
            fileData.setIsNull(true);
        } else {
            fileData.setIsNull(false);
        }
        fileName = filePath.substring(filePath.lastIndexOf("/") + 1, filePath.lastIndexOf("."));

        fileData.setFileName(fileName);
        fileData.setFileSize(fileSize);
        fileData.setFilePath(filePath);
        fileData.setlocalFolder(localFolder);
        fileData.setExtension(filePath.substring(filePath.lastIndexOf(".")));

        return fileData;
    }

    public static long getAllFileSize(ArrayList<FileData> versions) {
        long size = 0;
        for (FileData file : versions) {
            size = size + file.getFileSize();
        }
        return size;
    }

    public static HashMap<String, Object> putParamsIntoMap(ArrayList<FileData> files, long allFileSize) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("files", files);
        map.put("allFileSize", allFileSize + "");//长整型无法取出来，这里传字符串
        return map;
    }

    public static long getFileSzie(String filePath) {
        File file = new File(filePath);
        long size;
        if (file.exists()) {
            size = file.length();
        } else {
            return 0;
        }
        return size;
    }
}
