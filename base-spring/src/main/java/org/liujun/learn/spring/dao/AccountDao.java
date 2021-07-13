package org.liujun.learn.spring.dao;


import org.liujun.learn.spring.pojo.Account;

/**
 * 银行帐户操作
 *
 * @author liujun
 * @version 0.0.1
 */
public interface AccountDao {


    /**
     * 添加帐户信息
     *
     * @param account 帐户信息
     * @return 添加结果
     */
    int addAccount(Account account);


    /**
     * 获取帐户信息
     *
     * @param carId 帐户的id
     * @return 查询的帐户查询的结果
     */
    Account getAccount(Long carId);


    /**
     * 进行帐户的修改操作
     *
     * @param account 金额操作
     * @return 转帖的结果
     */
    int updateAccountMoney(Account account);

    /**
     * 删除帐户信息
     *
     * @param account
     * @return
     */
    int deleteAccount(Account account);

}
