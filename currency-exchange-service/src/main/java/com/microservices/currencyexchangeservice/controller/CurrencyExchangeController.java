package com.microservices.currencyexchangeservice.controller;

import com.microservices.currencyexchangeservice.models.ExchangeValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import static java.util.Objects.requireNonNull;

@RestController
public class CurrencyExchangeController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private Environment environment;
    @Autowired
    private ExchangeValueRepository exchangeValueRepository;

    @GetMapping("/currency-exchange/{from}/{to}")
    public ExchangeValue retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
        final ExchangeValue exchangeValue = exchangeValueRepository.findByFromAndTo(from, to);
        if (exchangeValue == null) {
            throw new UnsupportedOperationException("Wrong or unsupported currency!");
        }
        exchangeValue.setPort(Integer.parseInt(requireNonNull(environment.getProperty("local.server.port"))));
        logger.info("{}", exchangeValue);
        return exchangeValue;
    }

}
