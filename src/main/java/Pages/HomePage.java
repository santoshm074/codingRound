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

    @FindBy(linkText = "Your trips")
	public WebElement yourTripLink;
    
    @FindBy(id ="SignIn")
    public WebElement signIn;
    
    @FindBy(id ="signInButton")
    public WebElement signInBtn;
    
    @FindBy(id ="errors1")
    public WebElement errorMsg;
   
}
