package com.duetche.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.duetche.model.Shop;
@Component
@Service
public class ShopService{
	
	final static Logger log = Logger.getLogger(ShopService.class);
	
	@Autowired
	AddressConverter converter;
	List<Shop> shopDetailsLst = new ArrayList<>();
	
	@Transactional
	public void saveShopDetails(Shop shop) throws IOException
	{
		System.out.println("converter ="+converter);
		String fullAddress = null;
		fullAddress = shop.getShopName()+","+shop.getShopAddress()+","+shop.getPostCode();
		shop.setLatitude(converter.findLatitude(fullAddress));
		shop.setLongitude(converter.findLongitude(fullAddress));
		shopDetailsLst.add(shop);
		log.info("Shop details added !!");
	
	}
	
	public Shop getNearestShop(String latitude, String longitude)
	{
		SortedMap<Double, Shop> sortedResultMap = new TreeMap<>();
		
		for(Shop shop  : shopDetailsLst)
		{
			
			sortedResultMap.put(distance(new Double(shop.getLatitude()), new Double(shop.getLongitude()), new Double(latitude), new Double(longitude)),shop);
		}
		
		if(!sortedResultMap.isEmpty())
		{
			return sortedResultMap.get(sortedResultMap.firstKey());
		}
		
		log.info("Nothing to return !!");
		return null;
		
		
	}
	
	
	private double distance(double lat1, double lng1, double lat2, double lng2) {

	    double earthRadius = 3958.75; // in miles, change to 6371 for kilometer output

	    double dLat = Math.toRadians(lat2-lat1);
	    double dLng = Math.toRadians(lng2-lng1);

	    double sindLat = Math.sin(dLat / 2);
	    double sindLng = Math.sin(dLng / 2);

	    double a = Math.pow(sindLat, 2) + Math.pow(sindLng, 2)
	        * Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2));

	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

	    double dist = earthRadius * c;

	    return dist; // output distance, in MILES
	}
	
}
