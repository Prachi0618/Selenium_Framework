package learningacademy.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import learningacademy.TestComponents.BaseTest;
import learningacademy.pageobjects.CheckoutPage;
import learningacademy.pageobjects.ConfirmationPage;
import learningacademy.pageobjects.OrderPage;
import learningacademy.pageobjects.ProductCatalouge;
import learningacademy.pageobjects.cartPage;

public class SubmitOrderTest extends BaseTest {
	String productName = "BANARSI SAREE";
	@Test(dataProvider="getData",groups= {"Purchase"},retryAnalyzer=learningacademy.TestComponents.Retry.class)
	public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException {
		
		ProductCatalouge productCatalouge = landingPage.loginApplication(input.get("email"), input.get("password"));

		List<WebElement> products = productCatalouge.getProductList();
		productCatalouge.addProductToCart(input.get("productName"));
		cartPage cartpage = productCatalouge.goToCartPage();

		Boolean match = cartpage.VerifyProductDisplay(input.get("productName"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartpage.goToCheckout();
		checkoutPage.selectCountry("Australia");
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}
	@Test(dependsOnMethods= {"submitOrder"})
	public void OrderHistoryTest() {
		ProductCatalouge productCatalouge = landingPage.loginApplication("hello1hi@gmail.com", "Hellohii1");
		OrderPage orderPage = productCatalouge.goToOrderstPage();
		Assert.assertTrue(orderPage.VerifyOrderDisplay(productName));
		
	}
	
	@DataProvider
	public Object[][] getData() throws IOException {
//		HashMap<String, String> map= new HashMap<String,String>();
//		map.put("email","hello1hi@gmail.com" );
//		map.put("password", "Hellohii1");
//		map.put("productName", "BANARSI SAREE");
//		
//		HashMap<String, String> map1= new HashMap<String,String>();
//		map1.put("email","shetty@gmail.com" );
//		map1.put("password", "Iamking@000");
//		map1.put("productName", "IPHONE 13 PRO");
		
		List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\learningacademy\\data\\PurchaseOrder.json");
		
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
}
