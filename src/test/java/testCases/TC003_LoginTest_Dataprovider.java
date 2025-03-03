package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginTest_Dataprovider extends BaseClass {

	@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class, groups={"regression","master"})
	public void verify_login(String email, String pass, String expectedResult) {
		logger.info("*********** Starting of the test case : verify_login ***********");

		try {
			HomePage homePage = new HomePage(driver);
			homePage.clickMyAccount();
			homePage.clickLogin();

			LoginPage loginPage = new LoginPage(driver);
			loginPage.setEmail(email);
			loginPage.setPassword(pass);
			loginPage.clickLoginButton();

			MyAccountPage myaccount = new MyAccountPage(driver);
			Boolean myaccount_loaded = myaccount.isMyAccountPageExists();

			if (expectedResult.equalsIgnoreCase("Valid")) {
				if (myaccount_loaded == true) {
					myaccount.clickLogout();
					Assert.assertTrue(myaccount_loaded);
					logger.info("*********** Login successful : verify_login ***********");
				} else {
					Assert.fail();
					logger.info("*********** Login failed : verify_login ***********");
				}
			}
			
			if (expectedResult.equalsIgnoreCase("Invalid")) {
				if (myaccount_loaded == false) {
					Assert.assertTrue(true);
					logger.info("*********** Login negative case verified : verify_login ***********");
				} else {
					Assert.fail();
					logger.info("*********** Login failed : verify_login ***********");
				}
			}

		} catch (Exception e) {
			Assert.fail();
			logger.info("*********** Login failed : verify_login ***********");
		}

	}

}
