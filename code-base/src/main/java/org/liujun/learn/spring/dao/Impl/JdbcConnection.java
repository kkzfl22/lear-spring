package org.liujun.learn.spring.dao.Impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 执行jdbc数据库操作
 *
 * @author liujun
 * @version 0.0.1
 */
public class JdbcConnection {


    public static Connection getConn(String url, String userName, String password) throws SQLException {
        Connection connection = DriverManager.getConnection(url, userName, password);
        return connection;
    }

}
