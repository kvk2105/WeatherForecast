package com.example.weatherforcast.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.weatherforcast.model.ForecastResponse;
import com.example.weatherforcast.service.ForecastService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/api/v1/weather")
@Slf4j
public class ForecastController {
	private static Logger logger = LoggerFactory.getLogger(ForecastController.class);

	private final ForecastService forecastService;

	public ForecastController(ForecastService forecastService) {
		this.forecastService = forecastService;
	}

	@RequestMapping(value = "/zipcode", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ForecastResponse> getForecast(@RequestParam(required = true) String zipcode,
			@RequestParam(required = true) boolean extendedForecast) {
		logger.info("Recieved Request for zipcode:{}", zipcode);
		ForecastResponse foreCastData = forecastService.fetchForecastByZipcode(zipcode, extendedForecast);
		if (foreCastData != null)
			return ResponseEntity.ok(foreCastData);
		else
			return ResponseEntity.notFound().build();

	}
}