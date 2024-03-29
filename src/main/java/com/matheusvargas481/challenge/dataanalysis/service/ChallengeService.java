package com.matheusvargas481.challenge.dataanalysis.service;

import com.matheusvargas481.challenge.dataanalysis.domain.Costumer;
import com.matheusvargas481.challenge.dataanalysis.domain.Sale;
import com.matheusvargas481.challenge.dataanalysis.domain.Salesman;

import java.util.Comparator;
import java.util.List;

public class ChallengeService {
    private List<Costumer> costumers;
    private List<Salesman> salesmans;
    private List<Sale> sales;

    public ChallengeService(List<Costumer> costumers, List<Salesman> salesmans, List<Sale> sales) {
        this.costumers = costumers;
        this.salesmans = salesmans;
        this.sales = sales;
    }

    public int getAmountClient() {
        return costumers.size();
    }

    public int getAmountSeller() {
        return salesmans.size();
    }

    public Long getExpensiveSale() { return sales.stream().max(Comparator.comparing(Sale::valueTotal)).map(Sale::getId).get(); }

    public String getWorstSeller() { return salesmans.stream().min(Comparator.comparing(Salesman::getValueTotalSale)).map(Salesman::getName).get(); }
}
