/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javafx.update.action;

import com.javafx.update.db.UpdateDB;
import com.javafx.update.model.FileData;
import java.util.ArrayList;
import java.util.HashMap;

public class UpdateFileAction {

    private static HashMap<String, Object> params;
    private static long allFileSize;

    public void setParams(HashMap<String, Object> param) {
        params = param;
    }

    public HashMap<String, Object> process() {
        System.out.println("params === " + params.toString());
        String localFolder = (String) params.get("localFolder");
        String localVersion = (String) params.get("localVersion");
        String projectRealPath = (String) params.get("projectRealPath");
        ArrayList<FileData> versions = UpdateDB.getUpdateVersions(localVersion, localFolder, projectRealPath);
        allFileSize = getAllFileSize(versions);
        FileData lastVersion = versions.get(versions.size() - 1);
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("files", versions);
        hashMap.put("allFileSize", allFileSize + "");
        hashMap.put("lastVersion", lastVersion.getFileName());
System.out.println("hashMap ========== " + hashMap + " ||| path ================== " + versions.get(0).getFilePath());
        return hashMap;
    }

    private static long getAllFileSize(ArrayList<FileData> versions) {
        long size = 0;
        for (FileData file : versions) {
            size = size + file.getFileSize();
        }
        return size;
    }
}
