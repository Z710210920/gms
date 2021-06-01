package com.Zyuchen.gmsservice.service;

import com.Zyuchen.gmsservice.entity.Reserve;
import com.Zyuchen.gmsservice.entity.vo.ReserveQuery;
import com.Zyuchen.gmsservice.entity.vo.ReserveVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 预约表 服务类
 * </p>
 *
 * @author Zyuchen
 * @since 2021-05-27
 */
public interface ReserveService extends IService<Reserve> {

    List<ReserveVO> selectReserveVo(QueryWrapper<ReserveVO> wrapper);
}
