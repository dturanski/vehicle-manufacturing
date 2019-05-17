# Vehicle Manufacturing Process (Dataflow Task Launcher)

Prototype of an event-driven vehicle manufacturing process immplemented with Spring Data Flow.
```
                            |---add-engine   ------|
                            |                      |
place-order -- build-body --|---add-transmission --|--inspect-vehicle
                            |                      |
                            |---add-trim ----------|
```

This sends task launch requests to the `task-launcher-data-flow-sink-kafka` prebuilt stream app,
to run the process. As each task completes, it emits an event which triggers the next step(s).

## Prerequisites

* Spring Cloud Data flow installed on a supported platform 
* Kafka message broker
* Mysql 5.7 database

## Running 

* Build the app

The gradle build needs some work

```
cd manafacturing-common
./gradlew build install
cd ..
./gradlew build install 
```

To build docker images, you need, change the docker id in `build.gradle` and run the following for each project one at a time:

```
./gradlew jibDockerBuild
```

* Register the tasks in SCDF with names exactly as shown in the flow chart above, these are passed in the task launch requests.
NOTE: `place-order` is not a task, it's a sourfce. 

* Register the `place-order` app as sink. 

* Register https://github.com/spring-cloud-stream-app-starters/tasklauncher-dataflow/blob/master/spring-cloud-starter-stream-sink-task-launcher-dataflow[task-launcher-dataflow-sink-kafka]

* Create a stream 

```
dataflow:> stream create build-vehicle --definition "place-order | task-launcher-dataflow --spring.cloud.dataflow.client.server-uri=<dataflow-server-url> --trigger.max-period=10000"
```

* Deploy the stream

NOTE: If deploying to Kubernetes, make sure the `place-order` service ia a `LoadBalancer` type.

```
deployer.place-order.kubernetes.createLoadBalancer=true

```

post a vehicle specification, e.g., `place-order/src/test/resources/VehicleSpec.json` to

`<place-order-base-url>/orders` and use SCDF to monitor the status of the tasks.


 

