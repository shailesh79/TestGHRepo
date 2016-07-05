package com.duetche.controller;

import static org.junit.Assert.fail;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.nio.charset.Charset;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.duetche.model.Shop;
import com.duetche.service.ShopService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ShopController.class)
@WebAppConfiguration
public class ShopControllerTest {
	
	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(
			MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
	
	protected MockMvc mockMvc;
	
	@Mock
	protected ShopService shopService;
	
	@InjectMocks
	protected ShopController shopController;


	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(shopController).build();
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testGetNearestShop() throws Exception
	{
		testGetNearestShop("PuneShop","18.5524665", "72");
	}
	
	@Test
	public void testSaveShopDetails() throws Exception
	{

		Shop shop = new Shop();
		shop.setShopName("PuneShop");
		shop.setLatitude("18.5524665");
		shop.setLongitude("72");
		testSaveShopDetails(shop);
	}

	
	public void testGetNearestShop(String shopName,String latitude, String longitude) throws Exception {

		Shop shop = new Shop();
		shop.setShopName(shopName);
		shop.setLatitude(shopName);
		shop.setLongitude(longitude);
	
		when(shopService.getNearestShop(anyString(),anyString())).thenReturn(shop);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/shop/getShop")).
		andExpect(content().contentType(APPLICATION_JSON_UTF8))
        .andExpect(status().isOk())
        .andExpect(jsonPath("shopName").value(shopName))
        .andExpect(jsonPath("latitude").value(shopName))
        .andExpect(jsonPath("longitude").value(longitude));
		
		 verify(shopService, times(1)).getNearestShop(Mockito.anyString(),Mockito.anyString());
	     verifyNoMoreInteractions(shopService);
	}
	
	
	public void testSaveShopDetails(Shop shop) throws Exception {

	
		//when(shopService.getNearestShop(anyString(),anyString())).thenReturn(shop);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/shop/addShop"))
				
		.andExpect(content().contentType(APPLICATION_JSON_UTF8))
        .andExpect(status().isOk());

	}

	@Test
	public void testCreate() {
		fail("Not yet implemented");
	}

}
