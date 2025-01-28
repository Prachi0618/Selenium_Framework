package learningacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import learningacademy.abstractComponent.AbstractComponents;



public class CheckoutPage extends AbstractComponents{
	

		WebDriver driver;

		public CheckoutPage(WebDriver driver) {
			super(driver);
			this.driver = driver;
			PageFactory.initElements(driver, this);

		}

		@FindBy(css = ".action__submit")
		 private WebElement submit;

		@FindBy(css = "[placeholder='Select Country']")
		private WebElement country;

		@FindBy(xpath = "(//button[contains(@class,'ta-item')])[1]")
		private WebElement selectCountry;

		private By results = By.cssSelector(".ta-results");
		By submits= By.cssSelector(".action__submit");
		public void selectCountry(String countryName) {
			Actions a = new Actions(driver);
			a.sendKeys(country, countryName).build().perform();
			waitForElementToAppear(By.cssSelector(".ta-results"));
			selectCountry.click();
		}
		
		public ConfirmationPage submitOrder()
		{
			JavascriptExecutor js= (JavascriptExecutor)driver;
			 js.executeScript("document.body.style.zoom='70%'");
			waitForElementToClick(By.cssSelector(".action__submit"));
			submit.click();
			return new ConfirmationPage(driver);
			
			
		}

}
