import static org.testng.Assert.assertTrue;

import java.io.IOException;
import org.openqa.selenium.os.WindowsUtils;
import org.openqa.selenium.support.ui.Select;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Base.Utilities;
import Pages.HotelSearchPage;

public class HotelBookingTest extends Utilities{
	public HotelSearchPage hotelSearchPage;

	@BeforeMethod
	public void setup() throws Exception {
		initialize();
		hotelSearchPage = new HotelSearchPage();

	}
	
	@AfterMethod
	public void tearDown(ITestResult result) throws IOException {
		
			driver.quit();
			WindowsUtils.killByName("chromedriver.exe");
	}

    @Test
    public void testShouldBeAbleToSearchForHotels() throws IOException {
    	//Click on Hotel link
    	hotelSearchPage.hotelLink.click();
        //Enter the location details
    	hotelSearchPage.localityTextBox.sendKeys(getMessageProperty("Locality.Details"));
        waitFor(4000);
        hotelSearchPage.hotelLocation.click();
        waitFor(3000);
        //Select the person details
        new Select(hotelSearchPage.travellerSelection).selectByVisibleText(getMessageProperty("Traveller.Details"));
        hotelSearchPage.searchButton.click();
        waitFor(6000);
        String SearchResults = hotelSearchPage.HotelSearchResults.getText();
        //Validate hotel search results page is displayed to user
        assertTrue(SearchResults.contains(getMessageProperty("Hotel.Search.Results.Msg")),
				"Hotel search results not found");

    }
}
