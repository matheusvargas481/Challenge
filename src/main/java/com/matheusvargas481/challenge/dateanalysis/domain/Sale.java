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
    private List<SaleItems> salesItems;
    private String salesmanName;

    public static final String TYPE = "003";

    public double valueTotal() {
        return salesItems.stream().mapToDouble(itemsSum -> itemsSum.getItemsQuantity() * itemsSum.getItemPrice()).sum();
    }
}
