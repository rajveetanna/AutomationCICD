package rahulshettyacademy.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SelfTryStandAlone {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.get("https://rahulshettyacademy.com/client");

		driver.findElement(By.id("userEmail")).sendKeys("rajveebhatt178@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Vivek@2212");
		driver.findElement(By.id("login")).click();

		if (driver.findElement(By.id("toast-container")).getText().contains("Login Successfully")) {

			List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
			List<WebElement> productName = driver.findElements(By.xpath("//div[@class='card-body']//h5"));
			List<WebElement> addToCartButton = driver.findElements(By.xpath("//button[text()=' Add To Cart']"));
			
			for (int i = 0; i < products.size(); i++) {
				String itemName = productName.get(i).getText();
				if (itemName.equalsIgnoreCase("ADIDAS ORIGINAL")) {
					addToCartButton.get(i).click();
				}
			}

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(class,'ngx-spinner-overlay')]")));
			
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),' Cart ')]")));
				driver.findElement(By.xpath("//button[contains(text(),' Cart ')]")).click();
			

			// prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		}
	}

}
