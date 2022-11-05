package drivers;
import com.codeborne.selenide.WebDriverProvider;
import config.EmulationDeviceConfig;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.AutomationName;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import static org.apache.commons.io.FileUtils.copyInputStreamToFile;

public class EmulationMobileDriver implements WebDriverProvider {
    static final EmulationDeviceConfig emulationConfig = ConfigFactory.create(EmulationDeviceConfig.class, System.getProperties());

    public static URL getAppiumServerUrl() {
        try {
            return new URL(emulationConfig.appiumUrl());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public WebDriver createDriver(Capabilities capabilities) {
        File app = getApp();
        UiAutomator2Options options = new UiAutomator2Options();
        options.merge(capabilities);
        options.setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2);
        options.setPlatformName(emulationConfig.platformName());
        options.setDeviceName(emulationConfig.deviceName());
        options.setPlatformVersion(emulationConfig.platformVersion());
        options.setApp(app.getAbsolutePath());
        options.setAppPackage("org.wikipedia.alpha");
        options.setAppActivity("org.wikipedia.main.MainActivity");

        return new AndroidDriver(getAppiumServerUrl(), options);
    }



    private File getApp() {


        File app = new File(emulationConfig.appPath());
        if (!app.exists()) {
            try (InputStream in = new URL(emulationConfig.appUrl()).openStream()) {
                copyInputStreamToFile(in, app);
            } catch (IOException e) {
                throw new AssertionError("Failed to download application", e);
            }
        }
        return app;
    }
}
