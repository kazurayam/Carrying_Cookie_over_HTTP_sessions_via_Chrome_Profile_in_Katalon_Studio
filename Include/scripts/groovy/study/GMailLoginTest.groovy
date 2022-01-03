package study

import static org.junit.Assert.*

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.devtools.DevTools
import org.openqa.selenium.devtools.Event

import org.openqa.selenium.devtools.v96.network.Network

import com.kazurayam.webdriverfactory.chrome.ChromeDriverFactory
import com.kazurayam.webdriverfactory.UserProfile
import com.kazurayam.webdriverfactory.chrome.ChromeProfileUtils
import com.kazurayam.webdriverfactory.chrome.ProfileDirectoryName

import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

public class GMailLoginTest {

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
	public void test_launch_Chrome_with_profileDirectory() {
		ChromeDriverFactory factory = ChromeDriverFactory.newHeadlessChromeDriverFactory()
		ProfileDirectoryName pdn = new ProfileDirectoryName('Default')
		driver = factory.newChromeDriver(pdn)
		assertNotNull(driver)
		//
		UserProfile userProfile = ChromeProfileUtils.findUserProfileByProfileDirectoryName(pdn)
		println("Opening Chrome with")
		println("    ProfileDirectoryName=" + pdn)
		println("    UserProfile=" + userProfile)
		//
		DevTools devTool = driver.getDevTools()
		devTool.createSession()
		devTool.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()))
		Event event = Network.requestWillBeSent()
		devTool.addListener(event, { requestSent ->
			println("Request URL => " + requestSent.getRequest().getUrl())
			println("Request Method => " + requestSent.getRequest().getMethod())
			Gson gson = new GsonBuilder().setPrettyPrinting().create()
			println("Request Headers => " + gson.toJson(requestSent.getRequest().getHeaders()))
			println("------------------------------------------------------")
		})
		//
		DriverFactory.changeWebDriver(driver)
		WebUI.navigateToUrl(url)
		WebUI.delay(3)
	}
}
