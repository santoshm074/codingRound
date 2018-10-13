package Base;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.testng.Reporter;

import com.sun.javafx.PlatformUtil;

public class Utilities {
	protected static WebDriver driver;
	static ChromeDriverService service;
	static String resourcePath;

	public static String getEnvironmentProperty(String property) throws IOException {
		
		Properties prop = new Properties();
		String OS = "Linux";
		FileInputStream fis;
		if(OS.equalsIgnoreCase("Linux")){
				fis = new FileInputStream(
				System.getProperty("user.dir") + "/Resources/env.properties");
		}
		else{
				fis = new FileInputStream(
				System.getProperty("user.dir") + "\\Resources\\env.properties");
		}
		prop.load(fis);
		return prop.getProperty(property);
	}

	@SuppressWarnings("deprecation")
	public static void initialize() throws IOException, InterruptedException {

		if(getEnvironmentProperty("OS").equals("Linux")){
			resourcePath = "/Resources/";
			 }
		else{
			resourcePath = "\\Resources\\";
			}
		
		System.out.println("TEST STARTUP.....");
		if (getEnvironmentProperty("browser").equals("chrome")) {
			service = new ChromeDriverService.Builder()
					.usingDriverExecutable(
							new File(System.getProperty("user.dir")+ resourcePath+"chromedriver.exe"))
					.usingAnyFreePort().build();
			
			service.start();
			driver = new RemoteWebDriver(service.getUrl(), DesiredCapabilities.chrome());

		} else if (getEnvironmentProperty("browser").equals("ie")) {
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+ resourcePath + "IEDriverServer.exe");
			// driver = new InternetExplorerDriver();
			DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
			capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			capabilities.setCapability("requireWindowFocus", true);
			driver = new InternetExplorerDriver(capabilities);
		} else if (getEnvironmentProperty("browser").equals("firefox")) {
			System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+ resourcePath +"geckodriver.exe");
			FirefoxProfile profile = new FirefoxProfile();
			profile.setAcceptUntrustedCertificates(true);
			profile.setAssumeUntrustedCertificateIssuer(false);
			driver = new FirefoxDriver();
		}

		else if (getEnvironmentProperty("browser").equals("safari")) {
			SafariOptions options = new SafariOptions();
			driver = new SafariDriver(options);
		} 

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(getEnvironmentProperty("url"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		System.out.println(("Initialization successfully done"));
	}

	public static String getMessageProperty(String property) throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + resourcePath + "messages.properties");
		prop.load(fis);
		return prop.getProperty(property);
	}

	
	public void waitFor(int durationInMilliSeconds) {
        try {
            Thread.sleep(durationInMilliSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }


   public boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
	
	// Replacing existing method
	/*public static boolean isElementPresent(By by) {

		try {
			List<WebElement> ElementList = driver.findElements(by);
			if (ElementList.isEmpty()) {

				return false;
			} else {
				return true;
			}
		} catch (NoSuchElementException e) {

			System.out.println("The Elements are not present");
		}
		return false;
	}*/

	public void setDriverPath() {
		if (PlatformUtil.isMac()) {
			System.setProperty("webdriver.chrome.driver", "chromedriver");
		}
		if (PlatformUtil.isWindows()) {
			System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		}
		if (PlatformUtil.isLinux()) {
			System.setProperty("webdriver.chrome.driver", "chromedriver_linux");
		}
	}
	
	public void switchToFrame(final String frameName) {
		try {
			driver.switchTo().frame(frameName);
		} catch (final NoSuchFrameException ne) {
			Reporter.log("NoSuchFrameException " + ne.getMessage());
		}
	}

	public static boolean selectDate(String month, String date) {
		String currentMonth = driver
				.findElement(By.xpath("//div[@class='monthBlock first']//span[@class='ui-datepicker-month']"))
				.getText();
		if (currentMonth.contains(month) == false) {
			do {
				driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/div[2]/div/a")).click();
			} while (currentMonth.contains(month));

		}
		WebElement table = driver.findElement(By.xpath("//div[@class='monthBlock first']"));
		List<WebElement> tableRows = table.findElements(By.tagName("tr"));
		for (WebElement row : tableRows) {
			List<WebElement> colums = row.findElements(By.tagName("td"));

			for (WebElement clm : colums) {
				clm.getText();
				if (clm.getText().contentEquals(date)) {
					clm.click();
					return true;
				}

			}

		}
		return false;
	}
}
