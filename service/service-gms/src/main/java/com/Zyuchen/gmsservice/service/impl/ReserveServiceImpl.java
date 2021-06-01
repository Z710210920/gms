package com.Zyuchen.gmsservice.service.impl;

import com.Zyuchen.gmsservice.entity.Reserve;
import com.Zyuchen.gmsservice.entity.vo.ReserveQuery;
import com.Zyuchen.gmsservice.entity.vo.ReserveVO;
import com.Zyuchen.gmsservice.mapper.ReserveMapper;
import com.Zyuchen.gmsservice.service.ReserveService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 预约表 服务实现类
 * </p>
 *
 * @author Zyuchen
 * @since 2021-05-27
 */
@Service
public class ReserveServiceImpl extends ServiceImpl<ReserveMapper, Reserve> implements ReserveService {

    @Override
    public List<ReserveVO> selectReserveVo(QueryWrapper<ReserveVO> wrapper) {
        return baseMapper.selectReserveVo(wrapper);
    }
}
