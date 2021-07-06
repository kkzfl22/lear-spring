/*
 * Copyright (C), 2008-2021, Paraview All Rights Reserved.
 */
package org.liujun.learn.spring.controller;

import org.liujun.learn.spring.pojo.Account;
import org.liujun.learn.spring.pojo.DataResult;
import org.liujun.learn.spring.service.TransferMoneyService;
import org.liujun.learn.spring.utils.JsonUtils;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 转账服务的控制层
 *
 * @author liujun
 * @since 2021/7/5
 */
public class TransferController extends HttpServlet {

    private TransferMoneyService transferMoneyInstance = new TransferMoneyService();


    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws IOException {
        req.setCharacterEncoding(StandardCharsets.UTF_8.displayName());

        Account src = new Account();
        src.setCardId(Long.parseLong(req.getParameter("srcCardId")));
        src.setName(req.getParameter("srcName"));

        Account target = new Account();
        target.setCardId(Long.parseLong(req.getParameter("targetCardId")));
        target.setName(req.getParameter("targetName"));

        int transferMoney = Integer.parseInt(req.getParameter("money"));
        boolean transferRsp = transferMoneyInstance.transfer(src, target, transferMoney);


        DataResult result = null;

        if (transferRsp) {
            result = DataResult.success();
            res.setStatus(HttpServletResponse.SC_OK);
        } else {
            result = DataResult.fail();
            res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }


        //结果的响应操作
        res.setCharacterEncoding(StandardCharsets.UTF_8.displayName());
        res.setContentType("application/json;charset=utf-8");
        res.getWriter().write(JsonUtils.toJson(result));
    }
}
