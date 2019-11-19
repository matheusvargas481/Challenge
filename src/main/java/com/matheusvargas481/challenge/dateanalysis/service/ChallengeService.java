package com.matheusvargas481.challenge.dateanalysis.service;

import com.matheusvargas481.challenge.dateanalysis.domain.Client;
import com.matheusvargas481.challenge.dateanalysis.domain.Sale;
import com.matheusvargas481.challenge.dateanalysis.domain.Salesman;

import java.util.Comparator;
import java.util.List;

public class ChallengeService {
    private List<Client> clientList;
    private List<Salesman> salesmanList;
    private List<Sale> saleList;

    public ChallengeService(List<Client> clientList, List<Salesman> salesmanList, List<Sale> saleList) {
        this.clientList = clientList;
        this.salesmanList = salesmanList;
        this.saleList = saleList;
    }

    public int getAmountClient() {
        return clientList.size();
    }

    public int getAmountSeller() {
        return salesmanList.size();
    }

    public Long getExpensiveSale() {
        return saleList.stream().max(Comparator.comparing(Sale::valueTotal)).map(Sale::getId).get();
    }

    public String getWorsSeller() {
        return salesmanList.stream().min(Comparator.comparing(Salesman::getValueTotalSale)).map(Salesman::getName).get();
    }
}
