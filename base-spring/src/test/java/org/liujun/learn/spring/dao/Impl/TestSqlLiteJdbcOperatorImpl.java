package org.liujun.learn.spring.dao.Impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.liujun.learn.spring.pojo.Account;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

/**
 * 数据库操作
 *
 * @author liujun
 * @version 0.0.1
 */
public class TestSqlLiteJdbcOperatorImpl {


    /**
     * 测试实例信息
     */
    private SqlLiteJdbcOperatorImpl instance = new SqlLiteJdbcOperatorImpl();


    /**
     * 测试转帐的一个完整的流程
     */
    @Test
    public void testQuery() {
        Account account = getAccount();
        instance.addAccount(account);
        try {
            account.setMoney(-100);
            instance.updateAccountMoney(account);
            Account accInfo = instance.getAccount(account.getCardId());
            Assertions.assertEquals(accInfo.getMoney(), 9900);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //最后的清理工作
            instance.deleteAccount(account);
        }

    }

    /**
     * 构建的新的帐户信息
     *
     * @return 帐户信息
     */
    private Account getAccount() {
        Account accInfo = new Account();
        accInfo.setCardId(RandomUtils.nextLong());
        accInfo.setName(RandomStringUtils.randomAlphabetic(5));
        accInfo.setMoney(10000);
        return accInfo;
    }

}
