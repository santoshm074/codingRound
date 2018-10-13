package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Base.Utilities;

public class HomePage extends Utilities{
	
	public HomePage() {
	System.out.println("we are in home page cunstructor");
	PageFactory.initElements(driver,this);
	
	}

    @FindBy(id ="OneWay")
    public WebElement bookingOptions; 

    @FindBy(linkText = "Hotels")
	public WebElement hotelLink;

    @FindBy(id = "Tags")
    public WebElement localityTextBox;

    @FindBy(id = "SearchHotelsButton")
    public WebElement searchButton;

    @FindBy(id = "travellersOnhome")
    public WebElement travellerSelection;
    
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
    
    @FindBy(linkText = "Your trips")
	public WebElement yourTripLink;
    
    @FindBy(id ="SignIn")
    public WebElement signIn;
    
    @FindBy(id ="signInButton")
    public WebElement signInBtn;
    
    @FindBy(id ="errors1")
    public WebElement errorMsg;
    
    @FindBy(id ="SearchBtn")
    public WebElement searchFlightsBtn;

    @FindBy(xpath ="//div[@class='notification clearFix']/p/small")
    public WebElement HotelSearchResults;
    
    @FindBy(id ="ui-id-3")
    public WebElement hotelLocation;
    
}
