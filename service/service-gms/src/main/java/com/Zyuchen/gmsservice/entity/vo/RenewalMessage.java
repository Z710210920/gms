package com.Zyuchen.gmsservice.entity.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class RenewalMessage {

    private int[] length = {1, 3, 6, 12};
    private String userId;
    private int type;

    public BigDecimal getTotalNumber(){
        return BigDecimal.valueOf(this.length[this.getType()] * 150 - this.getType() * 10);
    }
}
