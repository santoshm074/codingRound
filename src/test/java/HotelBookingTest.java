import java.io.IOException;
import org.openqa.selenium.os.WindowsUtils;
import org.openqa.selenium.support.ui.Select;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Base.Utilities;
import Pages.HomePage;

public class HotelBookingTest extends Utilities{
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
    public void testShouldBeAbleToSearchForHotels() throws IOException {
    	//Click on Hotel link
        homePage.hotelLink.click();
        //Enter the location details
        homePage.localityTextBox.sendKeys(getMessageProperty("Locality.Details"));
        //Select the person details
        new Select(homePage.travellerSelection).selectByVisibleText(getMessageProperty("Traveller.Details"));
        homePage.searchButton.click();

    }
}
