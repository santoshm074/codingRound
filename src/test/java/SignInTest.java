import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.openqa.selenium.os.WindowsUtils;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import Base.Utilities;
import Pages.HomePage;

public class SignInTest extends Utilities{
	
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
    public void testShouldThrowAnErrorIfSignInDetailsAreMissing() throws IOException {
    	//Click on Your trip options
    	homePage.yourTripLink.click();
    	waitFor(2000);
    	homePage.signIn.click();
    	switchToFrame("modal_window");
    	homePage.signInBtn.click();
    	waitFor(5000);
        String errors1 = homePage.errorMsg.getText();
        //Validate the error message
        assertTrue(errors1.contains(getMessageProperty("SignIn.ErrorMsg")),
				"Error message not displayed to user");
    }
}
