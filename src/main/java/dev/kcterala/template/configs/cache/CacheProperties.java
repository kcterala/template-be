package dev.kcterala.template.configs.cache;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@ConfigurationProperties(prefix = "cache")
public class CacheProperties {
    private Map<String, CacheConfig> configs;

    public Map<String, CacheConfig> getConfigs() {
        return configs;
    }

    public void setConfigs(final Map<String, CacheConfig> cacheConfigs) {
        this.configs = cacheConfigs;
    }
}
