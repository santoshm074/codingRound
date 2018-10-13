package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Base.Utilities;

public class HotelSearchPage extends Utilities{
	
	public HotelSearchPage() {
	System.out.println("we are in home page cunstructor");
	PageFactory.initElements(driver,this);
	
	}

    @FindBy(linkText = "Hotels")
	public WebElement hotelLink;

    @FindBy(id = "Tags")
    public WebElement localityTextBox;

    @FindBy(id = "travellersOnhome")
    public WebElement travellerSelection;
    
    @FindBy(xpath ="//div[@class='notification clearFix']/p/small")
    public WebElement HotelSearchResults;
    
    @FindBy(id ="ui-id-3")
    public WebElement hotelLocation;
    
    @FindBy(id = "SearchHotelsButton")
    public WebElement searchButton;
}
