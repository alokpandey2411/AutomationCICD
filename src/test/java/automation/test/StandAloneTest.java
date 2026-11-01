package automation.test;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import automation.pageobjects.LandingPage;

public class StandAloneTest {
	
	public static void main(String[] args) {
		
		String productName="ZARA COAT 3";
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client/#/auth/login");
		
		LandingPage lp = new LandingPage(driver);
		driver.findElement(By.id("userEmail")).sendKeys("varunpandey@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Alok@112233");
		driver.findElement(By.id("login")).click();
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".offset-sm-1")));
		List<WebElement> products=driver.findElements(By.cssSelector(".offset-sm-1"));
		
		WebElement prod=products.stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
		.orElse(null);
		
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		
		//driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
		driver.findElement(By.cssSelector(".btn.btn-custom[routerlink='/dashboard/cart']")).click();
		
		List<WebElement> cartProducts=driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean match=cartProducts.stream().anyMatch(cp->cp.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);
		
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();
		
		driver.findElement(By.xpath("(//button[@type='button'])[2]")).click();
		driver.findElement(By.cssSelector(".action__submit")).click();
		
		String confMess=driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confMess.equalsIgnoreCase("Thankyou for the order."));
		driver.quit();
		
		
		
		
	}
	
	
	
}
