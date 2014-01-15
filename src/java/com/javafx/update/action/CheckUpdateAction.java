/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javafx.update.action;

import com.javafx.update.db.UpdateDB;
import java.util.HashMap;


public class CheckUpdateAction  {
    
    public void setParams(HashMap<String, Object> params) {
        localVersion = (String) params.get("localVersion");
    }

    public HashMap<String, String> process() {
        HashMap<String, String> result = new HashMap<String, String>();
System.out.println("result : ====" + UpdateDB.isUpdate(localVersion));
        result.put("isUpdateStr", UpdateDB.isUpdate(localVersion));

        return result;

    }
    private String localVersion;
    
}
