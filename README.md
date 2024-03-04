# WeatherForecast
Considerations:
1. This solution is implemented using Hashmaps for simulating the database and Cache data store.
2. It doesn't use any 3rd party libraries for cache implementation.
3. The zipcodes are added to keyWithExpiry Map to manage and evict those zipcodes based on their expiry time. This happens periodically using a scheduler configured t run every 60 sec.
4. Added try/catch blocks, loggers, code comments, Return codes and statuses to API invoking clients wherevr possible.
5. This will have few cons such as can't be run in Multi threaded environment, and a deployment environment where multiple instances of this server is run, as it leads to consistency issues because cache is implmented using a local in-memory datastore considering the scope of this assignment and hence not used any 3rd party solutions like Redis, ehcache .
