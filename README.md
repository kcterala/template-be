# Template for a new Spring boot project

- [x] Add Event log filter
- [x] Add Swagger OpenApi
- [x] Add in-memory cache
- [ ] Add docker compose for postgres, kafka and redis.
- [ ] Add dockerfile for the application
- [ ] Monitoring for the application


### Steps to add new cache

- Add new cache in `AppCache` class.
- Add the maxSize and ttl config in the application.properties in the following format:
  ```
  cache.configs.<cache_name>.maxSize=<maxSize>
  cache.configs.<cache_name>.ttlInMinutes=<ttl>
  ```
- Add the Bean for the new cache instance in `CacheInitializer` class.
  ```
    @Bean
    @Qualifier(AppCache.<cache_name_constant>)
    public Cache<String, String> sampleCache() {
        return createCache(AppCache.<cache_name_constant>);
    }
  ```
- This instance can be autowired where necessary.