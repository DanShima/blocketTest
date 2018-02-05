package automationTest;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;



public class FirstTestCase {
	private static WebDriver driver = null;	
	private static WebElement element;
	
	public static void main(String[] args) {
		
		System.setProperty("webdriver.gecko.driver","C:\\SeleniumGecko\\geckodriver.exe");
		
		logInTest();		
        // Close the driver
        driver.quit();
	}
	
	
	public static void logInTest() {
		//open Firefox browser
        driver = new FirefoxDriver();
        //launch Blocket website
        driver.get("https://www.blocket.se/");
        //go to the login page
        driver.get("https://www.blocket.se/konto/senaste_sidan");
        
        // Print a Log In message to the screen
        System.out.println("Successfully opened the login page");     
        
		driver.findElement(By.id("CredIdentifier")).sendKeys("giddy_with_panda@hotmail.com");
		driver.findElement(By.id("CredPassword")).sendKeys("woaini88!");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		try{
			element = driver.findElement (By.xpath(".//*[@href='https://www.blocket.se/spid/logout']/a"));
		 }catch (Exception e){
			}
	     Assert.assertNotNull(element);
	     System.out.println("Ending test " + new Object(){}.getClass().getEnclosingMethod().getName());
    }
		
	}
		

}
