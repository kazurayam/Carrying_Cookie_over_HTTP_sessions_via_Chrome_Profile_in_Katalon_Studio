import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

import org.openqa.selenium.Cookie
import org.openqa.selenium.chrome.ChromeDriver

import com.kazurayam.webdriverfactory.CookieUtils
import com.kazurayam.webdriverfactory.chrome.ChromeDriverFactory
import com.kazurayam.webdriverfactory.chrome.LaunchedChromeDriver
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

/**
 * testcase name: main/TC1_ChromeDriver_using_default_profile
 * 
 */

if (DriverFactory.getExecutedBrowser().getName() != "CHROME_DRIVER") {
	KeywordUtil.markErrorAndStop("Please choose CHROME_DRIVER")
}

ChromeDriverFactory.setPathToChromeDriverExecutable(DriverFactory.getChromeDriverPath())

ChromeDriverFactory factory = ChromeDriverFactory.newChromeDriverFactory()
LaunchedChromeDriver launched

// 1st session
launched = factory.newChromeDriver()  // THIS IS THE DIFFERENCE FROM TC1

Cookie timestamp1 = WebUI.callTestCase(findTestCase("main/observeCookie"), ["launched": launched])
ChromeDriver chromeDriver1 = launched.getDriver()
chromeDriver1.quit()   
// by .quit(), the browser will save the cookies in to the profile directory of the "Default" profile 
// in a temporary directory, which wil be discared

// 2nd session
launched = factory.newChromeDriver()  // THIS IS THE DIFFERENCE FROM TC1

Cookie timestamp2 = WebUI.callTestCase(findTestCase("main/observeCookie"), ["launched": launched])
ChromeDriver chromeDriver2 = launched.getDriver()
chromeDriver2.quit()

//
println "timestamp1 => " + CookieUtils.stringifyCookie(timestamp1)
println "timestamp2 => " + CookieUtils.stringifyCookie(timestamp2)

assert timestamp1.getValue() == timestamp2.getValue()
assert timestamp1.getExpiry() != timestamp2.getExpiry()
	
