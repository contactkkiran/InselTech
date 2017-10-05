import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;
//package com.techbeamers;
import java.util.concurrent.TimeUnit;

//--
import org.openqa.selenium.By;
//--
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//--
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.thoughtworks.selenium.Selenium;
import com.thoughtworks.selenium.webdriven.WebDriverBackedSelenium;

public class MailCreateTest {
	private WebDriver driver;
	public String defaultUser = null;

	public int count = 0;

	@BeforeClass
	public void beforeClass() throws InterruptedException {
		// driver = new FirefoxDriver();

		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		driver = new ChromeDriver();
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	@Test
	public void verifySearchButtonTest() throws InterruptedException {
		getuserinfo();
		System.out.println("Inside Test Case");

		Thread.sleep(2000);

		// Enter User Name
		for (int i = 1; i <= count; i++) {
			login();
			// defaultUser = defaultUser + i;
			driver.navigate().refresh();
			Thread.sleep(10000);
			// driver.findElement(By.id("add_email_account")).click();
//			driver.navigate().refresh();
//			Thread.sleep(5000);

			// A "base url", used by selenium to resolve relative URLs
			String baseUrl = "http://inseltech.com.mx:2082";

			// Create the Selenium implementation used selenium backed webdriver
			// as during execution exception might occur if the element is not
			// enabled .Selenium handles this situation. Only in this case we
			// use selenium rest of the scripts webdriver api

			// driver.findElement(By.id("add_email_account")).sendKeys(defaultUser
			// + i);

			// Generate Random password

			MyStringRandomGen msr = new MyStringRandomGen();
			String password = msr.generateRandomString();
			System.out.println("Random password Generated is : " + defaultUser + i + password);
			try {// Emter Password
				Thread.sleep(5000);
				driver.findElement(By.id("add_email_password1")).sendKeys(password);
				Thread.sleep(5000);
				// Renter the password
				driver.findElement(By.id("add_email_password2")).sendKeys(password);
				Thread.sleep(5000);
				Selenium selenium = new WebDriverBackedSelenium(driver, baseUrl);
				try {
					selenium.type("id=add_email_account", defaultUser + i);
				} catch (Exception e) {
					// TODO: handle exception
					driver.navigate().refresh();
					Thread.sleep(5000);
				}
				// Clicl on redio button
				driver.findElement(By.id("quota_unlimited")).click();
				Thread.sleep(5000);
				// Click on Add Email button
				driver.findElement(By.id("add_email_create")).click();
				Thread.sleep(20000);
				System.out.println("Successfully created email for user:" + defaultUser + i);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				System.out.println("");
			} finally {
//				driver.navigate().refresh();				
//				Thread.sleep(5000);driver.findElement(By.id("add_email_password1")).sendKeys(password);
//				Thread.sleep(5000);				
//				// Renter the password
//				driver.findElement(By.id("add_email_password2")).sendKeys(password);
//				Thread.sleep(5000);
//				// Clicl on redio button
//				driver.findElement(By.id("quota_unlimited")).click();
//				Thread.sleep(5000);
//
//				Selenium selenium = new WebDriverBackedSelenium(driver, baseUrl);
//				try {
//					selenium.type("id=add_email_account", defaultUser + i);
//				} catch (Exception e) {
//					// TODO: handle exception
//					driver.navigate().refresh();
//					Thread.sleep(5000);
//				}
//				// Click on Add Email button
//				driver.findElement(By.id("add_email_create")).click();
//				Thread.sleep(20000);
//				System.out.println("*********Successfully created email for user:" + defaultUser + i);

			}

			// defaultUser
		}

	}

	private void login() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("http://inseltech.com.mx:2082/");
		driver.manage().window().maximize();
		// String search_text = "Google Search";
		Thread.sleep(2000);

		// driver.navigate().refresh();
		Thread.sleep(2000);

		// Clear this user text filed as openint the login page retains the old
		// login name hence Clear
		driver.findElement(By.id("user")).clear();
		driver.findElement(By.id("user")).sendKeys("inseltec");
		Thread.sleep(2000);

		driver.findElement(By.id("pass")).clear();
		driver.findElement(By.id("pass")).sendKeys("Gutierrez26");
		Thread.sleep(2000);
		driver.findElement(By.id("login_submit")).click();

		Thread.sleep(2000);
		System.out.println("Successfy Aboout to Click on Email Account lik");
		driver.findElement(By.id("item_email_accounts")).click();
		System.out.println("Successfully Clicke on in Email account link");

	}

	private void getuserinfo() {
		try {

			File file = new File("login.properties");
			FileInputStream fileInput = new FileInputStream(file);
			Properties properties = new Properties();
			properties.load(fileInput);
			fileInput.close();

			Enumeration enuKeys = properties.keys();
			while (enuKeys.hasMoreElements()) {
				String key = (String) enuKeys.nextElement();
				defaultUser = key;
				String value = properties.getProperty(key);
				int runCount = Integer.parseInt(value);
				count = runCount;
				System.out.println(key + ": " + value);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
