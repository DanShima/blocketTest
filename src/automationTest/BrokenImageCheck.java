package automationTest;

import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;


public class BrokenImageCheck {
	private static WebDriver driver;
	private static int invalidImageCount;
	
	public static void main(String[] args) {
		
		System.setProperty("webdriver.gecko.driver","C:\\SeleniumGecko\\geckodriver.exe");		
		setUp();
		checkForBrokenImage();
		tearDown();
	}

	/**
	 * open Firefox browser and go to the website
	 */
	public static void setUp() {
		driver = new FirefoxDriver();
		driver.get("https://www.blocket.se/");
	}

	/**
	 * find all images on a page and check for invalid images
	 */
	public static void checkForBrokenImage() {
		try {
			invalidImageCount = 0;
			//find all images on the page
			List<WebElement> imagesList = driver.findElements(By.tagName("img"));
			System.out.println("Total number of images are: " + imagesList.size());
			//go through all images and check for invalidity
			for (WebElement imgElement : imagesList) {
				if (imgElement != null) {
					verifyimageActive(imgElement);
				}
			}
			System.out.println("Total number of invalid images are "	+ invalidImageCount);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

	/**
	 * close the driver
	 */
	public static void tearDown() {		
			driver.quit();
	}
	
	/**
	 * use HTTPClient library to check image status. 
	 * @param imgElement 
	 */
	protected static void verifyimageActive(WebElement imgElement) {
		try {
			HttpClient client = HttpClientBuilder.create().build();
			HttpGet request = new HttpGet(imgElement.getAttribute("src"));
			HttpResponse response = client.execute(request);
			// iterate through each image and verify response code with HttpStatus 
			// invalid images count is incremented if HttpStatus is not 200
			if (response.getStatusLine().getStatusCode() != 200)
				invalidImageCount++;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
