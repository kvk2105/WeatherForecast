package com.example.weatherforcast.util;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.example.weatherforcast.repository.ForecastRepository;

@EnableScheduling
public class CacheCleaningUtil {

	@Autowired
	ForecastRepository forecastRepository;
	@Scheduled(fixedDelay = 60000)
	public void CealnupCache() {
		//Cleanup the cached data for every 60 seconds by removing the expired keys
		Map<String, Long> keysWithExpiry = forecastRepository.getKeysWithExpiry();
		for(String key : keysWithExpiry.keySet()) {
			if(keysWithExpiry.get(key)< System.currentTimeMillis())
				keysWithExpiry.remove(key);
		}
	}
}
