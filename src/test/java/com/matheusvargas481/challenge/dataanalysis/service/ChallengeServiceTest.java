package com.matheusvargas481.challenge.dataanalysis.service;

import com.matheusvargas481.challenge.dataanalysis.builder.BuildProcessor;
import com.matheusvargas481.challenge.dataanalysis.domain.Costumer;
import com.matheusvargas481.challenge.dataanalysis.domain.Sale;
import com.matheusvargas481.challenge.dataanalysis.domain.Salesman;
import com.matheusvargas481.challenge.dataanalysis.util.ParserDat;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class ChallengeServiceTest {
    private static final String SEPARETOR = "ç";
    private ChallengeService challengeService;
    private ParserDat parserDat;

    @Before
    public void setUp() {
        parserDat = new ParserDat(SEPARETOR);
        String[] fileDat = {
                "001ç1234567891234çDiegoç50000",
                "001ç3245678865434çRenatoç40000.99",
                "002ç2345675434544345çJose da SilvaçRural",
                "002ç2345675433444345çEduardoPereiraçRural",
                "003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çDiego",
                "003ç08ç[1-34-10,2-33-1.50,3-40-0.10]çRenato"
        };
        List<String> lines = new ArrayList<>();
        lines.addAll(Arrays.asList(fileDat));
        List<String[]> objects = parserDat.createObjects(lines);
        BuildProcessor buildProcessor = new BuildProcessor();
        List<Salesman> salesmans = buildProcessor.filterObjects(objects, Salesman.TYPE).stream()
                .map(buildProcessor::createSalesman)
                .collect(Collectors.toList());
        List<Costumer> costumers = buildProcessor.filterObjects(objects, Costumer.TYPE).stream()
                .map(buildProcessor::createCostumer)
                .collect(Collectors.toList());
        List<Sale> sales = buildProcessor.filterObjects(objects, Sale.TYPE).stream()
                .map(buildProcessor::createSale)
                .collect(Collectors.toList());
        buildProcessor.assignmentSales(sales, salesmans);
        challengeService = new ChallengeService(costumers, salesmans, sales);
    }

    @Test
    public void testCreateSalesmans() {
        String[] dataSalesman = {"001ç1234567891234çDiegoç50000",
                "001ç3245678865434çRenatoç40000.99"};
        List<String> salesmanLines = new ArrayList<>(Arrays.asList(dataSalesman));
        List<String[]> objectsSalesmans = parserDat.createObjects(salesmanLines);
        BuildProcessor buildProcessor = new BuildProcessor();
        List<Salesman> salesmansTest = buildProcessor.filterObjects(objectsSalesmans, Salesman.TYPE).stream()
                .map(buildProcessor::createSalesman)
                .collect(Collectors.toList());
        assertThat(salesmansTest, containsInAnyOrder(
                hasProperty("name", is("Diego")),
                hasProperty("name", is("Renato"))
        ));
    }

    @Test
    public void testCreateCostumers() {
        String[] dataCostumer = {"002ç2345675434544345çJose da SilvaçRural",
                "002ç2345675433444345çEduardo PereiraçRural"};
        List<String> costumersLines = new ArrayList<>(Arrays.asList(dataCostumer));
        List<String[]> objectsCostumers = parserDat.createObjects(costumersLines);
        BuildProcessor buildProcessor = new BuildProcessor();
        List<Costumer> costumersTest = buildProcessor.filterObjects(objectsCostumers, Costumer.TYPE).stream()
                .map(buildProcessor::createCostumer)
                .collect(Collectors.toList());
        assertThat(costumersTest, containsInAnyOrder(
                hasProperty("name", is("Jose da Silva")),
                hasProperty("name", is("Eduardo Pereira"))
        ));
    }

    @Test
    public void testCreateSales() {
        String[] dataSale = {"003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çDiego",
                "003ç08ç[1-34-10,2-33-1.50,3-40-0.10]çRenato"};
        List<String> saleLines = new ArrayList<>(Arrays.asList(dataSale));
        List<String[]> objectsSales = parserDat.createObjects(saleLines);
        BuildProcessor buildProcessor = new BuildProcessor();
        List<Sale> salesTest = buildProcessor.filterObjects(objectsSales, Sale.TYPE).stream()
                .map(buildProcessor::createSale)
                .collect(Collectors.toList());
        assertThat(salesTest, containsInAnyOrder(
                hasProperty("salesmanName", is("Diego")),
                hasProperty("salesmanName", is("Renato"))
        ));
    }

    @Test
    public void testGetAmountClient() {
        assertEquals(2, challengeService.getAmountClient());
    }

    @Test
    public void testGetAmountSeller() {
        assertEquals(2, challengeService.getAmountSeller());
    }

    @Test
    public void testGetExpensiveSaleForId() {
        assertEquals(new Long(10), challengeService.getExpensiveSale());
    }

    @Test
    public void testGetWorsSellerForName() {
        assertEquals("Renato", challengeService.getWorstSeller());
    }
}