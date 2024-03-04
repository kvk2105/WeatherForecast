package com.example.weatherforcast.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.weatherforcast.model.ForecastResponse;
import com.example.weatherforcast.repository.ForecastRepository;

@Service
public class ForecastService {
	private static Logger logger = LoggerFactory.getLogger(ForecastService.class);
	
	@Autowired
	private ForecastRepository forecastRepository;
	public ForecastResponse fetchForecastByZipcode(String zipcode, boolean extend) {
		return forecastRepository.fetchForecastByZipcode(zipcode,extend);
	}

}
