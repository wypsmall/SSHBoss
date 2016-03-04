package com.neo.account.service;

import org.springframework.stereotype.Service;
import org.springframework.web.context.support.WebApplicationContextUtils;

import java.util.List;

/**
 * Created by neowyp on 2016/3/4.
 */
@Service("accountService")
public class AccountServiceImpl implements IAccountService {

    public List getAccountList() {
        System.out.println("===========getAccountList");
        return null;
    }
}
