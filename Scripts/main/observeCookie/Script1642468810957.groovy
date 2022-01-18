import org.openqa.selenium.Cookie
import org.openqa.selenium.chrome.ChromeDriver


// observeCookie(LaunchedChromeDriver)

// 2 parameters are given:
//     LauchedChromeDriver lauched
//     String cookieName
assert launched != null
assert cookieName != null
assert cookieName == "timestamp"

launched.getEmployedOptions().ifPresent({ options ->
		println "options => " + options.toString() })

launched.getChromeUserProfile().ifPresent({ up ->
		println "userProfile => " + up.toString() })

launched.getInstruction().ifPresent({ uda ->
		println "userDataAccess => " + uda.toString() })

URL url = new URL("http://127.0.0.1")
ChromeDriver chromeDriver = launched.getDriver()
try {
	chromeDriver.navigate().to(url.toString())
} catch (Exception e) {
	throw new Exception("Possibly the URL ${url.toString()} is down.", e)
}

Cookie cookie = chromeDriver.manage().getCookieNamed(cookieName)
return cookie

