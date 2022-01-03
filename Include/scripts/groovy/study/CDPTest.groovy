package study

import static org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.devtools.DevTools
import org.openqa.selenium.devtools.v96.network.Network


import com.kazurayam.webdriverfactory.chrome.ChromeDriverFactory
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

/**
 * refered to
 * https://rahulshettyacademy.com/blog/index.php/2021/11/04/selenium-4-key-feature-network-interception/
 * https://applitools.com/blog/selenium-chrome-devtools-protocol-cdp-how-does-it-work/
 */
public class CDPTest {

	private ChromeDriver driver
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
	public void test_launch_Chrome() {
		ChromeDriverFactory factory = ChromeDriverFactory.newChromeDriverFactory()
		driver = factory.newChromeDriver()
		assertNotNull(driver)
		//
		DevTools devTool = driver.getDevTools()
		devTool.createSession()
		devTool.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()))
		devTool.addListener(Network.requestWillBeSent(), { requestSent ->
			println("Request URL => " + requestSent.getRequest().getUrl())
			println("Request Method => " + requestSent.getRequest().getMethod())
			println("Request Headers => " + requestSent.getRequest().getHeaders().toString())
			println("------------------------------------------------------")
		})
		//
		DriverFactory.changeWebDriver(driver)
		WebUI.navigateToUrl(url)
		WebUI.delay(1)
	}


}
