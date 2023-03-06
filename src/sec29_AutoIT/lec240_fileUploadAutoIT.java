package sec29_AutoIT;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class lec240_fileUploadAutoIT {

	public static void main(String[] args) throws InterruptedException, IOException {

		String downloadPath = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver", "D:\\Grid\\chromedriver.exe");
		
		HashMap<String, Object> chromePref = new HashMap<String, Object>();
		chromePref.put("profile.default_content_settings.popups", 0);
		chromePref.put("download.default_directory", downloadPath);
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", chromePref);
				
		WebDriver driver = new ChromeDriver();
		driver.get("https://pdf2jpg.net/");
		driver.manage().window().maximize();
		driver.findElement(By.id("advanced_pdf_file")).click();
		Thread.sleep(2000);
		Runtime.getRuntime().exec("D:\\SeleniumWebDriver\\fileUpload.exe");
		Thread.sleep(5000);
		System.out.println("File Upload :-  " + driver.findElement(By.cssSelector("label[for='submit-pdf']")).getText());
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(1000) );
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("convert_pdf_to_jpg_button")));
		driver.findElement(By.id("convert_pdf_to_jpg_button")).click();
		Thread.sleep(5000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("processing_status")));
		System.out.println("File Convert :- " + driver.findElement(By.id("processing_status")).getText());
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("View")));
		driver.findElement(By.linkText("View")).click();
		Thread.sleep(5000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[title='Close']")));
		driver.findElement(By.cssSelector("a[title='Close']")).click();
//		driver.findElement(By.linkText("Download")).click();
//		Thread.sleep(5000);
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dismiss-button")));
//		driver.findElement(By.id("dismiss-button")).click();
//		File f = new File (downloadPath+"\\Appium-MobileTesting-Pravin-page-001.jpg");
//		if (f.exists()) {
//			System.out.println("File Download :- File is Sucessfully download..!");
//		}
//		
//		Thread.sleep(1000);
		driver.close();
	}
}
