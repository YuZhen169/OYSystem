package com.yoa.service;

import com.yoa.entity.Position;

import java.util.List;

/**
 * Created by ❤ on 2019/11/6.
 */
public interface PositionService {

    public List<Position> findByCondition(Position position)throws Exception;

}
