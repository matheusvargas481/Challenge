package com.matheusvargas481.challenge.dateanalysis.service;

import com.matheusvargas481.challenge.dateanalysis.domain.Client;
import com.matheusvargas481.challenge.dateanalysis.domain.Sale;
import com.matheusvargas481.challenge.dateanalysis.domain.Salesman;

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

    public void getAmountClient() {

    }

    public void getAmountSeller() {

    }

    public void getExpensiveSale() {

    }

    public void getWorsSeller() {

    }
}
