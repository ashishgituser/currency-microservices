package com.microservices.currencyexchangeservice.controller;

import com.microservices.currencyexchangeservice.bean.CurrencyExchange;
import com.microservices.currencyexchangeservice.repository.CurrencyExchangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {

    @Autowired
    private CurrencyExchangeRepository currencyExchangeRepository;

    @Autowired
    private Environment environment;

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange retrieveExchangeValue(@PathVariable("from") String from, @PathVariable("to") String to) {
        // var currencyExchange = new CurrencyExchange(1000L, from, to, BigDecimal.valueOf(100));
        var currencyExchange = currencyExchangeRepository.findByFromAndTo(from, to);
        if (currencyExchange == null) {
            throw new RuntimeException("Currency data not found.");
        }
        currencyExchange.setEnvironment(environment.getProperty("local.server.port"));
        return currencyExchange;
    }
}
