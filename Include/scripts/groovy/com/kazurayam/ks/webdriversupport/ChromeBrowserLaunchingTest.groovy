package com.kazurayam.ks.webdriversupport

import static org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver

import com.kazurayam.webdriverfactory.chrome.ChromeDriverFactory
import com.kazurayam.webdriverfactory.chrome.ChromeDriverFactory.UserDataAccess
import com.kazurayam.webdriverfactory.chrome.ChromeOptionsModifier
import com.kazurayam.webdriverfactory.chrome.ChromeOptionsModifiers
import com.kazurayam.webdriverfactory.chrome.ProfileDirectoryName

import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kazurayam.webdriverfactory.UserProfile

@RunWith(JUnit4.class)

public class ChromeBrowserLaunchingTest {

	private WebDriver driver
	private String url = "http://demoaut.katalon.com"

	@Before
	void setup() {
		driver = null
		ChromeDriverFactory.setPathToChromeDriverExecutable(DriverFactory.getChromeDriverPath())
	}

	@After
	void quitWebDriver() {
		WebUI.delay(1)
		if (driver != null) {
			driver.quit()
		}
	}

	
	@Test
	public void test_launch_Chrome() {
		ChromeDriverFactory factory = ChromeDriverFactory.newChromeDriverFactory()
		driver = factory.newChromeDriver()
		assertNotNull(driver)
		DriverFactory.changeWebDriver(driver)
		WebUI.navigateToUrl(url)
	}

	
	@Test
	public void test_launch_HeadlessChrome() {
		ChromeDriverFactory factory = ChromeDriverFactory.newHeadlessChromeDriverFactory()
		driver = factory.newChromeDriver()
		assertNotNull(driver)
		DriverFactory.changeWebDriver(driver)
		WebUI.navigateToUrl(url)
	}

	
	@Test
	public void test_launch_Chrome_with_profile() {
		ChromeDriverFactory factory = ChromeDriverFactory.newChromeDriverFactory()
		driver = factory.newChromeDriver(new UserProfile('Katalon'))
		assertNotNull(driver)
		DriverFactory.changeWebDriver(driver)
		WebUI.navigateToUrl(url)
	}
	
	
	@Test
	public void test_launch_HeadlessChrome_with_profile() {
		ChromeDriverFactory factory = ChromeDriverFactory.newHeadlessChromeDriverFactory()
		driver = factory.newChromeDriver(new UserProfile('Katalon'))
		assertNotNull(driver)
		DriverFactory.changeWebDriver(driver)
		WebUI.navigateToUrl(url)
	}
	
	
	@Test
	public void test_launch_Chrome_with_profileDirectory() {
		ChromeDriverFactory factory = ChromeDriverFactory.newChromeDriverFactory()
		driver = factory.newChromeDriver(new ProfileDirectoryName('Default'))
		assertNotNull(driver)
		DriverFactory.changeWebDriver(driver)
		WebUI.navigateToUrl(url)
	}
	
	
	@Test
	public void test_launch_HeadlessChrome_with_profileDirectory() {
		ChromeDriverFactory factory = ChromeDriverFactory.newHeadlessChromeDriverFactory()
		driver = factory.newChromeDriver(new ProfileDirectoryName('Default'))
		assertNotNull(driver)
		DriverFactory.changeWebDriver(driver)
		WebUI.navigateToUrl(url)
	}
	
	@Ignore
	@Test
	public void test_launch_HeadlessChrome_with_profile_FOR_HERE() {
		ChromeDriverFactory factory = ChromeDriverFactory.newHeadlessChromeDriverFactory()
		driver = factory.newChromeDriver(new UserProfile('Katalon'), UserDataAccess.FOR_HERE)
		assertNotNull(driver)
		DriverFactory.changeWebDriver(driver)
		WebUI.navigateToUrl(url)
		WebUI.delay(1)
	}
	
	@Test
	public void test_launch_Chrome_with_ChromeOptions() {
		ChromeDriverFactory factory = ChromeDriverFactory.newChromeDriverFactory()
		factory.addChromeOptionsModifier(ChromeOptionsModifiers.disableExtensions())
		driver = factory.newChromeDriver()
		assertNotNull(driver)
		DriverFactory.changeWebDriver(driver)
		WebUI.navigateToUrl(url)
	}
}
