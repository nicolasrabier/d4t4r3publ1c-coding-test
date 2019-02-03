# d4t4r3publ1c-coding-test

## Scenario

Cab Data Researcher is a company that provides insights on the open data about NY cab trips.

Cab trips in NY are public available as csv downloadable files. In order to make it more useful we want to wrap the data in a public API.
 
Data format is as follow:
 
medallion, hack_license, vendor_id, rate_code, store_and_fwd_flag, pickup_datetime, dropoff_datetime, passenger_count, trip_time_in_secs, trip_distance

The medallion is the cab identification.
 
Our API should provide a way to query how many trips a particular cab (medallion) has made given a particular pickup date (using pickup_datetime and only considering the date part)
 
The API must receive one or more medallions and return how many trips each medallion has made.
 
Considering that the query creates a heavy load on the database, the results must be cached.

The API must allow user to ask for fresh data, ignoring the cache.

There must be also be a method to clear the cache.
 
What do we provide:

* SQL statements to populate database from the csv

## My Answer

My work is located on GitHub at this location: https://github.com/nicolasrabier/d4t4r3publ1c-coding-test

### API Documentation

http://localhost:8080/swagger-ui.html

### Data Discrepancy

These cases shouldn't logically occur and will affect the accuracy and so the pertinence of the result:
* A medallion has more than 1 trip with same pick up date & time (366 occurrences)
* The pick up date time is posterior to drop-off date time (4672 occurrences)
* The pick up date time plus the trip time in second is different to the drop off date time (16744 occurrences)

### JUnit Test

Tests incomplete. 
Encountered many issues with test and the config with H2DB that doesn't support TEXT 
(Potential Solution: put test database on mysql or use @Profile)





