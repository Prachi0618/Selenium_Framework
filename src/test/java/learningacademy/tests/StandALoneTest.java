package learningacademy.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import learningacademy.pageobjects.LandingPage;

public class StandALoneTest {

	public static void main(String[] args)  {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				WebDriverManager.edgedriver().setup();
			//System.setProperty("webdriver.edge.driver", "/D://drivers//msedgedriver.exe/");
				WebDriver driver = new EdgeDriver();
				driver.get("https://rahulshettyacademy.com/client/");
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
				driver.manage().window().maximize();
				LandingPage landingPage=new LandingPage(driver); 
				String username = "hello1hi@gmail.com";
				String password = "Hellohii1";
				String prodname= "BANARSI SAREE";
				String countryName="Australia";
				driver.findElement(By.id("userEmail")).sendKeys(username); 
				driver.findElement(By.id("userPassword")).sendKeys(password);
				driver.findElement(By.id("login")).click();
				WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
				List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
				
				
				WebElement prod = products.stream().filter(product->
				product.findElement(By.cssSelector("b")).getText().equals(prodname)).findFirst().orElse(null);
				prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
				
				
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
				wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
				driver.findElement(By.cssSelector("[routerlink*='cart'")).click();
				
				
				//Verify the title it's in the cart or not 
	
				List<WebElement> cartprods=driver.findElements(By.xpath("//*[@class='cartSection']//h3"));
				boolean match = cartprods.stream().anyMatch(cartprod->cartprod.getText().equalsIgnoreCase(prodname));
				Assert.assertTrue(match);
				
				driver.findElement(By.cssSelector(".totalRow button")).click();
				driver.findElement(By.cssSelector("input[placeholder='Select Country'")).sendKeys(countryName);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));

				driver.findElement(By.cssSelector(".ta-item:nth-of-type(1)")).click();
				
				
				JavascriptExecutor js= (JavascriptExecutor)driver;
				js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
				wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".action__submit")));

				driver.findElement(By.cssSelector(".action__submit")).click();
				
				String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
				Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
				driver.close();

				
				
	
	
	}

}
