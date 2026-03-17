package com.bhanu.service;


import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.model.CityResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.InetAddress;

@Service
public class GeoIPService {

    @Autowired
    private DatabaseReader databaseReader;

    public String getCountry(String ipAddress) {

        try {

            InetAddress ip = InetAddress.getByName(ipAddress);

            CityResponse response = databaseReader.city(ip);

            return response.getCountry().getName();

        } catch (Exception e) {

            return "Unknown";
        }
    }

    public String getCity(String ipAddress) {

        try {

            InetAddress ip = InetAddress.getByName(ipAddress);

            CityResponse response = databaseReader.city(ip);

            return response.getCity().getName();

        } catch (Exception e) {

            return "Unknown";
        }
    }
}