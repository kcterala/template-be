package dev.kcterala.template.configs.cache;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class CacheInitializer {
    private final CacheProperties cacheProperties;

    public CacheInitializer(final CacheProperties cacheProperties) {
        this.cacheProperties = cacheProperties;
    }

    @Bean
    @Qualifier(AppCache.EVEN_NUMBER_CACHE)
    public Cache<String, String> evenNumberCache() {
        return createCache(AppCache.EVEN_NUMBER_CACHE);
    }


    @Bean
    @Qualifier(AppCache.ODD_NUMBER_CACHE)
    public Cache<String, String> oddNumberCache() {
        return createCache(AppCache.ODD_NUMBER_CACHE);
    }

    public <K, V> Cache<K, V> createCache(final String cacheName) {
        final CacheConfig cacheConfig = cacheProperties.getConfigs().get(cacheName);
        if (cacheConfig == null) {
            throw new IllegalArgumentException("AppCache configuration not found for cache: " + cacheName);
        }
        return Caffeine.newBuilder()
                .maximumSize(cacheConfig.getMaxSize())
                .expireAfterWrite(cacheConfig.getTtlInMinutes(), TimeUnit.SECONDS)
                .build();
    }
}
