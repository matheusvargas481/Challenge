//package com.matheusvargas481.challenge.dateanalysis.builder;
//
//import com.matheusvargas481.challenge.dateanalysis.domain.Client;
//import com.matheusvargas481.challenge.dateanalysis.domain.Sale;
//import com.matheusvargas481.challenge.dateanalysis.domain.SaleItems;
//import com.matheusvargas481.challenge.dateanalysis.domain.Salesman;
//import org.apache.commons.lang3.StringUtils;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//public class BuildProcessor {
//    private static final String SEPARADOR1 = ",";
//
//    private static final String SEPARADOR2 = "-";
//
//    public static Client clientBuilder(List<String> code) {
//        return Client.builder()
//                .cnpj(code.get(1))
//                .name(code.get(2))
//                .bussinesArea(code.get(3))
//                .build();
//    }
//
//    public static Salesman salesmanBuilder(List<String> code) {
//        return Salesman.builder()
//                .cpf(code.get(1))
//                .name(code.get(2))
//                .salary(Double.parseDouble(code.get(3)))
//                .build();
//    }
//
////    public static Sale saleBuilder(List<String> code) {
////
////        List<SaleItems> saleItemsList = new ArrayList<>();
////
////        double totalSale = 0;
////
////        String values = StringUtils.remove(StringUtils.remove(code.get(2), "["), "]");
////
////        if (StringUtils.isNotBlank(values)) {
////
////            List<String> itemList = Arrays.asList(values.split(SEPARADOR1));
////
////            for (String value : itemList) {
////
////                List<String> itemValues = Arrays.asList(value.split(SEPARADOR2));
////                saleItemsList.add(SaleItems.builder()
////                        .idItems(Long.parseLong(itemValues.get(0)))
////                        .itemsQuantity(Integer.parseInt(itemValues.get(1)))
////                        .itemPrice(Double.parseDouble(itemValues.get(2)))
////                        .build());
////
////                totalSale = totalSale + Double.parseDouble(itemValues.get(2));
////            }
////        }
////        return Sale.builder()
////                .id(Long.parseLong(code.get(1)))
////                .saleItemsList(saleItemsList)
////                .salesmanName(code.get(3))
////                .valorTotalVenda(totalSale)
////                .build();
////    }
//}
