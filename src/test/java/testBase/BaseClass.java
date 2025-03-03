package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

public class BaseClass {
	public static WebDriver driver;
	public Logger logger;
	public Properties prop;
	
	@BeforeClass(groups={"sanity", "master", "regression"})
	@Parameters({"os","browser"})
	public void setup(String os, String browser) throws IOException {
		
		//loading config properties file
		FileReader file = new FileReader("./src//test//resources//config.properties");
		prop = new Properties();
		prop.load(file);
		
		
		logger = LogManager.getLogger(this.getClass()); // log4j
		
		switch(browser.toLowerCase())
		{
		case "chrome" : driver = new ChromeDriver(); break;
		case "firefox" : driver = new FirefoxDriver(); break;
		case "edge" : driver = new EdgeDriver(); break;
		default : System.out.println("Invalid browser name...");
		return;
		}
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		// Reading URL from properties file
		driver.get(prop.getProperty("appUrl"));
		driver.manage().window().maximize();
	}

	@AfterClass(groups={"sanity", "master", "regression"})
	public void tearDown() {
		driver.close();

	}

	@SuppressWarnings("deprecation")
	public String randomString() {
		return RandomStringUtils.randomAlphabetic(5);
	}

	public String randomNumber() {
		return RandomStringUtils.randomNumeric(10);
	}

	public String randomAlphaNumaric() {
		return RandomStringUtils.randomAlphabetic(5) + "$" + RandomStringUtils.randomNumeric(5);
	}
	
	public String captureScreen(String testName) {

		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

		String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + testName + "_" + timeStamp
				+ ".png";
		File targetFile = new File(targetFilePath);

		sourceFile.renameTo(targetFile);

		return targetFilePath;

	}

}
