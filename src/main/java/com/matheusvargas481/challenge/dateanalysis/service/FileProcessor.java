package com.matheusvargas481.challenge.dateanalysis.service;

import com.matheusvargas481.challenge.dateanalysis.builder.BuildProcessor;
import com.matheusvargas481.challenge.dateanalysis.domain.Client;
import com.matheusvargas481.challenge.dateanalysis.domain.Sale;
import com.matheusvargas481.challenge.dateanalysis.domain.Salesman;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class FileProcessor {

    public static final int CODE_SIZE = 4;
    public static final int CODE_POSITION = 0;

    @Autowired
    private Client client;

    @Autowired
    private Sale sale;

    @Autowired
    private Salesman salesman;

    @Autowired
    private BuildProcessor buildProcessor;

    private List<String> clientesBruto = new ArrayList<>();
    private List<String> vendedoresBruto = new ArrayList<>();
    private List<String> vendasBruto = new ArrayList<>();
    private List<Client> clientList = new ArrayList();
    private List<Salesman> vendedores = new ArrayList();
    private List<Sale> vendas = new ArrayList<>();
    private List<String> dados = new ArrayList<>();

    public List<String> leitura () {
        FileFilter filter = new FileFilter() {
            public boolean accept(File file) {
                return file.getName().endsWith(".dat");
            }
        };
        File dir = new File("/home/ilegra/data/in");
        File[] files = dir.listFiles(filter);
        try {
            for (int i = 0; i < files.length; i++) {
                dados.addAll(Files.lines(Paths.get(files[i].getPath())).collect(Collectors.toList()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dados;
    }

    public List<Client> client(){
        for(String stringClient : leitura()){
            String[] arrayClient = stringClient.split("ç");
            if(stringClient.startsWith("002")){
                Client client = Client.builder().cnpj(arrayClient[1]).name(arrayClient[2]).bussinesArea(arrayClient[3]).build();
                clientList.add(client);
            }
        }
        return clientList;
    }

    public void salesman(){

    }

    public void sale(){

    }

    public void saleItems(){

    }

//    private void checkCodeType(String linha, List<Salesman> salesmanList, List<Client> clientList, List<Sale> saleList, List<String> code) {
//        if (code != null && code.size() == CODE_SIZE && StringUtils.isNotBlank(code.get(CODE_POSITION))) {
//            if (code.get(CODE_POSITION).equals(salesman.TYPE)) {
//                salesmanList.add(buildProcessor.salesmanBuilder(code));
//            } else if (code.get(CODE_POSITION).equals(client.TYPE)) {
//                clientList.add(buildProcessor.clientBuilder(code));
//            } else if (code.get(CODE_POSITION).equals(sale.TYPE)) {
//                saleList.add(buildProcessor.saleBuilder(code));
//            }
//
//        } else {
//            log.error("Error parsing line\n", linha);
//            System.out.println("Colocar exception");;
//
//        }
//    }

//    private void buildQuantities(Exchange exchange, List<Seller> sellers, List<Client> clients, List<Sale> sales) {
//        AmountDice amount = AmountDice.builder()
//                .sellers(sellers)
//                .clients(clients)
//                .sales(sales).build();
//        exchange.getIn().setBody(amount);
//    }
}