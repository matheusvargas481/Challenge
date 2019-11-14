package com.matheusvargas481.challenge.dateanalysis.builder;

import com.matheusvargas481.challenge.dateanalysis.domain.Client;
import com.matheusvargas481.challenge.dateanalysis.domain.Sale;
import com.matheusvargas481.challenge.dateanalysis.domain.SaleItems;
import com.matheusvargas481.challenge.dateanalysis.domain.Salesman;

import java.util.ArrayList;
import java.util.List;

public class BuildProcessor {
    private static final String SEPARETOR = "ç";
    private static final String SEPARETOR_COMMA = ",";
    private static final String SEPARETOR_HYPHEN = "-";
    private List<Client> clientList = new ArrayList();
    private List<Salesman> salesmanList = new ArrayList();
    private List<Sale> saleList = new ArrayList<>();
    private List<SaleItems> saleItemsList = new ArrayList<>();

    public Client client(String stringClient) {
        String[] arrayClient = stringClient.split(SEPARETOR);
        Client client = Client.builder()
                .cnpj(arrayClient[1])
                .name(arrayClient[2])
                .bussinesArea(arrayClient[3])
                .build();
        clientList.add(client);
        return client;
    }

    public Salesman salesman(String stringSalesman) {
        String[] arraySalesman = stringSalesman.split(SEPARETOR);
        Salesman salesman = Salesman
                .builder()
                .cpf(arraySalesman[1])
                .name(arraySalesman[2])
                .salary(Double.parseDouble(arraySalesman[3]))
                .build();
        salesmanList.add(salesman);
        return salesman;
    }

    public List<SaleItems> saleItems(String stringSalesItems) {
        double totalSale = 0;
        String items = stringSalesItems.replace("[", "").replace("]", "");
        String[] arrayItemsList = items.split(SEPARETOR_COMMA);
        for (String item : arrayItemsList) {
            String[] arrayItens = item.split(SEPARETOR_HYPHEN);
            SaleItems saleItems = SaleItems
                    .builder()
                    .idItems(Long.parseLong(arrayItens[0]))
                    .itemsQuantity(Integer.parseInt(arrayItens[1]))
                    .itemPrice(Double.parseDouble(arrayItens[2]))
                    .build();
            totalSale = totalSale + Double.parseDouble(arrayItens[2]);
            saleItemsList.add(saleItems);
        }
        return saleItemsList;
    }

    public Sale sale(String stringSale) {
        String[] arraySale = stringSale.split(SEPARETOR);
        Sale sale = Sale
                .builder()
                .id(Long.parseLong(arraySale[1]))
                .saleItemsList(saleItems(arraySale[2]))
                .salesmanName(arraySale[3])
                .build();
        saleList.add(sale);
        return sale;
    }
}
