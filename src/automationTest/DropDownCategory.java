package automationTest;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;


public class DropDownCategory {
	private static WebDriver driver = null;		
	
	 @BeforeClass
     public static void openBrowser(){
		 System.setProperty("webdriver.gecko.driver","C:\\SeleniumGecko\\geckodriver.exe");	
         driver = new FirefoxDriver();
         //wait for page to load
         driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		} 
	 
	 /**
	  * test the single-select drop-down category under "Fordon" page
	  * @throws InterruptedException
	  */
	 @Test
	 public void checkDropDownCategory() throws InterruptedException {
		 System.out.println("Starting test " + new Object(){}.getClass().getEnclosingMethod().getName());		
	     //go to the website's page with cars
		 driver.get("https://www.blocket.se/stockholm/fordon?ca=11&cg=1000");
		 
		//find the category drop-down
			Select oSelect = new Select(driver.findElement(By.id("catgroup")));
	 
		// select option 'Bilar' 
			oSelect.selectByVisibleText("Bilar");
	 
		// make changes noticeable		
			Thread.sleep(3000);
			
	 
		// select another option
			oSelect.selectByVisibleText("Verktyg");
			Thread.sleep(3000);
	 
			//print all the options for the selected drop down and select one option of your choice			
			List<WebElement> options = oSelect.getOptions();
			// Get the size of the Select element
			int listSize = options.size();
	 
			// loop through the list length and print all the options
			for(int i =0; i < listSize ; i++){
				// Storing the value of the option	
				String categoryOption = oSelect.getOptions().get(i).getText();
				
				System.out.println(categoryOption);
				// Putting a check on each option that if any of the option is equal to 'Africa" then select it 
				if(categoryOption.equals("Motorcyklar")){
					oSelect.selectByIndex(i);
					break;
					}
				}	    
		 
	 }
	 
	 
	 @AfterClass
	 public static void closeBrowser(){
		 driver.quit();
	 }
	

}
