package com.tindpt;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class H2DBExample {
    private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DB_CONNECTION = "jdbc:h2:./database";
    private static final String DB_USER = "";
    private static final String DB_PASSWORD = "";
    public static void main(String[] abc) throws IOException, SQLException {
    	File file=new File("./data");
    	listFilesForFolder(file);
    }
    public static void listFilesForFolder(final File folder) throws IOException, SQLException {
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolder(fileEntry);
            } else {
            	RandomAccessFile rAccessFile=new RandomAccessFile(fileEntry, "r");
            	rAccessFile.readLine();
            	String line = new String(rAccessFile.readLine().trim().getBytes("ISO-8859-1"), "UTF-8");
            	String[] split=line.split(",");
            	for(int i=0;i<split.length;i++)
            		if(split[i].equals("\" \"")) split[i]="";
            	rAccessFile.close();
            	
//                System.out.println(String.format("%s,%s", split[0],split[1]));
            	insertWithStatement(String.format("%s,%s", split[0],split[1]), String.join(",", split));
            }
        }
    }
    private static Connection getDBConnection() {
    	Connection dbConnection=null;
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER,
                    DB_PASSWORD);
            return dbConnection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return dbConnection;
    }
    public static void insertWithStatement(String key,String value) throws SQLException {
        Statement stmt = null;
        Connection dbConnection=getDBConnection();
        try {
        	dbConnection.setAutoCommit(false);
            stmt = dbConnection.createStatement();
            stmt.execute(String.format("DELETE FROM PROFILE WHERE KEY = '%s'", key));
            stmt.execute(String.format("INSERT INTO PROFILE VALUES('%s', '%s')", key,value));
            stmt.close();
            dbConnection.commit();
        } catch (SQLException e) {
            System.out.println("Exception Message " + e.getLocalizedMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	dbConnection.close();
        }
    }
    public static MyInterator selectWithStatement(int reverse,int limit) throws SQLException {
        Statement stmt = null;
        MyInterator it= new MyInterator();
        ResultSet rs=null;
        Connection dbConnection=getDBConnection();
        try {
        	dbConnection.setAutoCommit(false);
            stmt = dbConnection.createStatement();
            rs = stmt.executeQuery(String.format("select * from PROFILE ORDER BY '%d' LIMIT '%d'",reverse,limit));
            while (rs.next()) {
            	it.list.add(rs.getString("value"));
			}
            it.current=it.list.size();//update current pointer
            stmt.close();
            dbConnection.commit();
        } catch (SQLException e) {
            System.out.println("Exception Message " + e.getLocalizedMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	dbConnection.close();
        	return it;
        }
    }
    public static MyInterator findWithStatement(String key) throws SQLException {
        Statement stmt = null;
        MyInterator it= new MyInterator();
        Connection dbConnection=getDBConnection();
        ResultSet rs=null;
        try {
        	dbConnection.setAutoCommit(false);
            stmt = dbConnection.createStatement();
            rs = stmt.executeQuery(String.format("select * from PROFILE WHERE key='%s'", key)); // ket qua nguoc
            while (rs.next()) {
            	it.list.add(rs.getString("value")); // ket qua thuan
			}
            it.current=it.list.size();//update current pointer
            stmt.close();
            dbConnection.commit();
        } catch (SQLException e) {
            System.out.println("Exception Message " + e.getLocalizedMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	dbConnection.close();
        	return it;
        }
    }

}
