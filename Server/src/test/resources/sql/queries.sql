select * from cab_trip_data;
select count(*) from cab_trip_data; #73488
select count(distinct(medallion)) from cab_trip_data; #10335
select count(distinct medallion, pickup_datetime) from cab_trip_data; #73122
select count(distinct medallion, hack_license) from cab_trip_data; #24643

select count(distinct medallion, pickup_datetime, trip_time_in_secs) from cab_trip_data; #73122
select count(distinct medallion, pickup_datetime, dropoff_datetime) from cab_trip_data; #73486

select count(distinct medallion, hack_license, pickup_datetime, dropoff_datetime) from cab_trip_data; #73488
select count(distinct medallion, hack_license) from cab_trip_data; #24643
select count(distinct medallion, hack_license, vendor_id) from cab_trip_data; #24655


select medallion, pickup_datetime, trip_time_in_secs, count(*)
from cab_trip_data
group by medallion, pickup_datetime, trip_time_in_secs
having count(*) > 1;

select * from cab_trip_data
where medallion='0DF80FDB0E48E1A2534EE0F509BC66FB'
and pickup_datetime='2013-12-30 06:43:00'
and trip_time_in_secs=600;

select * from cab_trip_data
where medallion='0E97EE91E9FC3335A9328334B6B516FB'
and pickup_datetime='2013-12-31 23:29:00'
and trip_time_in_secs=720;

select count(*) from cab_trip_data
where date_add(pickup_datetime, interval trip_time_in_secs second) != dropoff_datetime;

select count(*) from cab_trip_data
where pickup_datetime > dropoff_datetime;

select * from cab_trip_data
where pickup_datetime > dropoff_datetime;




select * from cab_trip_data
where medallion ='FE6449BC4837A48D56D9D01265659420'
and date(pickup_datetime) = '2013-12-31'
;

select count(*) from cab_trip_data
where medallion ='FE6449BC4837A48D56D9D01265659420'
and date(pickup_datetime) = '2013-12-31'
;


select count(*) from cab_trip_data
where medallion ='9150150894F4D1F06AB4A016AF410DB3'
and date(pickup_datetime) = '2013-12-31'
;

SELECT medallion, count(*) FROM cab_trip_data 
where medallion
group by medallion order by 1;
