package com.Zyuchen.gmsservice.entity.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TopUpMessage {

    private String userId;
    private BigDecimal number;
}
