# Mongodb Benchmark (bulk insert / update / delete)

results on c5d.2xlarge (benchmark app + mongod on the same instance)

### Default mongo settings
```
bulk insert: 55742ms (36363.6 per sec)
bulk update: 54013ms (37037.0 per sec)
bulk delete: 28916ms (71428.6 per sec)

bulk insert: 49865ms (40816.3 per sec)
bulk update: 52914ms (38461.5 per sec)
bulk delete: 29127ms (68965.5 per sec)

bulk insert: 45647ms (44444.4 per sec)
bulk update: 54144ms (37037.0 per sec)
bulk delete: 29123ms (68965.5 per sec)
```

### --nojournal

```
bulk insert: 62480ms (32258.1 per sec)
bulk update: 42709ms (47619.0 per sec)
bulk delete: 23294ms (86956.5 per sec)

bulk insert: 56701ms (35714.3 per sec)
bulk update: 42912ms (47619.0 per sec)
bulk delete: 23457ms (86956.5 per sec)

bulk insert: 56768ms (35714.3 per sec)
bulk update: 42355ms (47619.0 per sec)
bulk delete: 23283ms (86956.5 per sec)
```