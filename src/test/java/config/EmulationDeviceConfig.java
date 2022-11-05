package config;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:config/emulation.properties")
public interface EmulationDeviceConfig extends Config {
    @Key("device_name")
    String deviceName();

    @Key("platform_name")
    String platformName();

    @Key("platform_version")
    String platformVersion();

    @Key("app_URL")
    String appUrl();

    @Key("app_path")
    String appPath();

    @Key("appium_URL")
    String appiumUrl();
}
