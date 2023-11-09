package brokenlink1;

import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class brokenlink {

	
	    public static void main(String[] args) {


	        WebDriver driver = new ChromeDriver();
	        driver.manage().window().maximize();
	      // driver.get("https://www.myntra.com/"); // Replace this URL with the URL you want to check for broken links
	        driver.get("https://www.meesho.com/");
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	        List<WebElement> links = driver.findElements(By.tagName("a"));

	        System.out.println("Total links found: " + links.size());

	        for (WebElement link : links) {
	            String url = link.getAttribute("href");
	            try {
	                HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
	                connection.connect();
	                int responseCode = connection.getResponseCode();
	                connection.disconnect();

	                if (responseCode != 200) {
	                    System.out.println("Broken link found: " + url + " - Response Code: " + responseCode);
	                } else {
	                    System.out.println("Valid link: " + url);
	                }
	            } catch (Exception e) {
	                System.out.println("Exception occurred while checking the link: " + url);
	            }
	        }

	        driver.quit();
	    }
	}



	


