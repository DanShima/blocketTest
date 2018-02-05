package automationTest;
import java.util.concurrent.TimeUnit;

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
		
	
	
	 @BeforeClass
     public static void openBrowser(){
		 System.setProperty("webdriver.gecko.driver","C:\\SeleniumGecko\\geckodriver.exe");	
         driver = new FirefoxDriver();
         driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		} 
	 
	/**
	 * testing log in with user credentials
	 */
	@Test
	public void logInTest() {
		System.out.println("Starting test " + new Object(){}.getClass().getEnclosingMethod().getName());		
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
			 element = driver.findElement (By.cssSelector("a[href='https://www.blocket.se/spid/logout']"));
			 element.click();
			 System.out.println("found log out");
		 }catch (Exception e){
			}
	     Assert.assertNotNull(element);
	     System.out.println("Ending test " + new Object(){}.getClass().getEnclosingMethod().getName());
    }
	
	/**
	 * testing log in with fake, unregistered email and password
	 */	
	 @Test
     public void invalidLogInTest() {
     
		 System.out.println("Starting test " + new Object(){}.getClass().getEnclosingMethod().getName());
	     driver.get("https://www.blocket.se/konto/senaste_sidan");	
	     driver.findElement(By.id("CredIdentifier")).sendKeys("fakeemail@hotmail.com");
	     driver.findElement(By.id("CredPassword")).sendKeys("fake123");
	     driver.findElement(By.xpath("//button[@type='submit']")).click();	
	     try{
			element = driver.findElement (By.cssSelector("a[href='https://www.blocket.se/spid/logout']"));
	     }catch (Exception e){
			}
	     Assert.assertNull(element);
	     System.out.println("Ending test " + new Object(){}.getClass().getEnclosingMethod().getName());
     }
	
	
	 @AfterClass
	 public static void closeBrowser(){
		 driver.quit();
	 }
	
	
		
	}
		


