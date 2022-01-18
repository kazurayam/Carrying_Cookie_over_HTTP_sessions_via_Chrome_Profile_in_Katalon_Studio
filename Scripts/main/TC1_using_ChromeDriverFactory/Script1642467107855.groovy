import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static org.junit.Assert.*

import org.openqa.selenium.Cookie
import org.openqa.selenium.chrome.ChromeDriver

import com.kazurayam.webdriverfactory.CookieUtils
import com.kazurayam.webdriverfactory.UserProfile
import com.kazurayam.webdriverfactory.chrome.ChromeDriverFactory
import com.kazurayam.webdriverfactory.chrome.LaunchedChromeDriver
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI


// 
/**
 * project name : Carrying_Cookie_over_HTTP_Sessions_via_Chrome_Profile_in_Katalon_Studio
 * testcase name: main/TC1_using_ChromeDriverFactory
 * 
 * This code will open Chrome browser and navigate to the URL "http://127.0.0.1" twice.
 * The http server will send a cookie named "timestamp" with value of
 * 1. if the HTTP Request has no "timestamp" cookie, will create a new cookie with current timestamp
 * 2. if the HTTP Request sent a "timestamp" cookie, will echo it
 * The cookie has a expiry that lasts only 60 seconds.
 *
 * At the 1st time, Chrome is opened with UserDataAccess option of "FOR_HERE".
 * Then the "timestamp" cookie will be persisted in the profile storage.
 *
 * At the 2nd time, Chrome is opened with UserDataAccess option of "TO_GO".
 * TO_GO means that the files in the profile directory will be copied from the genuine location
 * to the temporary location. Therefore I expect the cookies are carried over to
 * the second session. In the second session,
 * I expect the "timestamp" cookie should be sent from Chrome to the server again.
 *
 * This code makes assertion if the values of "timestamp" cookie of the 1st session
 * and the 2nd session are equal.
 * If these are not equal, it means that cookie was not carried over.
 */

if (DriverFactory.getExecutedBrowser().getName() != "CHROME_DRIVER") {
	KeywordUtil.markErrorAndStop("Please choose CHROME_DRIVER")
}

ChromeDriverFactory.setPathToChromeDriverExecutable(DriverFactory.getChromeDriverPath())

ChromeDriverFactory factory = ChromeDriverFactory.newChromeDriverFactory()
LaunchedChromeDriver launched

// 1st session
launched = factory.newChromeDriver(new UserProfile("Picasso"),
					ChromeDriverFactory.UserDataAccess.FOR_HERE)
Cookie timestamp1 = WebUI.callTestCase(findTestCase("main/observeCookie"), ["launched": launched])
ChromeDriver chromeDriver1 = launched.getDriver()
chromeDriver1.quit()   // by .quit(), the browser will save the cookies in to the profile directory

// 2nd session
launched = factory.newChromeDriver(new UserProfile("Picasso"),
					ChromeDriverFactory.UserDataAccess.TO_GO)
Cookie timestamp2 = WebUI.callTestCase(findTestCase("main/observeCookie"), ["launched": launched])
ChromeDriver chromeDriver2 = launched.getDriver()
chromeDriver2.quit()

//
println "timestamp1 => " + CookieUtils.stringifyCookie(timestamp1)
println "timestamp2 => " + CookieUtils.stringifyCookie(timestamp2)

assertEquals(timestamp1.getValue(), timestamp2.getValue())
assertNotEquals(timestamp1.getExpiry(), timestamp2.getExpiry())
	
