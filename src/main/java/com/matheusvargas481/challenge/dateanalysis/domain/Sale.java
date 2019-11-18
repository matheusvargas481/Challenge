package com.matheusvargas481.challenge.dateanalysis.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Builder
@Getter
@Setter
@ToString

public class Sale {
    private Long id;
    private List<SaleItems> saleItemsList;
    private String salesmanName;

    public static final String TYPE = "003";
}
