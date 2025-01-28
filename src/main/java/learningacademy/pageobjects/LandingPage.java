package learningacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import learningacademy.abstractComponent.AbstractComponents;

public class LandingPage extends AbstractComponents {

	WebDriver driver;

	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	// WebElement userEmail=driver.findElement(By.id("userEmail"));

	@FindBy(id = "userEmail")
	WebElement userEmail;

	@FindBy(id = "userPassword")
	WebElement passwordele;

	@FindBy(id = "login")
	WebElement submit;

	@FindBy(css = "[class*='flyInOut']")
	WebElement errorMessage;

	public ProductCatalouge loginApplication(String email, String password) {
		userEmail.sendKeys(email);
		passwordele.sendKeys(password);
		submit.click();
		ProductCatalouge productCatalouge = new ProductCatalouge(driver);

		return productCatalouge;
	}

	public String getErrorMessage() {
		waitForElementToAppear(errorMessage);
		return errorMessage.getText();
	}

	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client/");
	}

}
