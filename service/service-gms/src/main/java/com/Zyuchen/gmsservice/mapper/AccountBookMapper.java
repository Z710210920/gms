package com.Zyuchen.gmsservice.mapper;

import com.Zyuchen.gmsservice.entity.AccountBook;
import com.Zyuchen.gmsservice.entity.vo.AccountBookVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 账簿 Mapper 接口
 * </p>
 *
 * @author Zyuchen
 * @since 2021-05-11
 */
public interface AccountBookMapper extends BaseMapper<AccountBook> {

    Page<AccountBookVO> VOpageByReserve(Page<AccountBookVO> page, @Param(Constants.WRAPPER) QueryWrapper<AccountBookVO> wrapper);

    Page<AccountBookVO> VOpageByClassSelection(Page<AccountBookVO> page, @Param(Constants.WRAPPER) QueryWrapper<AccountBookVO> wrapper);
}
