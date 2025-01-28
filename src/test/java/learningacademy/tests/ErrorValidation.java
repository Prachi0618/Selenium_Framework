package learningacademy.tests;

import java.io.IOException;
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
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import io.github.bonigarcia.wdm.WebDriverManager;
import learningacademy.TestComponents.BaseTest;
import learningacademy.pageobjects.CheckoutPage;
import learningacademy.pageobjects.ConfirmationPage;
import learningacademy.pageobjects.LandingPage;
import learningacademy.pageobjects.ProductCatalouge;
import learningacademy.pageobjects.cartPage;

public class ErrorValidation extends BaseTest {

	@Test(groups= {"ErrorHandling"},retryAnalyzer=learningacademy.TestComponents.Retry.class)
	
	public void LoginErrorValidation() throws IOException {

		String productName = "BANARSI SAREE";
		ProductCatalouge productCatalouge = landingPage.loginApplication("hello1hi@gmail.com", "Hellohii111");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());

	}
	@Test
	public void ProductErrorValidation() throws IOException, InterruptedException {
	String productName = "BANARSI SAREE";
	ProductCatalouge productCatalouge = landingPage.loginApplication("hello11hi@gmail.com", "Hellohii1");

	List<WebElement> products = productCatalouge.getProductList();
	productCatalouge.addProductToCart(productName);
	cartPage cartpage = productCatalouge.goToCartPage();

	Boolean match = cartpage.VerifyProductDisplay("BANARAASI");
	Assert.assertFalse(match);
	
	}
	
	

}
