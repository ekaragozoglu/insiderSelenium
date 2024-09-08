package common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Duration;

import static common.Browser.CHROME;

public class CommonConstants {
    private final static Logger logger = LogManager.getLogger();
    public static final String BROWSER_NAME = getBrowserName();
    public static final Duration IMPLICIT_WAIT_IN_SECONDS = Duration.ofSeconds(20);

    private static String getBrowserName() {
        String browserNameFromPomXml = System.getProperty("browser");
        String browserName;

        if (browserNameFromPomXml != null)
            browserName = browserNameFromPomXml;
        else {
            logger.warn("The Maven Profile is missing the platform configuration.");
            browserName = CHROME;
            logger.warn("The default platform '{}' will be enabled for this run.", browserName);
        }

        return browserName.toLowerCase();
    }
}
