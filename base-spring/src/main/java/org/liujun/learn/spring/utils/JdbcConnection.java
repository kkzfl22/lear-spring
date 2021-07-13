package org.liujun.learn.spring.utils;

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


    /**
     * jdbc驱动
     */
    private static final String SQL_LITE_JDBC = "org.sqlite.JDBC";


    /**
     * sqlite库的信息
     */
    private static final String URL = "jdbc:sqlite:account.db";


    /**
     * 用户名
     */
    private static final String USER_NAME = "";


    /**
     * 密码
     */
    private static final String PASSWORD = "";


    /**
     * 线程绑定对象
     */
    private ThreadLocal<Connection> threadLocal = new ThreadLocal<>();


    static {
        try {
            Class.forName(SQL_LITE_JDBC);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建新的连接操作
     *
     * @return
     */
    private Connection newConn() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }


    /**
     * 获取连接
     *
     * @return
     */
    public Connection getConn() {
        Connection connectInfo = threadLocal.get();
        if (connectInfo == null) {
            connectInfo = newConn();
            threadLocal.set(connectInfo);
        }

        return connectInfo;
    }

}
