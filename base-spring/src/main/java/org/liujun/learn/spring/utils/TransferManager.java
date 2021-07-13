/*
 * Copyright (C), 2008-2021, Paraview All Rights Reserved.
 */
package org.liujun.learn.spring.utils;

import java.sql.SQLException;

/**
 * 转帖的事务控制
 *
 * @author liujun
 * @since 2021/7/13
 */
public class TransferManager {


    /**
     * 执行数据库的连接处理
     */
    private JdbcConnection jdbcConnection;


    /**
     * 开始事务
     *
     * @throws SQLException
     */
    public void beginTransfer() throws SQLException {
        jdbcConnection.getConn().setAutoCommit(false);
    }

    /**
     * 提交事务
     *
     * @throws SQLException
     */
    public void commit() throws SQLException {
        jdbcConnection.getConn().commit();
    }

    /**
     * 回滚事务
     *
     * @throws SQLException
     */
    public void rollback() throws SQLException {
        jdbcConnection.getConn().rollback();
    }


    public void setJdbcConnection(JdbcConnection jdbcConnection) {
        this.jdbcConnection = jdbcConnection;
    }
}
