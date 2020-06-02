package com.yoa.service.impl;

import com.yoa.dao.PositionMapper;
import com.yoa.entity.Position;
import com.yoa.service.PositionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by ❤ on 2019/11/6.
 */
@Service("positionService")
public class PositionServiceImpl implements PositionService{

    @Resource
    private PositionMapper positionMapper;

    @Override
    public List<Position> findByCondition(Position position) throws Exception {
        return positionMapper.findByCondition(position);
    }




}
