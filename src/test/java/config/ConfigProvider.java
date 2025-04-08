package config;

import config.AppConfig;
import org.aeonbits.owner.ConfigFactory;

public class ConfigProvider {
    public static final AppConfig CONFIG = ConfigFactory.create(AppConfig.class, System.getProperties());
}
