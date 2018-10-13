package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Base.Utilities;

public class FlightSearchPage extends Utilities{
	
	public FlightSearchPage() {
	System.out.println("we are in home page cunstructor");
	PageFactory.initElements(driver,this);
	
	}

    @FindBy(id ="OneWay")
    public WebElement bookingOptions; 

   @FindBy(id ="FromTag")
    public WebElement fromTag;
    
    @FindBy(id ="ui-id-1")
    public WebElement origin;
    
    @FindBy(id ="ToTag")
    public WebElement toTag;
    
    @FindBy(id ="ui-id-2")
    public WebElement destination;
    
    @FindBy(tagName ="li")
    public WebElement getAllOptions;
    
    @FindBy(id ="SearchBtn")
    public WebElement searchFlightsBtn;

    @FindBy(xpath ="//div[@class='notification clearFix']/p/small")
    public WebElement HotelSearchResults;
    
    @FindBy(id ="ui-id-3")
    public WebElement hotelLocation;
    
}
