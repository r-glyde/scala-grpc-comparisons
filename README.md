# grpc-comparisons

```
docker run --rm -p 4770:4770 grpc-comparisons-scalagrpc:latest
# For profiling jmx
# docker run --rm -p 4770:4770 -p 5000:5000 -e "JAVA_OPTS=-Dcom.sun.management.jmxremote -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.port=5000 -Dcom.sun.management.jmxremote.rmi.port=5000 -Djava.rmi.server.hostname=0.0.0.0" grpc-comparisons-scalagrpc:latest
ghz --insecure --proto proto/echo.proto  --call example.echo.EchoService.Echo --duration=2m -D data/data_1.json  localhost:4770
```

## Results
* Setting docker CPU to 2
* Warmup for 1 minute with `data_1.json`
* Tests run for 2 minutes
* 1/100/1000 varies length of request collection

|              | P99 Latency (ms) |    TPS   | Total Count |
|--------------|-----------------:|---------:|------------:|
| scalaPb_1    |             13.12|   7667.26|       920073|
| scalaPb_100  |             15.89|   6066.99|       728040|
| scalaPb_1000 |             58.73|   1776.86|       213233|
| akka_1       |             40.68|   2879.91|       345590|
| akka_100     |             42.79|   2546.02|       305535|
| akka_1000    |             70.43|   1247.94|       149765|
| zio_1        |             21.21|   4952.22|       594267|
| zio_100      |             24.68|   4073.22|       488788|
| zio_1000     |             56.24|   1668.33|       200201|
| fs2_1        |             17.12|   5928.88|       711473|
| fs2_100      |             22.16|   4835.30|       580238|
| fs2_1000     |             71.91|   1574.17|       188901|
