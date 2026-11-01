package automation.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import automation.TestComponents.BaseTest;
import automation.pageobjects.CartPage;
import automation.pageobjects.CheckoutPage;
import automation.pageobjects.ConfirmationPage;
import automation.pageobjects.OrderPage;
import automation.pageobjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest {
	
	String productName="ZARA COAT 3";
	
	@Test(dataProvider="getData",groups= {"Purchase"})
	public void SubmitOrder(HashMap<String,String> input) throws IOException, InterruptedException {
		
		
		ProductCatalogue productCatalogue=landingPage.loginApplication(input.get("email"), input.get("password"));
		
		List<WebElement> products=productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("product"));
		CartPage cartPage=productCatalogue.goToCartPage();
		
		
		Boolean match=cartPage.verifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage=cartPage.goTocheckout();
		checkoutPage.selectCountry("india");
		
		ConfirmationPage confirmationPage=checkoutPage.submitOrder();
		String confMess=confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confMess.equalsIgnoreCase("Thankyou for the order."));

	}
	
	@Test(dependsOnMethods= {"SubmitOrder"})
	public void OrderHistoryTest() {
		ProductCatalogue productCatalogue=landingPage.loginApplication("varunpandey@gmail.com", "Alok@112233");
		OrderPage orderPage=productCatalogue.goToOrderPage();
		Assert.assertTrue( orderPage.verifyOrderDisplay(productName));
	}
	
	
	@DataProvider
	public Object[][] getData() throws IOException {
		
		List<HashMap<String, String>> data=getJsonDataToMap(System.getProperty("user.dir") + "\\src\\test\\java\\automation\\data\\PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
	
	
	/*
	@DataProvider
	public Object[][] getData() {
		
		HashMap<String,String> map1= new HashMap<String, String>();
		map1.put("email", "varunpandey@gmail.com");
		map1.put("password", "Alok@112233");
		map1.put("product", "ZARA COAT 3");
		
		HashMap<String,String> map2= new HashMap<String, String>();
		map2.put("email", "varunpandey@gmail.com");
		map2.put("password", "Alok@112233");
		map2.put("product", "ZARA COAT 3");
		
		return new Object[][] {{map1},{map2}};
	}*/
	
	/*
	@DataProvider
	public Object[][] getData() {
		
	
		return new Object[][]  {{"anshika@gmail.com","Iamking@000","ZARA COAT 3"}, {"shetty@gmail.com","Iamking@000","ADIDAS ORIGINAL" } };
	}*/
	
	
	//End
}
