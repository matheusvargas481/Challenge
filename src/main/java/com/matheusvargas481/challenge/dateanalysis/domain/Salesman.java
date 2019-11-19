package com.matheusvargas481.challenge.dateanalysis.domain;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@ToString

public class Salesman {
    private String cpf;
    private String name;
    private double salary;
    private List<Sale> sales;
    private double valueTotalSale;

    public void addSale(Sale sale) {
        if (sales == null) {
            sales = new ArrayList<>();
        }
        valueTotalSale += sale.valueTotal();
        sales.add(sale);
    }

    public static final String TYPE = "001";

}
