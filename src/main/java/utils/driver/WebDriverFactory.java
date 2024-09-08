package utils.driver;

import java.security.InvalidParameterException;

import static common.Browser.*;
import static common.CommonConstants.BROWSER_NAME;

public class WebDriverFactory {
    public WebDriverService getDriverService() {
        WebDriverService driver;

        switch (BROWSER_NAME) {
            case CHROME:
                driver = new ChromeDriverServiceImpl();
                break;
            case HEADLESS_CHROME:
                driver = new HeadlessChromeDriverServiceImpl();
                break;
            case FIREFOX:
                driver = new FirefoxDriverServiceImpl();
                break;
            case HEADLESS_FIREFOX:
                driver = new HeadlessFirefoxDriverServiceImpl();
                break;

            default:
                throw new InvalidParameterException("'" + BROWSER_NAME + "' browser is not implemented");
        }

        return driver;
    }
}
