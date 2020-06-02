package com.yoa.dao;

import com.yoa.entity.CheckResult;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;

/**
 * Created by ❤ on 2019/11/20.
 */
@Repository("checkResultMapper")
public interface CheckResultMapper {

    public Integer addCheckResult(CheckResult checkResult);

    public List<CheckResult> findByClaimId(@Param("claimId") Integer claimId);

    public Integer deleteByMainId(@Param("claimId")Integer claimId);

}
