package com.mayuri.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.mayuri.bean.CurrencyConversionBean;
import com.mayuri.proxy.FeignClient.CurrencyExchangeServiceProxy;

@RestController
public class CurrencyConversionController {
	@Autowired
	CurrencyExchangeServiceProxy proxy;
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean correncyConversion(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {
		CurrencyConversionBean response = proxy.retrieveExchangeValue(from, to);

		return new CurrencyConversionBean(response.getId(), response.getFrom(), response.getTo(), response.getConversionMultiple(),
				response.getQuantity(), quantity.multiply(response.getConversionMultiple()), response.getPort());
		}

}
