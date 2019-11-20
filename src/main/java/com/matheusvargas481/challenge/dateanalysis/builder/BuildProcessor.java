package com.matheusvargas481.challenge.dateanalysis.builder;

import com.matheusvargas481.challenge.dateanalysis.domain.Costumer;
import com.matheusvargas481.challenge.dateanalysis.domain.Sale;
import com.matheusvargas481.challenge.dateanalysis.domain.SaleItems;
import com.matheusvargas481.challenge.dateanalysis.domain.Salesman;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class BuildProcessor {

    private static final String SEPARETOR_COMMA = ",";
    private static final String SEPARETOR_HYPHEN = "-";

    public List<String[]> filterObjects(List<String[]> objects, String id) {
        return objects.stream()
                .filter(object -> object[0].startsWith(id))
                .collect(Collectors.toList());
    }

    public Costumer createCostumer(String[] lineClient) {
        Costumer costumer = Costumer.builder()
                .cnpj(lineClient[1])
                .name(lineClient[2])
                .bussinesArea(lineClient[3])
                .build();
        return costumer;
    }

    public Salesman createSalesman(String[] lineSalesman) {
        Salesman salesman = Salesman
                .builder()
                .cpf(lineSalesman[1])
                .name(lineSalesman[2])
                .salary(Double.parseDouble(lineSalesman[3]))
                .build();
        return salesman;
    }

    public Sale createSale(String[] lineSale) {
        Sale sale = Sale
                .builder()
                .id(Long.parseLong(lineSale[1]))
                .salesItems(saleItems(lineSale[2]))
                .salesmanName(lineSale[3])
                .build();
        return sale;
    }

    private List<SaleItems> saleItems(String lineSalesItems) {
        List<SaleItems> salesItems = new ArrayList<>();
        String items = lineSalesItems.replace("[", "").replace("]", "");
        String[] arrayItems = items.split(SEPARETOR_COMMA);
        for (String item : arrayItems) {
            String[] arrayItens = item.split(SEPARETOR_HYPHEN);
            SaleItems saleItems = SaleItems
                    .builder()
                    .id(Long.parseLong(arrayItens[0]))
                    .itemsQuantity(Integer.parseInt(arrayItens[1]))
                    .itemPrice(Double.parseDouble(arrayItens[2]))
                    .build();
            salesItems.add(saleItems);
        }
        return salesItems;
    }

    public void assignmentSales(List<Sale> salesList, List<Salesman> salesmanList) {
        salesmanList.forEach(salesman ->
                salesList.stream()
                        .filter(sale -> sale.getSalesmanName().equals(salesman.getName()))
                        .forEach(sale -> salesman.addSale(sale)));
    }

}
