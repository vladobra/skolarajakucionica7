package com.skolarajak.dao.datasource;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.skolarajak.utils.DBUtils;

public class C3poDataSource {
	private static ComboPooledDataSource cpds = new ComboPooledDataSource();
	 
    static {
        try {
            cpds.setDriverClass(DBUtils.myDriver);
            cpds.setJdbcUrl(DBUtils.myUrl);
            cpds.setUser("root");
            cpds.setPassword("root");
            cpds.setInitialPoolSize(10);
            cpds.setMaxPoolSize(120);
            
        } catch (PropertyVetoException e) {
            // handle the exception
        }
    }
     
    public static Connection getConnection() throws SQLException {
        return cpds.getConnection();
    }
     
    private C3poDataSource(){}
}
