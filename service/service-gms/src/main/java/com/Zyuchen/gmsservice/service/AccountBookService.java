package com.Zyuchen.gmsservice.service;

import com.Zyuchen.gmsservice.entity.AccountBook;
import com.baomidou.mybatisplus.extension.service.IService;

import java.math.BigDecimal;

/**
 * <p>
 * 账簿 服务类
 * </p>
 *
 * @author Zyuchen
 * @since 2021-05-11
 */
public interface AccountBookService extends IService<AccountBook> {

    void deductions(String userId, BigDecimal cost, int type);
}
