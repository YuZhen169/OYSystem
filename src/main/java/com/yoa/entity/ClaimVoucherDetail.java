package com.yoa.entity;

import lombok.Data;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by ❤ on 2019/11/1.
 */
@Data
@Table(name = "claim_voucher_detail")
public class ClaimVoucherDetail {

    private Integer id;
    private Integer mainId;
    private String item;
    private Double account;
    private String des;

    private ClaimVoucher claimVoucher;

}
