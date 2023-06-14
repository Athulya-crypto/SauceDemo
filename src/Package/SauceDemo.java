package Package;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

@Test
public class SauceDemo {
	ChromeDriver driver;
	@BeforeTest
	public void chrome()
	{
		System.setProperty("webdriver.chrome.driver","C:\\Selenium1\\chrome\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.get("https://www.saucedemo.com/");
		driver.manage().window().maximize();
		
	}
	@BeforeMethod
	public void url()
	{
		driver.get("https://www.saucedemo.com/");
		driver.manage().window().maximize();
		
		
	}
	public void loginToApplication()  
	{
		//Login to the application with valid user-name

		WebElement Username=driver.findElement(By.id("user-name"));
		WebElement password=driver.findElement(By.id("password"));
		boolean userNameIsEnabled=Username.isEnabled();
		System.out.println(userNameIsEnabled);
		Username.sendKeys("standard_user");
		boolean passwordIsEnabled=password.isEnabled();
		System.out.println(passwordIsEnabled);
		password.sendKeys("secret_sauce");
		
		driver.findElement(By.id("login-button")).click();
		WebElement title=driver.findElement(By.xpath("//div[@class='app_logo']"));
		String actualTitle=title.getText();
		String expectedTitle="Swag Labs";
		Assert.assertEquals(actualTitle,expectedTitle);
		System.out.println("User logged in to the application successfully");
		
		//Sort the products based on price -low to high
		WebElement sort=driver.findElement(By.xpath("//*[@id='header_container']/div[2]/div/span/select"));
		Select s=new Select(sort);
		s.selectByValue("lohi");
		System.out.println("Price sorting is done from low to high");
		
		// count the number of products listed in the page
		List<WebElement> price=driver.findElements(By.xpath("//div[@class='inventory_item_price']"));
		int count=price.size();
		System.out.println("count="+price.size());

    //add items to the cart 
	driver.findElement(By.id("add-to-cart-sauce-labs-onesie")).click();
	driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
	driver.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt")).click();
	driver.findElement(By.id("add-to-cart-test.allthethings()-t-shirt-(red)")).click();
	driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
	driver.findElement(By.id("add-to-cart-sauce-labs-fleece-jacket")).click();
	String actualRemoveIconText=driver.findElement(By.id("remove-sauce-labs-backpack")).getText();
	String expectedRemoveIconText="Remove";
	Assert.assertEquals(actualRemoveIconText, expectedRemoveIconText);
	System.out.println("Add to cart Icon is changed to Remove Icon");
	
	//click on cart icon
	driver.findElement(By.xpath("//div[@id='shopping_cart_container']/a")).click();
	String actualCurrentUrl=driver.getCurrentUrl();
	String expectedCurrentUrl="https://www.saucedemo.com/cart.html";
	Assert.assertEquals(actualCurrentUrl, expectedCurrentUrl);
	System.out.println("The page is redirected to 'Your Cart' page");
	boolean cartPageHeader=driver.findElement(By.xpath("//span[@class='title']")).isDisplayed();
	if(cartPageHeader==true)
	{
		System.out.println("Cart page header is displayed");
	}
	else
	{
		System.out.println("Cart page header is not displayed");
	}


	//click on checkout button 
	driver.findElement(By.id("checkout")).click();
	String actualCheckoutPageUrl=driver.getCurrentUrl();
	String expectedCheckoutPageUrl="https://www.saucedemo.com/checkout-step-one.html";
	Assert.assertEquals(actualCheckoutPageUrl,expectedCheckoutPageUrl);
	System.out.println("The page is redirected to 'Checkout page'");
	driver.findElement(By.xpath("//div[@id='header_container']/div[2]/span")).isDisplayed();
	
	//fill up the shipping details in the checkout page and click on continue button
	driver.findElement(By.id("first-name")).sendKeys("Antony");
	driver.findElement(By.id("last-name")).sendKeys("Domenic");
	driver.findElement(By.id("postal-code")).sendKeys("34566");
	driver.findElement(By.id("continue")).click();
	String actualCheckoutOverviewPageUrl=driver.getCurrentUrl();
	String expectedCheckoutOverviewPageurl="https://www.saucedemo.com/checkout-step-two.html";
	if(actualCheckoutOverviewPageUrl.equals(expectedCheckoutOverviewPageurl))
	{
		System.out.println("The page is redirected to the checkout overview page");
	}
	else
	{
		System.out.println("The page failed to redirect");
	}
	
	//click on finish button
	driver.findElement(By.id("finish")).click();
	 String pageSource=driver.getPageSource();
	 if(pageSource.contains("Thank you for your order!"))
	 {
		 System.out.println("Landed in the 'checkout complete' page"); 
	 }
	 else
	 {
		 System.out.println("The page failed to redirect");
	 }
	 String currentUrl=driver.getCurrentUrl();
	 String expectedUrl="https://www.saucedemo.com/checkout-complete.html";
	 Assert.assertEquals(currentUrl, expectedUrl);
	 System.out.println("Redirected to 'checkout complete' page"); 
	 
	//click on 'back home'button
	 driver.findElement(By.id("back-to-products")).click();
	 String currentPageUrl=driver.getCurrentUrl();
	 String expectedPageUrl="https://www.saucedemo.com/inventory.html";
	 Assert.assertEquals(currentPageUrl, expectedPageUrl);
	 System.out.println("Landed in the product page");
	 
	 // click on the hamburger button 
	 driver.findElement(By.xpath("//div[@class='bm-burger-button']")).click();
	 String inventorySidebarName=driver.findElement(By.id("inventory_sidebar_link")).getText();
	 if(inventorySidebarName.equals("All Items"))
	 {
		 System.out.println("Hamburger button is clicked");
	 }
	 else
	 {
		 System.out.println("Hamburger button is not clicked");
	 }
	 
	 //Click on log out icon 
	 driver.findElement(By.id("logout_sidebar_link")).click();
	 driver.findElement(By.id("login-button")).isDisplayed();
	}
	
	@AfterTest
	public void quit()
	{
		driver.close();
	}
}



