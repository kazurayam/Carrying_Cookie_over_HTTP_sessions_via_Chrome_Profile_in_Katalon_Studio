package com.kazurayam.webdriverfactory.ks

import static org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.openqa.selenium.WebDriver

import com.kazurayam.webdriverfactory.chrome.ChromeDriverFactory
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

@RunWith(JUnit4.class)
public class LaunchingChromeTest {

	private WebDriver driver
	private String url = "http://demoaut.katalon.com"

	@Before
	void setup() {
		driver = null
	}

	@After
	void quitWebDriver() {
		WebUI.delay(1)
		if (driver != null) {
			driver.quit()
		}
	}

	@Test
	public void test_Chrome_specifying_path_to_executable() {
		ChromeDriverFactory.setPathToChromeDriverExecutable(DriverFactory.getChromeDriverPath())
		ChromeDriverFactory factory = ChromeDriverFactory.newChromeDriverFactory()
		driver = factory.newChromeDriver()
		assertNotNull(driver)
		DriverFactory.changeWebDriver(driver)
		WebUI.navigateToUrl(url)
	}
}
