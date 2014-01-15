/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javafx.update.db;

import com.javafx.update.action.CheckUpdateAction;
import com.javafx.update.model.FileData;
import com.javafx.update.tool.DBTool;
import com.javafx.update.tool.FileDownloadTool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @HuaLun -- Richard
 */
public class UpdateDB {
    
    private static Connection con;

    public static ArrayList<FileData> getUpdateVersions( String localVersion,
            String localFoder, String projectRealPath) {
        con = DBTool.getConnection();
        ArrayList<FileData> files = new ArrayList<FileData>();
        String sql = "SELECT VERSION, PATH FROM versions "
                + "WHERE str_to_date(DATETIME,'%Y-%m-%d %H:%i:%s') > "
                + "(SELECT DATETIME FROM versions where VERSION = ?)";

        PreparedStatement pstmt = null;
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, localVersion);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                FileData file = new FileData();
                String fileRelPath = rs.getString("PATH");
                file = FileDownloadTool.getFileData(fileRelPath, localFoder, projectRealPath);
                files.add(file);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UpdateDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(UpdateDB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return files;
    }

    public static String isUpdate( String localVersion) {
        con = DBTool.getConnection();
        String updateStr = "false";
        String sql = "SELECT VERSION FROM versions "
                + "WHERE str_to_date(DATETIME,'%Y-%m-%d %H:%i:%s') > "
                + "(SELECT DATETIME FROM versions where VERSION = '" + localVersion + "')";
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                updateStr = "true";
            }

        } catch (SQLException ex) {
            Logger.getLogger(CheckUpdateAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return updateStr;
    }
}
