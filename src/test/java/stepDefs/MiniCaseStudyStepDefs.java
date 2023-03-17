package stepDefs;

import java.time.Duration;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class MiniCaseStudyStepDefs  {
static WebDriver driver;
	
	@Given("User is on Launch Page")
	public static void user_is_on_launch_page() {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.get("https://www.demoblaze.com/index.html");
	}
	@When("User Login")
	public void user_login() {
		driver.findElement(By.xpath("//a[@id='login2']")).click();
	    driver.findElement(By.xpath("//input[@id='loginusername']")).sendKeys("Uvaneswar");
	    driver.findElement(By.xpath("//input[@id='loginpassword']")).sendKeys("Uvan123");
	    driver.findElement(By.xpath("//button[@onclick='logIn()']")).click();
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	}
	@Then("Should display Home Page")
	public void should_display_home_page() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		boolean isDisp  = driver.findElement(By.xpath("//a[contains(text(),'Welcome Uvaneswar')]")).isDisplayed();
		Assert.assertTrue(isDisp);
	}
	
	@When("Add an item to cart")
	public void add_an_item_to_cart(io.cucumber.datatable.DataTable dataTable) {
		driver.navigate().to("https://www.demoblaze.com/index.html");
	    List<Map<String,String>> data=dataTable.asMaps();
	    for(int i=0;i<data.size();i++) {
	    String categories=data.get(i).get("cat");
	    String items=data.get(i).get("item");
	    String strpath="//a[contains(text(),'"+categories+"')]";
	    driver.findElement(By.xpath(strpath)).click();
	    String stritem="//a[contains(text(),'"+items+"')]";
	    driver.findElement(By.xpath(stritem)).click();
	    driver.findElement(By.xpath("//a[@class='btn btn-success btn-lg']")).click();
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.alertIsPresent());
	    Alert alert = driver.switchTo().alert();
		alert.accept();
		driver.navigate().to("https://www.demoblaze.com/index.html");
	    }
	}
	@Then("Items must be added to cart")
	public void items_must_be_added_to_cart() {
		driver.findElement(By.xpath("//a[@id='cartur']")).click();
	    WebDriverWait waits = new WebDriverWait(driver, Duration.ofSeconds(30));
	    waits.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//td[2]")));
	    boolean isDisp  = driver.findElement(By.xpath("//td[2]")).isDisplayed();
		Assert.assertTrue(isDisp);
	}
	
	@When("List of Items should be available in cart")
	public void list_of_items_should_be_available_in_cart() {
	    WebDriverWait waits = new WebDriverWait(driver, Duration.ofSeconds(30));
	    waits.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//td[2]")));
	    boolean isDisp  = driver.findElement(By.xpath("//td[2]")).isDisplayed();
		Assert.assertTrue(isDisp);
	}
	@Then("Delete an item from Cart")
	public void delete_an_item_from_cart() {
		String Befpath = "//h3[@class='panel-title']"; 
		  driver.findElement(By.xpath(Befpath)).getText();
		driver.findElement(By.xpath("//td//a[contains(text(),'Delete')]")).click();
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));  
		  String Aftpath = "//h3[@class='panel-title']"; 
		  driver.findElement(By.xpath(Aftpath)).getText();
		  if(Befpath!=Aftpath) {
				Assert.assertTrue(Befpath!=Aftpath);
				  }

	}
	
	@When("Items should be available in Cart")
	public void items_should_be_available_in_cart() {
		driver.navigate().to("https://www.demoblaze.com/cart.html");
	    WebDriverWait waits = new WebDriverWait(driver, Duration.ofSeconds(30));
	    waits.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//td[1]")));
	    boolean isDisp  = driver.findElement(By.xpath("//td[1]")).isDisplayed();
		Assert.assertTrue(isDisp);
	}
	@Then("Purchase Items")
	public void purchase_items() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.findElement(By.xpath("//button[@class='btn btn-success']")).click();
		  driver.findElement(By.xpath("//input[@id='name']")).sendKeys("Uvaneswar");
		  driver.findElement(By.xpath("//input[@id='country']")).sendKeys("India");
		  driver.findElement(By.xpath("//input[@id='city']")).sendKeys("Neyveli");
		  driver.findElement(By.xpath("//input[@id='card']")).sendKeys("12345678");
		  driver.findElement(By.xpath("//input[@id='month']")).sendKeys("March");
		  driver.findElement(By.xpath("//input[@id='year']")).sendKeys("2023");
		  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		  driver.findElement(By.xpath("//button[contains(text(),'Purchase')]")).click();
		  boolean isDisp2  = driver.findElement(By.xpath("//h2[contains(text(),'Thank you for your purchase!')]")).isDisplayed();
		  Assert.assertTrue(isDisp2);
	}

}
