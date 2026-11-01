package automation.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import automation.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {
	
	WebDriver driver;

	public CheckoutPage(WebDriver driver) {

		super(driver);
		// initialization
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "[placeholder='Select Country']")
	WebElement country;
	
	
	//@FindBy(xpath = "(//button[contains(@class,'ta-item')])[2]")
	@FindBy(xpath = "(//button[@type='button'])[2]")
	WebElement selectCountry;
	
	@FindBy(css = ".action__submit")
	WebElement submit;
	
	public void selectCountry(String countryName) {
		
		Actions a = new Actions(driver);
		a.sendKeys(country, countryName).build().perform();
		selectCountry.click();
		
	}
	
	public ConfirmationPage submitOrder() {
		
		submit.click();
		
		return new ConfirmationPage(driver);
	}
	
}
