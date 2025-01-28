package learningacademy.abstractComponent;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver; 
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import learningacademy.pageobjects.OrderPage;
import learningacademy.pageobjects.cartPage;

public class AbstractComponents {
	
	WebDriver driver;
	public AbstractComponents(WebDriver driver) {
		// TODO Auto-generated constructor stub
		
		this.driver=driver;
		PageFactory.initElements(driver, this);  
	}
	
	@FindBy(css = "[routerlink*='cart']")
	WebElement cartHeader;
	
	@FindBy(css = "[routerlink*='myorders']")
	WebElement orderHeader;
	


	public void waitForElementToAppear(By findBy) {
	WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
	wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public cartPage goToCartPage()
	{
		cartHeader.click();
		cartPage cartpage = new cartPage(driver);
		return cartpage;
	}
	
	public OrderPage goToOrderstPage()
	{
		orderHeader.click();
		OrderPage orderPage = new OrderPage(driver);
		return orderPage;
	}
	
	
	public void waitForElementToClick(By findBy) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(findBy));
	}
	
	public void waitForElementToAppear(WebElement findBy) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(findBy));
	}
	
	public void waitForElementToDisappear(WebElement ele) throws InterruptedException {
		Thread.sleep(1000);
		//WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		//wait.until(ExpectedConditions.invisibilityOf(ele));
	}
}
