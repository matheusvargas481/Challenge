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

public class Sale {
    private Long id;
       private List<SaleItems> saleItemsList = new ArrayList<>();
    private String salesmanName;
    private double valorTotalVenda = 0D;

    public static final String TYPE = "003";
//
//    public List<SaleItems> saleItemsList(String stringSale) {
//        String[] arraySale = stringSale.split("ç");
//        String[] arraySaleItems=arraySale[2].replace("[", "").replace("]", "").split(",");;
//        for (String item : arraySaleItems) {
//            String[] arrayItems = item.split("-");
//            saleItemsList.add(new SaleItems(Long.parseLong(arrayItems[0]), Integer.parseInt(arrayItems[1]), Double.parseDouble(arrayItems[2])));
//        }
//        return saleItemsList;
//    }
//
//    public Double totalCalculationSale() {
//        valorTotalVenda = saleItemsList.stream().mapToDouble(iv -> iv.getItemsQuantity() * iv.getItemPrice()).sum();
//        return valorTotalVenda;
//    }

}
