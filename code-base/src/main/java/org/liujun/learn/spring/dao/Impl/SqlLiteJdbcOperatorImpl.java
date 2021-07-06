package org.liujun.learn.spring.dao.Impl;

import org.liujun.learn.spring.dao.AccountDao;
import org.liujun.learn.spring.pojo.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 使用SQLlite数据库过时行数据操作
 *
 * @author liujun
 * @version 0.0.1
 */
public class SqlLiteJdbcOperatorImpl implements AccountDao {


    /**
     * jdbc驱动
     */
    private static final String SQL_LITE_JDBC = "org.sqlite.JDBC";


    /**
     * sqlite库的信息
     */
    private static final String URL = "jdbc:sqlite:D:\\java\\workspace\\learnwork\\spring\\learn-spring\\code-base\\account.db";


    /**
     * 用户名
     */
    private static final String USER_NAME = "";


    /**
     * 密码
     */
    private static final String PASSWORD = "";


    /**
     * 执行数据检查
     */
    private static final String QUERY_SQL = "select card_id,name,money from account where card_id = ? ";
    /**
     * 转帐的SQL
     */
    private static final String TRANSFER_MONEY = "update account set money = money + ? where card_id = ?  and name  = ? ";

    /**
     * 添加帐户信息
     */
    private static final String ADD_ACCOUNT = "insert into account(card_id,name,money) values(?,?,?) ";


    /**
     * 执行帐户的删除操作
     */
    private static final String DEL_ACCOUNT = "delete from account where card_id = ? and name = ?";


    static {
        try {
            Class.forName(SQL_LITE_JDBC);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    @Override
    public Account getAccount(Long cardId) {
        Account account = null;
        ResultSet rs = null;
        try (Connection connection = JdbcConnection.getConn(URL, USER_NAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(QUERY_SQL);
        ) {
            statement.setLong(1, cardId);
            rs = statement.executeQuery();
            while (rs.next()) {
                account = new Account();
                account.setCardId(rs.getLong("card_id"));
                account.setName(rs.getString("name"));
                account.setMoney(rs.getInt("money"));
                break;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (null != rs) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return account;
    }

    @Override
    public int updateAccountMoney(Account account) {
        int rowNum = 0;
        try (Connection connection = JdbcConnection.getConn(URL, USER_NAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(TRANSFER_MONEY);
        ) {
            statement.setInt(1, account.getMoney());
            statement.setLong(2, account.getCardId());
            statement.setString(3, account.getName());

            rowNum = statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return rowNum;
    }

    @Override
    public int addAccount(Account account) {
        int rowNum = 0;
        try (Connection connection = JdbcConnection.getConn(URL, USER_NAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(ADD_ACCOUNT);
        ) {
            statement.setLong(1, account.getCardId());
            statement.setString(2, account.getName());
            statement.setInt(3, account.getMoney());
            rowNum = statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return rowNum;
    }

    @Override
    public int deleteAccount(Account account) {
        int rowNum = 0;
        try (Connection connection = JdbcConnection.getConn(URL, USER_NAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(DEL_ACCOUNT);
        ) {
            statement.setLong(1, account.getCardId());
            statement.setString(2, account.getName());
            rowNum = statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return rowNum;
    }
}
