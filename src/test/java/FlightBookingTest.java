import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.os.WindowsUtils;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Base.Utilities;
import Pages.HomePage;

public class FlightBookingTest extends Utilities {

	public HomePage homePage;

	@BeforeMethod
	public void setup() throws Exception {
		initialize();
		homePage = new HomePage();
	}

	@AfterMethod
	public void tearDown(ITestResult result) throws IOException {

		driver.quit();
		WindowsUtils.killByName("chromedriver.exe");
	}

	@Test
	public void testThatResultsAppearForAOneWayJourney() throws IOException {

		waitFor(2000);
		homePage.bookingOptions.click();
		homePage.fromTag.clear();
		homePage.fromTag.sendKeys(getMessageProperty("Origin.City"));
		// wait for the auto complete options to appear for the origin
		waitFor(6000);
		homePage.origin.click();
		waitFor(6000);
		homePage.toTag.clear();
		homePage.toTag.sendKeys(getMessageProperty("Destination.City"));
		// wait for the auto complete options to appear for the origin
		waitFor(6000);
		homePage.destination.click();
		waitFor(5000);
		selectDate(getMessageProperty("Travel.Month"), getMessageProperty("Travel.Date"));
		// all fields filled in. Now click on search
		homePage.searchFlightsBtn.click();
		waitFor(6000);
		// verify that result appears for the provided journey search
		assertTrue(isElementPresent(By.className("searchSummary")), "Search results not retrieved for entered quuery.");

	}
}
