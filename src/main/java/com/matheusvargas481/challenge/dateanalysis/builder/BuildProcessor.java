package com.matheusvargas481.challenge.dateanalysis.builder;

import com.matheusvargas481.challenge.dateanalysis.domain.Client;
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

    public Client createClient(String[] lineClient) {
        Client client = Client.builder()
                .cnpj(lineClient[1])
                .name(lineClient[2])
                .bussinesArea(lineClient[3])
                .build();
        return client;
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
                .saleItemsList(saleItems(lineSale[2]))
                .salesmanName(lineSale[3])
                .build();
        return sale;
    }

    private List<SaleItems> saleItems(String lineSalesItems) {
        List<SaleItems> saleItemsList = new ArrayList<>();
        String items = lineSalesItems.replace("[", "").replace("]", "");
        String[] arrayItemsList = items.split(SEPARETOR_COMMA);
        for (String item : arrayItemsList) {
            String[] arrayItens = item.split(SEPARETOR_HYPHEN);
            SaleItems saleItems = SaleItems
                    .builder()
                    .idItems(Long.parseLong(arrayItens[0]))
                    .itemsQuantity(Integer.parseInt(arrayItens[1]))
                    .itemPrice(Double.parseDouble(arrayItens[2]))
                    .build();
            saleItemsList.add(saleItems);
        }
        return saleItemsList;
    }

    public void assignmentSales(List<Sale> salesList, List<Salesman> salesmanList) {
        salesmanList.forEach(salesman ->
                salesList.stream()
                        .filter(sale -> sale.getSalesmanName().equals(salesman.getName()))
                        .forEach(sale -> salesman.addSale(sale)));
    }

}
