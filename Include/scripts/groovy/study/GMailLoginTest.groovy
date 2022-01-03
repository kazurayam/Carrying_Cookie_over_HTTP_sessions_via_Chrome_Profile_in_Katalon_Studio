package study

import static org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.openqa.selenium.WebDriver

import com.kazurayam.webdriverfactory.chrome.ChromeDriverFactory
import com.kazurayam.webdriverfactory.chrome.ProfileDirectoryName

import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

public class GMailLoginTest {
	
	private WebDriver driver
	private String url = "https://mail.google.com/mail/u/0/#inbox"

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
	public void test_launch_Chrome_with_profileDirectory() {
		ChromeDriverFactory factory = ChromeDriverFactory.newChromeDriverFactory()
		driver = factory.newChromeDriver(new ProfileDirectoryName('Default'))
		assertNotNull(driver)
		DriverFactory.changeWebDriver(driver)
		WebUI.navigateToUrl(url)
		WebUI.delay(3)
	}

}
