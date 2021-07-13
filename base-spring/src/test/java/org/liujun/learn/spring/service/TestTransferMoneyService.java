package org.liujun.learn.spring.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.liujun.learn.spring.pojo.Account;

/**
 * 测试转帐服务
 *
 * @author liujun
 * @since 2021/7/5
 */
public class TestTransferMoneyService {


    /**
     * 转换服务
     */
    private TransferMoneyService moneyService = new TransferMoneyService();


    @Test
    public void transfer() {
        boolean transferRsp = moneyService.transfer(src(), target(), 100);
        Assertions.assertEquals(true, transferRsp);

        boolean transferRsp2 = moneyService.transfer(target(), src(), 100);
        Assertions.assertEquals(true, transferRsp2);

    }

    private Account src() {
        Account account = new Account();
        account.setCardId(88888888L);
        account.setName("liufei");
        return account;
    }

    private Account target() {
        Account target = new Account();
        target.setCardId(66666666L);
        target.setName("feifei");
        return target;
    }


}
