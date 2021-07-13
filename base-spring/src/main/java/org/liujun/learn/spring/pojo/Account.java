package org.liujun.learn.spring.pojo;

/**
 * @author liujun
 * @version 0.0.1
 */
public class Account {

    /**
     * 卡号
     */
    private Long cardId;


    /**
     * 帐户名
     */
    private String name;


    /**
     * 金额
     */
    private int money;


    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Account{");
        sb.append("cardId='").append(cardId).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", money=").append(money);
        sb.append('}');
        return sb.toString();
    }
}