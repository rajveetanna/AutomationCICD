package rahulshettyacademy.tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckOutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.OrderPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest {

	String productName = "ZARA COAT 3";

	@Test(dataProvider = "getData", groups = { "Purchase" })
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {

		ProductCatalogue productcataloguepage = landingpage.loginApplication(input.get("email"), input.get("password"));

		// ProductCatalogue productcataloguepage = new ProductCatalogue(driver);
		List<WebElement> products = productcataloguepage.getProductList();
		productcataloguepage.addProductToCart(input.get("product"));

		CartPage cartPage = productcataloguepage.goToCartPage();

		// CartPage cartPage = new CartPage(driver);
		Boolean match = cartPage.VerifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);

		// CheckOutPage
		CheckOutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("india");

		// ConfirmationPage
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();

		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}

	// To verify ZARA COAT 3 is displaying in orders page
	@Test(dependsOnMethods = { "submitOrder" })
	public void orderHistoryTest() throws InterruptedException {
		ProductCatalogue productcataloguepage = landingpage.loginApplication("rajveebhatt178@gmail.com", "Vivek@2212");
		OrderPage orderpage = productcataloguepage.goToOrdesrPage();
		Assert.assertTrue(orderpage.VerifyOrderDisplay(productName));
	}

	@DataProvider
	public Object[][] getData() throws IOException {
		// This method returns list of hashMap
		List<HashMap<String, String>> data = getJsonDataToMap(
				System.getProperty("user.dir") + "//src//test//java//rahulshettyacademy//data//PurchaseOrder.json");

		return new Object[][] { { data.get(0) }, { data.get(1) } };
	}

//	HashMap<String,String> map = new HashMap<String,String>();
//	map.put("email","rajveebhatt178@gmail.com");
//	map.put("password", "Vivek@2212");
//	map.put("product","ZARA COAT 3");
//	
//	HashMap<String,String> map1 = new HashMap<String,String>();
//	map1.put("email","jiyatanna@gmail.com");
//	map1.put("password", "Vivek@2212");
//	map1.put("product","ADIDAS ORIGINAL");
//	
	// Here Object is generic data type which accept any type of data type
	// return new Object[][] {{"rajveebhatt178@gmail.com","Vivek@2212","ZARA COAT
	// 3"},{"jiyatanna@gmail.com","Vivek@2212","ADIDAS ORIGINAL"}};

}
