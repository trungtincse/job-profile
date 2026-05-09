package com.tindpt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DatabaseController {
    private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DB_CONNECTION = "jdbc:h2:./database";
    private static final String DB_USER = "";
    private static final String DB_PASSWORD = "";
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
class MyInterator{
	ArrayList<String> list=new ArrayList<String>();
	int current=0;
	public String getFirst() {
		if(list.size()<=0) return null;
		return list.get(0);
	}
	public String next() {
		if(list.size()<=0) return null;
		if(current+1<list.size()) {
			current+=1;
			return list.get(current);
		}
		else {
			return list.get(current);
		}
	}
	public String previous() {
		if(list.size()<=0) return null;
		if(current-1>=0) {
			current-=1;
			return list.get(current);
		}
		else {
			return list.get(current);
		}
	}
	public boolean canNext() {
		if(current+1<list.size())
			return true;
		else return false;
	}
	public boolean canBack() {
		if(current-1>=0)
			return true;
		else return false;
	}
}