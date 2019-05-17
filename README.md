# Vehicle Manufacturing Process

Prototype of an event-driven vehicle manufacturing process immplemented with Spring Cloud Stream.
```
                            |---add-engine   ------|
                            |                      |
place-order -- build-body --|---add-transmission --|--inspect-vehicle
                            |                      |
                            |---add-trim ----------|
```

post a vehicle specification, e.g., `place-order/src/test/resources/VehicleSpec.json` to

`<place-order-base-url>/orders`

This sends a message to the other components and orchestrates the process. 
As each step completes, it emits an event which triggers the next step(s).
 

The `dataFlow-task-launcher` branch contains an implementation the works with Spring Cloud Data Flow and runs each manufacturing step as a task. 

## Assumptions

Fully automated process - no human interaction involved in any of the steps.

The process is not linear - once the body is built, add engine, add transmission, add trim can happen in parallel, in any order.

The inspect vehicle step cannot be completed until the assembly is complete. 

## Build the app

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