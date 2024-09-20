package rahulshettyacademy.pageobjects;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {

	// Local class variable
	WebDriver driver;

	// Construction
	public ProductCatalogue(WebDriver driver) {

		super(driver);
		// Initialization of driver
		// Local class variable = StandAloneTest variable driver
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// PageFactory
	@FindBy(css = ".mb-3")
	List<WebElement> products;
	// List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
	
	@FindBy(css = ".ng-animating")
	WebElement spinner;

	By productsBy = By.cssSelector(".mb-3");
	
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	
	By toastMessage = By.cssSelector("#toast-container");

	public List<WebElement> getProductList() {
		waitForElementToAppear(productsBy);
		return products;
	}

	public WebElement getProductByName(String productName) {
		WebElement prodName = null;
		List<WebElement> productList = getProductList();
		for(WebElement prodList : productList) {
			if(prodList.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(productName)) {
				prodName = prodList;
			}
		}
		return prodName;
//		WebElement prod = getProductList().stream()
//				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
//				.orElse(null);
//		return prod;
	}

	public void addProductToCart(String productName) throws InterruptedException {
		
		WebElement prod = getProductByName(productName);
		prod.findElement(addToCart).click();
		waitForElementToAppear(toastMessage);
		waitForElementToDisappear(spinner);
	}
}
