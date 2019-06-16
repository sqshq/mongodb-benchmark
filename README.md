# MongoDB Benchmark (bulk insert / update / delete)

results on `c5d.2xlarge`, `Amazon Linux 2` (benchmark app + mongod on the same instance)

MongoDB version: `4.0.10 enterprise`

### Default settings
```
run #1
bulk insert: 24215ms (41666.7 per sec)
bulk update: 21462ms (47619.0 per sec)
bulk delete: 13278ms (76923.1 per sec)

run #2
bulk insert: 23929ms (43478.3 per sec)
bulk update: 22805ms (45454.5 per sec)
bulk delete: 12630ms (83333.3 per sec)

run #3
bulk insert: 20913ms (50000.0 per sec)
bulk update: 22753ms (45454.5 per sec)
bulk delete: 12783ms (83333.3 per sec)
```

### --nojournal

```
run #1
bulk insert: 23345ms (43478.3 per sec)
bulk update: 18327ms (55555.6 per sec)
bulk delete: 10365ms (100000.0 per sec)

run #2
bulk insert: 21196ms (47619.0 per sec)
bulk update: 18917ms (55555.6 per sec)
bulk delete: 10351ms (100000.0 per sec)

run #3
bulk insert: 19437ms (52631.6 per sec)
bulk update: 18858ms (55555.6 per sec)
bulk delete: 10332ms (100000.0 per sec)
```

### --storageEngine inMemory

```
run #1
bulk insert: 22678ms (45454.5 per sec)
bulk update: 17726ms (58823.5 per sec)
bulk delete: 9947ms (111111.1 per sec)

run #2
bulk insert: 19979ms (52631.6 per sec)
bulk update: 18275ms (55555.6 per sec)
bulk delete: 10290ms (100000.0 per sec)

run #3
bulk insert: 19574ms (52631.6 per sec)
bulk update: 18478ms (55555.6 per sec)
bulk delete: 10060ms (100000.0 per sec)
```
