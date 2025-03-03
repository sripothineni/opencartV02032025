package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class MyAccountPage extends BasePage {

	public MyAccountPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(how = How.XPATH, using = "//h2[normalize-space()='My Account']")
	WebElement msgHeading;

	@FindBy(how = How.XPATH, using = "//a[@class='list-group-item'][normalize-space()='Logout']")
	WebElement lnkLogout;

	
	//Actions
	public boolean isMyAccountPageExists() {
		try {
			return msgHeading.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public void clickLogout() {
		lnkLogout.click();
	}

}
