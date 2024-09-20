package rahulshettyacademy.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckOutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class StepDefinitionImpli extends BaseTest{
	
	public LandingPage landingpage;
	public ProductCatalogue productcataloguepage;
	public CartPage cartPage;
	public CheckOutPage checkoutPage;
	public ConfirmationPage confirmationPage;
	
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException
	{
		landingpage = launchApplication();	 	
	}
	
	@Given("^I logged in with username (.+) and password (.+)$")
	public void I_logged_in_witg_username_and_password(String userName, String password)
	{
		productcataloguepage = landingpage.loginApplication(userName,password);
	}
	 
    @When("^I add product (.+) to cart$")
    public void I_add_product_to_cart(String productName) throws InterruptedException
    {
		productcataloguepage.addProductToCart(productName);
    }
    
    @When("^I checkout (.+) and submit the order$")
    public void I_checkout_and_submit_the_order(String productName) throws InterruptedException
    {
    	cartPage = productcataloguepage.goToCartPage();
		Boolean match = cartPage.VerifyProductDisplay(productName);
		Assert.assertTrue(match);

		checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("india");

		confirmationPage = checkoutPage.submitOrder();
    }
    
    @Then("{string} message is displayed on ConfirmationPage")
    public void message_displayed_confirmationPage(String string)
    {
    	String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
		driver.close();
    }
    
    @Then("{string} message is displayed")
    public void message_is_displayed(String stringarg)
    {
    	Assert.assertEquals(stringarg, landingpage.getErrorMessage());
    	driver.close();
    }

}
