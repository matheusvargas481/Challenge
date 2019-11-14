package com.matheusvargas481.challenge.dateanalysis.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Result {
    private int amountClients;
    private int amountSellers;
    private Long idExpensiveSale;
    private String worstSeller;
}
