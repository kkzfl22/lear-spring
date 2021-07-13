package org.liujun.learn.spring.service;

import org.liujun.learn.spring.dao.AccountDao;
import org.liujun.learn.spring.dao.Impl.SqlLiteJdbcOperatorImpl;
import org.liujun.learn.spring.factory.BeansFactory;
import org.liujun.learn.spring.pojo.Account;

/**
 * 执行转帖的服务
 *
 * @author liujun
 * @since 2021/7/5
 */
public class TransferMoneyService {


    /**
     * 构建一个帐户操作
     */
    private AccountDao accountDao;


    /**
     * 转帐操作
     *
     * @param userSrc    原始的用户信息
     * @param userTarget 转帖的目标用户
     * @param money      转帖的金额
     * @return
     */
    public boolean transfer(Account userSrc, Account userTarget, int money) {
        //1,获取原始帐户信息
        Account srcInfo = accountDao.getAccount(userSrc.getCardId());
        Account targetInfo = accountDao.getAccount(userTarget.getCardId());

        //执行转帐操作
        if (srcInfo.getMoney() < money) {
            throw new RuntimeException("当前帐户余额不足");
        }

        srcInfo.setMoney(-money);
        accountDao.updateAccountMoney(srcInfo);

        //人为制造异常让转账失败
        //  int rsp = money / 0;

        //目标帐号增加金额
        targetInfo.setMoney(money);
        accountDao.updateAccountMoney(targetInfo);

        return true;
    }

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }
}
