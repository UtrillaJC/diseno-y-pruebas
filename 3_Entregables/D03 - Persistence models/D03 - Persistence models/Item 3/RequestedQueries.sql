-- Query C/1:
-- The average, the minimum, the maximum, and the standard deviation of the number of applications per trip.

select avg(t.applications.size), min(t.applications.size), max(t.applications.size), sqrt(sum(t.applications.size * t.applications.size)/count(a) - (avg(t.applications.size) * avg(t.applications.size))) from Trip t, Application a;

-- Query C/2:
-- The average, the minimum, the maximum, and the standard deviation of the number of trips managed per manager.

select avg(m.trips.size), min(m.trips.size), max(m.trips.size), sqrt(sum(m.trips.size * m.trips.size)/count(t) - (avg(m.trips.size) * avg(m.trips.size))) from Manager m, Trip t;

-- Query C/3:
-- The average, the minimum, the maximum, and the standard deviation of the price of the trips.

select avg(t.price.amount), min(t.price.amount), max(t.price.amount), sqrt(sum(t.price.amount * t.price.amount)/count(t.price.amount) - (avg(t.price.amount) * avg(t.price.amount))) from Trip t;

-- Query C/4:
-- The average, the minimum, the maximum, and the standard deviation of the number trips guided per ranger.

select avg(r.trips.size), min(r.trips.size), max(r.trips.size), sqrt(sum(r.trips.size * r.trips.size)/count(t) - (avg(r.trips.size) * avg(r.trips.size))) from Ranger r, Trip t;

-- Query C/5:
-- The ratio of applications with status “PENDING”.

select concat(100*(select count(a) from Application a where a.status = 'PENDING')/count(b), '%') from Application b;

-- Query C/6:
-- The ratio of applications with status “DUE”.

select concat(100*(select count(a) from Application a where a.status = 'DUE')/count(b), '%') from Application b;

-- Query C/7:
-- The ratio of applications with status “ACCEPTED”.

select concat(100*(select count(a) from Application a where a.status = 'ACCEPTED')/count(b), '%') from Application b;

-- Query C/8:
-- The ratio of applications with status “CANCELLED”.

select concat(100*(select count(a) from Application a where a.status = 'CANCELLED')/count(b), '%') from Application b;

-- Query C/9:
-- The ratio of trips that have been cancelled versus the total number of trips that have been organised.

select concat(100*(select count(t) from Trip t where t.cancelledReason is not null)/count(r), '%') from Trip r;

-- Query C/10:
-- The listing of trips that have got at least 10% more applications than the av-erage, ordered by number of applications.

select t from Trip t where t.applications.size >= 1.1*(select avg(r.applications.size) from Trip r) order by t.applications.size; 

-- Query C/11:
-- A table with the number of times that each legal text’s been referenced.

select l.title, l.trips.size from LegalText l;

-- ***********************************************************************************************************************************************************************************

-- Query B/1:
-- The minimum, the maximum, the average, and the standard deviation of the number of notes per trip.

select min(t.notes.size), max(t.notes.size), avg(t.notes.size), sqrt(sum(t.notes.size * t.notes.size)/count(n) - (avg(t.notes.size) * avg(t.notes.size))) from Trip t, Note n;

-- Query B/2:
-- The minimum, the maximum, the average, and the standard deviation of the number of audit records per trip.

select min(t.auditRecords.size), max(t.auditRecords.size), avg(t.auditRecords.size), sqrt(sum(t.auditRecords.size * t.auditRecords.size)/count(a) - (avg(t.auditRecords.size) * avg(t.auditRecords.size))) from Trip t, AuditRecord a;

-- Query B/3:
-- The ratio of trips with an audit record.

select concat(100*(select count(t) from Trip t where t.auditRecords is not empty)/count(r), '%') from Trip r;

-- Query B/4:
-- The ratio of rangers who have registered their curricula.

select concat(100*(select count(r) from Ranger r where r.curriculum is not null)/count(a), '%') from Ranger a;

-- Query B/5:
-- The ratio of rangers whose curriculum’s been endorsed.

select concat(100*(select count(r) from Ranger r where r.curriculum.endorserRecords is not empty)/count(a), '%') from Ranger a;

-- Query B/6:
-- The ratio of suspicious managers.

select concat(100*(select count(m) from Manager m where m.suspicious is true)/count(a), '%') from Manager a;

-- Query B/7:
-- The ratio of suspicious rangers.

select concat(100*(select count(r) from Ranger r where r.suspicious is true)/count(a), '%') from Ranger a;