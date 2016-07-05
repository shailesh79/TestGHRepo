package com.duetche.controller;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;

import com.duetche.model.Shop;
import com.duetche.service.ShopService;

@RestController
@RequestMapping("/shop")
public class ShopController {
	
	
	ShopService shopService = new ShopService();
	
	@RequestMapping(method=RequestMethod.GET, value="/getShop")
	public Shop getNearestShop(@RequestParam( value="latitude",required = false) String latitude
			,@RequestParam( value="longitude",required = false) String longitude)
	 // public Shop getNearestShop(@RequestParam String latitude, String longitude )
	  {
		
		return shopService.getNearestShop(latitude, longitude);
		 
	  }
	
	  
	@RequestMapping(method=RequestMethod.POST, value="/addShop", consumes= "application/json")
	  public void saveShopDetails(@RequestBody Shop shop) {
	
		try{
			
			shopService.saveShopDetails(shop);
		
		}catch(IOException exp)
		{
		}catch(Exception mainExp)
		{
			System.out.println("mainExp = "+mainExp);
		}
		
	  }
	
	
	

}
