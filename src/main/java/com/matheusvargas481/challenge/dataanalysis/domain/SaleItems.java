package com.matheusvargas481.challenge.dataanalysis.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString

public class SaleItems {
    private Long id;
    private int itemsQuantity;
    private double itemPrice;
}