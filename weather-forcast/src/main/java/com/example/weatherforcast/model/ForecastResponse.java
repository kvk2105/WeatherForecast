package com.example.weatherforcast.model;

import java.util.List;

import lombok.Data;

@Data
public class ForecastResponse {
	List<Forecast> forecastData;
	boolean isFromcache;
	public ForecastResponse(List<Forecast> forecastData, boolean isFromcache) {
		super();
		this.forecastData = forecastData;
		this.isFromcache = isFromcache;
	}
	
	
}
