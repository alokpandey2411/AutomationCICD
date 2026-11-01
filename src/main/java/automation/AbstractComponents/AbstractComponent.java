package automation.AbstractComponents;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import automation.pageobjects.CartPage;
import automation.pageobjects.OrderPage;

public class AbstractComponent {

	WebDriver driver;

	public AbstractComponent(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//button[@routerlink='/dashboard/cart']")
	WebElement cartHeader;
	
	@FindBy(xpath = "//button[@routerlink='/dashboard/myorders']")
	WebElement orderHeaders;

	public void waitForElementToAppear(By Findby) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(Findby));
	}

	public void waitForWebElementToAppear(WebElement Findby) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(Findby));
	}

	public void waitForElementToDisappear(WebElement elem) throws InterruptedException {
		
		Thread.sleep(1000);

//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//		wait.until(ExpectedConditions.invisibilityOf(elem));
	}

	public CartPage goToCartPage() {

		cartHeader.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;
	}
	
	public OrderPage goToOrderPage() {
		orderHeaders.click();
		OrderPage orderPage = new OrderPage(driver);
		return orderPage;
	}

}
