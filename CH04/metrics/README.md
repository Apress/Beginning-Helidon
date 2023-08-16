# Metrics Sample

A sample project for _Metrics_ section of _Chapter 4: Observability_. It contains various JAX-RS resources for different metric types. It demonstrates using MicroProfile Metrics annotations and programmatic APIs.

## Build and run

With JDK17+
```bash
mvn package
java -jar target/ch04-metrics.jar
```

## Metrics

All metrics in OpenMetrics format:

```
curl http://localhost:8080/metrics
```

All metrics in JSON format:

```
curl -H 'Accept: application/json' http://localhost:8080/metrics
```

Base metrics in OpenMetrics format:

```
curl http://localhost:8080/metrics/base
```

Base metrics in JSON format:

```
curl -H 'Accept: application/json' http://localhost:8080/metrics/base
```

Vendor metrics in OpenMetrics format:

```
curl http://localhost:8080/metrics/vendor
```

Vendor metrics in JSON format:

```
curl -H 'Accept: application/json' http://localhost:8080/metrics/vendor
```

Application metrics in OpenMetrics format:

```
curl http://localhost:8080/metrics/application
```

Application metrics in JSON format:

```
curl -H 'Accept: application/json' http://localhost:8080/metrics/application
```

### Counter defined using annotations sample

Resource endpoint:

```
curl http://localhost:8080/counter/annotations
```

Retrieving associated _cntr1_ application metric in OpenMetrics format:

```
curl http://localhost:8080/metrics/application/cntr1
```

Retrieving associated _cntr1_ application metric in JSON format:

```
curl -H 'Accept: application/json' http://localhost:8080/metrics/application/cntr1
```

### Counter defined using programmatic APIs sample

Resource endpoint:

```
curl http://localhost:8080/counter/programmatic
```

Retrieving associated _cntr2_ application metric in OpenMetrics format:

```
curl http://localhost:8080/metrics/application/cntr2
```

Retrieving associated _cntr2_ application metric in JSON format:

```
curl -H 'Accept: application/json' http://localhost:8080/metrics/application/cntr2
```

### Gauge defined using annotations sample

Retrieving associated _gauge1_ application metric in OpenMetrics format:

```
curl http://localhost:8080/metrics/application/gauge1
```

Retrieving associated _gauge1_ application metric in JSON format:

```
curl -H 'Accept: application/json' http://localhost:8080/metrics/application/gauge1
```

### Gauge defined using programmatic APIs sample

Retrieving associated _gauge2_ application metric in OpenMetrics format:

```
curl http://localhost:8080/metrics/application/gauge2
```

Retrieving associated _gauge2_ application metric in JSON format:

```
curl -H 'Accept: application/json' http://localhost:8080/metrics/application/gauge2
```

### Concurrent Gauge defined using annotations sample

Resource endpoint:

```
curl http://localhost:8080/concurrentgauge/annotations
```

Retrieving associated _cgauge1_ application metric in OpenMetrics format:

```
curl http://localhost:8080/metrics/application/cgauge1
```

Retrieving associated _cgauge1_ application metric in JSON format:

```
curl -H 'Accept: application/json' http://localhost:8080/metrics/application/cgauge1
```

### Concurrent Gauge defined using programmatic APIs sample

Resource endpoint:

```
curl http://localhost:8080/concurrentgauge/programmatic
```

Retrieving associated _gauge2_ application metric in OpenMetrics format:

```
curl http://localhost:8080/metrics/application/cgauge2
```

Retrieving associated _gauge2_ application metric in JSON format:

```
curl -H 'Accept: application/json' http://localhost:8080/metrics/application/cgauge2
```

### Histogram defined using programmatic APIs sample

Resource endpoint:

```
curl http://localhost:8080/histogram
```

Retrieving associated _histogram_ application metric in OpenMetrics format:

```
curl http://localhost:8080/metrics/application/histogram/<value>
```

Retrieving associated _histogram_ application metric in JSON format:

```
curl -H 'Accept: application/json' http://localhost:8080/metrics/application/histogram
```

### Meter defined using annotations sample

Resource endpoint:

```
curl http://localhost:8080/meter/annotations
```

Retrieving associated _mtr1_ application metric in OpenMetrics format:

```
curl http://localhost:8080/metrics/application/mtr1
```

Retrieving associated _mtr1_ application metric in JSON format:

```
curl -H 'Accept: application/json' http://localhost:8080/metrics/application/mtr1
```

### Meter defined using programmatic APIs sample

Resource endpoint:

```
curl http://localhost:8080/meter/programmatic
```

Retrieving associated _mtr2_ application metric in OpenMetrics format:

```
curl http://localhost:8080/metrics/application/mtr2
```

Retrieving associated _mtr2_ application metric in JSON format:

```
curl -H 'Accept: application/json' http://localhost:8080/metrics/application/mtr2
```

### Simple Timer defined using annotations sample

Resource endpoint:

```
curl http://localhost:8080/simpletimer/annotations
```

Retrieving associated _stmr1_ application metric in OpenMetrics format:

```
curl http://localhost:8080/metrics/application/stmr1
```

Retrieving associated _stmr1_ application metric in JSON format:

```
curl -H 'Accept: application/json' http://localhost:8080/metrics/application/stmr1
```

### Simple Timer defined using programmatic APIs sample

Resource endpoint:

```
curl http://localhost:8080/simpletimer/programmatic
```

Retrieving associated _stmr2_ application metric in OpenMetrics format:

```
curl http://localhost:8080/metrics/application/stmr2
```

Retrieving associated _stmr2_ application metric in JSON format:

```
curl -H 'Accept: application/json' http://localhost:8080/metrics/application/stmr2
```

### Timer defined using annotations sample

Resource endpoint:

```
curl http://localhost:8080/timer/annotations
```

Retrieving associated _tmr1_ application metric in OpenMetrics format:

```
curl http://localhost:8080/metrics/application/tmr1
```

Retrieving associated _tmr1_ application metric in JSON format:

```
curl -H 'Accept: application/json' http://localhost:8080/metrics/application/tmr1
```

### Timer defined using programmatic APIs sample

Resource endpoint:

```
curl http://localhost:8080/timer/programmatic
```

Retrieving associated _tmr2_ application metric in OpenMetrics format:

```
curl http://localhost:8080/metrics/application/tmr2
```

Retrieving associated _tmr2_ application metric in JSON format:

```
curl -H 'Accept: application/json' http://localhost:8080/metrics/application/tmr2
```