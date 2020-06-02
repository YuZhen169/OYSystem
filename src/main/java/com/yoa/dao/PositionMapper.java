package com.yoa.dao;

import com.yoa.entity.Position;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ❤ on 2019/11/6.
 */
@Repository("positionMapper")
public interface PositionMapper {

    public List<Position> findByCondition(Position position)throws Exception;




}
