package automation.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import automation.AbstractComponents.AbstractComponent;

public class OrderPage extends AbstractComponent {

	WebDriver driver;

	public OrderPage(WebDriver driver) {

		super(driver);
		// initialization
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	// PageFactory

	@FindBy(css = "tr td:nth-child(3)")
	List<WebElement> productNames;
	
	
	
	public Boolean verifyOrderDisplay(String productName) {
		
		Boolean match=productNames.stream().anyMatch(cp->cp.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	
	
}
