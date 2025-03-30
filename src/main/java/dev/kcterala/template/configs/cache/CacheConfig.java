package dev.kcterala.template.configs.cache;

public class CacheConfig {
    private int maxSize;
    private int ttlInMinutes;

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(final int maxSize) {
        this.maxSize = maxSize;
    }

    public int getTtlInMinutes() {
        return ttlInMinutes;
    }

    public void setTtlInMinutes(final int ttlInMinutes) {
        this.ttlInMinutes = ttlInMinutes;
    }
}
