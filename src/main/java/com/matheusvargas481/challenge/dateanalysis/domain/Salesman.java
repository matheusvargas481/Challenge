package com.matheusvargas481.challenge.dateanalysis.domain;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString

public class Salesman {
    private String cpf;
    private String name;
    private double salary;
//    private Double totalSale = 0D;
    public static final String TYPE = "001";
}
