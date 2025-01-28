package learningacademy.stepDefinition;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import learningacademy.TestComponents.BaseTest;
import learningacademy.pageobjects.CheckoutPage;
import learningacademy.pageobjects.ConfirmationPage;
import learningacademy.pageobjects.LandingPage;
import learningacademy.pageobjects.ProductCatalouge;
import learningacademy.pageobjects.cartPage;

public class StepDefinition extends BaseTest {
	public LandingPage landingpage;
	public ProductCatalouge productCatalouge;
	public ConfirmationPage confirmationPage;
	@Given("I landed on Ecommerce Page")
	public void I_landend_on_Ecommerce_Page() throws IOException{
		landingpage = launchApplication();
	}
	
	@Given ("^I logged in with username (.+) and password (.+)$")
	public void logged_in_username_and_password(String username, String password) {
	 productCatalouge = landingPage.loginApplication(username,password);
	}
	
	  @When ("^I add the product (.+)  to cart$" )
	  public void add_product_to_cart(String productName) throws InterruptedException {
		  List<WebElement> products = productCatalouge.getProductList();
			productCatalouge.addProductToCart(productName);
	  }
	  @And("^Checkout (.+) and submit the order$") 
	  public void checkout_product_and_submit_order(String productName) {
		  cartPage cartpage = productCatalouge.goToCartPage();

			Boolean match = cartpage.VerifyProductDisplay(productName);
			Assert.assertTrue(match);
			CheckoutPage checkoutPage = cartpage.goToCheckout();
			checkoutPage.selectCountry("Australia");
			confirmationPage = checkoutPage.submitOrder();
	  }
	  @Then ("{string} message displayed on Confirmation Page")
	  public void message_displayed_confirmation_page(String string) {
		  String confirmMessage = confirmationPage.getConfirmationMessage();
			Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
			driver.close();
	  }
	  @Then ("{string}  message is displayed.")
	  public void error_message_is_displayed(String str) {
		  Assert.assertEquals(str, landingPage.getErrorMessage());
		  driver.close();
	  }
}
