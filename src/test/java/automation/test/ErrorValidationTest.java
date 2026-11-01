package automation.test;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import automation.TestComponents.BaseTest;
import automation.TestComponents.Retry;
import automation.pageobjects.CartPage;
import automation.pageobjects.CheckoutPage;
import automation.pageobjects.ConfirmationPage;
import automation.pageobjects.ProductCatalogue;

public class ErrorValidationTest extends BaseTest {
	
	@Test(groups= {"ErrorHandling"},retryAnalyzer=Retry.class)
	public void LoginErrorValidation() throws IOException {
		
		String productName="ZARA COAT 3";
		landingPage.loginApplication("varunpandey1122@gmail.com", "Alok@1122331");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());

	}
	
	@Test
	public  void ProductErrorValidation() throws InterruptedException {
		
		String productName="ZARA COAT 3";
		ProductCatalogue productCatalogue=landingPage.loginApplication("varunpandey@gmail.com", "Alok@112233");
		
		List<WebElement> products=productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage=productCatalogue.goToCartPage();
		
		
		Boolean match=cartPage.verifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);
	}
	
}
