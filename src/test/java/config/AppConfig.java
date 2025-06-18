package config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:config/default.properties",
        "classpath:config/${env}.properties"
})
public interface AppConfig extends Config {

    @Key("base_url")
    @DefaultValue("https://www.demoblaze.com")
    String baseUrl();

    //todo в будущем вряд ли у тебя будет только один юзер и пароль на сайт, возможно, тогда они будут не в конфигах
    @Key("username")
    String username();

    @Key("password")
    String password();

    @Key("browser")
    @DefaultValue("chrome")
    String browser();

}
