package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener {
	
	public ExtentSparkReporter sparkReporter; // UI of the report
	public ExtentReports extent; // populates common information on the report
	public ExtentTest test; // Creating test case entries in the report and updates the status of the test methods
	
	String repName;
	
	// executes only once so we are setting the report data here
	public void onStart(ITestContext testContext) {
		
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		repName= "Test-Report-"+timeStamp+".html";
		
		sparkReporter = new ExtentSparkReporter(".\\reports\\"+repName);
		sparkReporter.config().setDocumentTitle("opencart Automation Report");
		sparkReporter.config().setReportName("opencart Functional testing");
		sparkReporter.config().setTheme(Theme.DARK);
		
		
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter); // connecting to the spark reported to extent
		
		//below details populated automatically for now we have hard coaded
		extent.setSystemInfo("Application", "opencart");
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("Sub Module", "Customer");
		extent.setSystemInfo("Tester Name", System.getProperty("user.name"));
		extent.setSystemInfo("Environment", "QA");
		
		String os=testContext.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("operating system",os);
		
		String browser= testContext.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser Name", "Chrome");
		
		List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
		if(!includedGroups.isEmpty()) {
			extent.setSystemInfo("Groups", includedGroups.toString());
		}
		
	}
	
	public void onTestSuccess(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName()); // create new entry in the report
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.PASS, result.getName()+ " got successfully executed"); // update status 

	}

	public void onTestFailure(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName()); // create new entry in the report
		test.assignCategory(result.getMethod().getGroups());
		
		test.log(Status.FAIL, "Test case FAILED is: " + result.getName()); // update status
		test.log(Status.INFO, result.getThrowable().getMessage()); // Error message from console is copied here
		
		try {
			String imgPath = new BaseClass().captureScreen(result.getName());
			test.addScreenCaptureFromPath(imgPath);
		} catch(Exception e1) {
			e1.printStackTrace();
		}
	}

	public void onTestSkipped(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName()); // create new entry in the report
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, result.getName()+ " got skipped"); // update status
		test.log(Status.INFO, result.getThrowable().getMessage());
	}

	public void onFinish(ITestContext context) {
		
		extent.flush();
		
		//To directly open the report
		String pathOfExtentReport = System.getProperty("user.dir")+"\\reports\\"+repName;
		File extentReport = new File(pathOfExtentReport);
		
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//sending report in mail to the team
		/*
		try {
			URL url = new URL("file:///"+System.getProperty("user.dir")+"\\reports\\+repName");
			
			//creating email message
			
			ImageHtmlEmail email = new ImageHtmlEmail();
			email.setDataSourceResolver(new DataSourceUrlResolver(url));
			email.setHostName("smtp.googlemail.com");
			email.setSmtpPort(465);
			email.setAuthenticator(new DefaultAuthenticator("sri.pothineni@gmail.com","password"));
			email.setSSLOnConnect(true);
			email.setFrom("sri.pothineni@gmail.com");
			email.setSubject("Test Results");
			email.setMsg("Please find the attched report...");
			email.addTo("sri.pothineni@gmail.com");
			email.attach(url, "extent report","please check the report");
			email.send();
			
		} catch (MalformedURLException | EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		*/
		
		
	}

	
	

}
