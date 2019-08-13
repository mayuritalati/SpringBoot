package com.mayuri;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class CurrencyConversionController {
  
	@Autowired
	ExchangeValueRepository exchangeValueRepository;
	
	@Autowired
	Environment Environment;
    
    @GetMapping(path="/currency-exchange/from/{from}/to/{to}", produces = "application/json")
    public ExchangeValue retriveConversion (@PathVariable String from, @PathVariable String to) {
    	ExchangeValue exchangeValue = exchangeValueRepository.findByFromAndTo   (from, to);
    	exchangeValue.setPort(Integer.parseInt(Environment.getProperty("local.server.port")));
    	System.out.println("calll");
    	return exchangeValue;
    }
    
 
}
