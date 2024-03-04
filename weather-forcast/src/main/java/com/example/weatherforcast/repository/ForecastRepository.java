package com.example.weatherforcast.repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.example.weatherforcast.model.Forecast;
import com.example.weatherforcast.model.ForecastResponse;

@Repository
public class ForecastRepository {
	private static Logger logger = LoggerFactory.getLogger(ForecastRepository.class);
	private Map<String, List<Forecast>> foreCastData = new HashMap<>();
	private Map<String, List<Forecast>> cachedForeCastData = new HashMap<>();
	private Map<String, Long> keysWithExpiry = new HashMap<>();
	private long expiryInMilliseconds = 30000;

	{
		logger.info("Initializing data to simulate database");

		Forecast f1 = new Forecast("85001", "Phoenix", "Arizona", new Date(), 63, 65, 58, 30, 45, "Good");
		Forecast f2 = new Forecast("85001", "Phoenix", "Arizona", DateUtils.addDays(new Date(), 1), 60, 65, 57, 33, 42,
				"Good");
		Forecast f3 = new Forecast("85001", "Phoenix", "Arizona", DateUtils.addDays(new Date(), 2), 62, 67, 58, 31, 43,
				"Good");
		List<Forecast> forecastList = List.of(f1, f2, f3);
		foreCastData.put("85001", forecastList);

		Forecast f4 = new Forecast("30002", "Georgia", "Atlanta", new Date(), 64, 69, 58, 28, 40, "Average");
		Forecast f5 = new Forecast("30002", "Georgia", "Atlanta", DateUtils.addDays(new Date(), 1), 63, 69, 56, 30, 41,
				"Average");
		Forecast f6 = new Forecast("30002", "Georgia", "Atlanta", DateUtils.addDays(new Date(), 2), 61, 64, 51, 33, 42,
				"Average");
		List<Forecast> forecastList1 = List.of(f4, f5, f6);
		foreCastData.put("30002", forecastList1);

		Forecast f7 = new Forecast("73301", "Texas", "Austin", new Date(), 61, 64, 57, 27, 40, "Poor");
		Forecast f8 = new Forecast("73301", "Texas", "Austin", DateUtils.addDays(new Date(), 1), 55, 61, 52, 31, 36,
				"Poor");
		Forecast f9 = new Forecast("73301", "Texas", "Austin", DateUtils.addDays(new Date(), 2), 56, 67, 57, 28, 30,
				"Poor");
		List<Forecast> forecastList2 = List.of(f7, f8, f9);
		foreCastData.put("73301", forecastList2);

	}

	public ForecastResponse fetchForecastByZipcode(String zipcode, boolean extend) {
		Long expiryTimestamp = keysWithExpiry.get(zipcode);

		try {
			if(expiryTimestamp == null || expiryTimestamp < System.currentTimeMillis()) {
				logger.info("Fetching data from databse");
				// Adding fresh data from database to the cache
				List<Forecast> resultFromDb = getByZipcodeFromDb(zipcode);
				cachedForeCastData.put(zipcode, resultFromDb);

				// Adding the expiry time for the data added to cache
				keysWithExpiry.put(zipcode, System.currentTimeMillis() + expiryInMilliseconds);

				if (extend) {
					return new ForecastResponse(getByZipcodeFromDb(zipcode), false);
				} else {
					return new ForecastResponse(List.of(getByZipcodeFromDb(zipcode).get(0)), false);
				}
			}
			else {
				logger.info("Fetching data from cached data");
				if (extend) {
					return new ForecastResponse(getByZipcodeFromCache(zipcode), true);
				} else {
					return new ForecastResponse(List.of(getByZipcodeFromCache(zipcode).get(0)), true);
				}
			} 
		} catch (Exception ex) {
			logger.error("Error while fetching forecast data", ex);
			throw new RuntimeException("Error fetching forecast data.");
		}
	}

	public List<Forecast> getByZipcodeFromDb(String zipcode) {
		List<Forecast> forecastByZipcode = foreCastData.get(zipcode);
		return forecastByZipcode;
	}

	public List<Forecast> getByZipcodeFromCache(String zipcode) {
		List<Forecast> forecastByZipcode = cachedForeCastData.get(zipcode);
		return forecastByZipcode;
	}

	public Map<String, Long> getKeysWithExpiry() {
		return keysWithExpiry;
	}	
}
