package com.microservices.currencyconversionservice.controllers;

import com.microservices.currencyconversionservice.feign.CurrencyExchangeServiceProxy;
import com.microservices.currencyconversionservice.models.CurrencyConversionBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyConversionController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CurrencyExchangeServiceProxy currencyExchangeServiceProxy;

    @GetMapping("/currency-converter-feign/{from}/{to}/{amount}")
    public CurrencyConversionBean convertCurrencyFeign(@PathVariable String from,
                                                  @PathVariable String to,
                                                  @PathVariable BigDecimal amount) {
        final CurrencyConversionBean response
                = currencyExchangeServiceProxy.retrieveExchangeValue(from, to);

        logger.info("{}", response);

        return new CurrencyConversionBean(response.getId(), from, to,
                response.getCurrencyRate(), amount, amount.multiply(response.getCurrencyRate()), response.getPort());
    }

}
