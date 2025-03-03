package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class HomePage extends BasePage{

	public HomePage(WebDriver driver) {
		super(driver);
	}

	// Locators
	@FindBy(how = How.XPATH, using = "//a[@title='My Account']")
	WebElement lnkMyaccount;

	@FindBy(how = How.XPATH, using = "//a[normalize-space()='Register']")
	WebElement lnkRegister;

	@FindBy(how = How.XPATH, using = "//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Login']")
	WebElement lnkLogin;

	@FindBy(how = How.XPATH, using = "//input[@placeholder='Search']")
	WebElement tbSearch;

	@FindBy(how = How.XPATH, using = "//button[@class='btn btn-default btn-lg']")
	WebElement btnSearch;

	@FindBy(how = How.XPATH, using = "//button[@class='btn btn-inverse btn-block btn-lg dropdown-toggle']")
	WebElement btnCart;

	@FindBy(how = How.XPATH, using = "//strong[normalize-space()='View Cart']")
	WebElement lnkviewCart;

	// Action methods
	public void clickMyAccount() {
		lnkMyaccount.click();
	}

	public void clickRegister() {
		lnkRegister.click();
	}

	public void clickLogin() {
		lnkLogin.click();
	}

	public void enterSearchText(String text) {
		tbSearch.sendKeys(text);
	}

	public void clickSearch() {
		btnSearch.click();
	}

	public void clickCart() {
		btnCart.click();
	}

	public void clickViewcart() {
		lnkviewCart.click();
	}

	
}
