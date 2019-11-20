package com.matheusvargas481.challenge.dataanalysis.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString

public class Costumer {
    private String cnpj;
    private String name;
    private String bussinesArea;

    public static final String TYPE = "002";
}
