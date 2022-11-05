package config;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:config/credentials.properties")

public interface CredConfig extends Config {
    @Config.Key("user")
    String user();

    @Config.Key("key")
    String key();

    @Config.Key("appBS")
    String appBS();


}
