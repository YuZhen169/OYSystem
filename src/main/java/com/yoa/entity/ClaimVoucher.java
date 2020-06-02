package com.yoa.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.*;

/**
 * Created by ❤ on 2019/10/30.
 */
@Data
@Table(name="biz_claim_voucher")
public class ClaimVoucher {

    private Integer id;
    private String nextDealSn;
    private String createSn;
    private Date createTime;
    private String event;
    private Double totalAccount;
    private String status;
    private Date modifyTime;

    private String snName;
    private String nextName;

    private List<ClaimVoucherDetail> detailList;


}
