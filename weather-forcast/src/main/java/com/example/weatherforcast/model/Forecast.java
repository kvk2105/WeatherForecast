package com.example.weatherforcast.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Forecast {
	private String zipcode;
	private String city;
	private String state;
	private Date date;
	private int currentTemperature;
	private int highTemperature;
	private int lowTemperature;
	private int wind;
	private int precipitation;
	private String airQuality;
	
	public Forecast(String zipcode, String city, String state, Date date, int currentTemperature, int highTemperature,
			int lowTemperature, int wind, int precipitation, String airQuality) {
		super();
		this.zipcode = zipcode;
		this.city = city;
		this.state = state;
		this.date = date;
		this.currentTemperature = currentTemperature;
		this.highTemperature = highTemperature;
		this.lowTemperature = lowTemperature;
		this.wind = wind;
		this.precipitation = precipitation;
		this.airQuality = airQuality;
	}	
}
