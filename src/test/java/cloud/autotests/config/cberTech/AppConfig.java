package cloud.autotests.config.cberTech;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:config/sberTechProd/app.properties"
})
public interface AppConfig extends Config {

    String webUrl();

}
