package com.Zyuchen.gmsservice.mapper;

import com.Zyuchen.gmsservice.entity.Reserve;
import com.Zyuchen.gmsservice.entity.vo.ReserveQuery;
import com.Zyuchen.gmsservice.entity.vo.ReserveVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 预约表 Mapper 接口
 * </p>
 *
 * @author Zyuchen
 * @since 2021-05-27
 */
public interface ReserveMapper extends BaseMapper<Reserve> {
    List<ReserveVO> selectReserveVo(@Param(Constants.WRAPPER) QueryWrapper<ReserveVO> wrapper);
}
