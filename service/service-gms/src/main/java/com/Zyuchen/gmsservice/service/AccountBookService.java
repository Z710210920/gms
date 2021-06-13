package com.Zyuchen.gmsservice.service;

import com.Zyuchen.gmsservice.entity.AccountBook;
import com.Zyuchen.gmsservice.entity.vo.AccountBookVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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

    String deductions(String userId, BigDecimal cost, int type);

    Page<AccountBookVO> VOpageByReserve(Page<AccountBookVO> page, QueryWrapper<AccountBookVO> wrapper);

    Page<AccountBookVO> VOpageByClassSelection(Page<AccountBookVO> page, QueryWrapper<AccountBookVO> wrapper);
}
