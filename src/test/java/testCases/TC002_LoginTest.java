package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass {

	@Test(groups={"sanity", "master"})
	public void verify_login() {
		logger.info("*********** Starting of the test case : verify_login ***********");

		try {
		HomePage homePage = new HomePage(driver);
		homePage.clickMyAccount();
		homePage.clickLogin();

		LoginPage loginPage = new LoginPage(driver);
		loginPage.setEmail(prop.getProperty("email"));
		loginPage.setPassword(prop.getProperty("password"));
		loginPage.clickLoginButton();

		MyAccountPage myaccount = new MyAccountPage(driver);
		Boolean myaccount_loaded = myaccount.isMyAccountPageExists();

		if (myaccount_loaded == true) {
			Assert.assertTrue(myaccount_loaded);
			logger.info("*********** Login successful : verify_login ***********");
			myaccount.clickLogout();
		} else {
			Assert.fail();
			logger.info("*********** Login failed : verify_login ***********");
		}
		driver.close();
		}
		catch(Exception e) {
			Assert.fail();
			logger.info("*********** Login failed : verify_login ***********");
		}

	}

}
