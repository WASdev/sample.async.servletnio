# Java EE7: Non-blocking Servlet IO [![Build Status](https://travis-ci.org/WASdev/sample.async.servletnio.svg?branch=master)](https://travis-ci.org/WASdev/sample.async.servletnio)

Java EE7 added support for Non-blocking Servlet IO (NIO) in Servlet 3.1, which enables reading and writing data without blocking a thread, which can be very useful when faced with slow clients. The new function builds upon support for asynchronous processing that was introduced in Servlet 3.0, and will only work with async-enabled apps.

This sample contains a few variations to illustrate how Non-blocking IO in Servlet 3.1 works:

* *Plain*: [PlainServlet](/src/main/java/net/wasdev/servlet/nio/PlainServlet.java) is just a plain/synchronous servlet
* *Async*: [AsyncServlet](/src/main/java/net/wasdev/servlet/nio/AsyncServlet.java) defines an asynchronous servlet using attribuets defined in the Servlet 3.0 specification.
* *Async with Runnable*: [AsyncServletRunnable](/src/main/java/net/wasdev/servlet/nio/AsyncServletRunnable.java) is an asynchronous servlet that uses a runnable to offload work to a different thread.
* *Async with NIO*: [AsyncServletNIO](/src/main/java/net/wasdev/servlet/nio/AsyncServletNIO.java) is a servlet that uses an [AsyncReadListener](/src/main/java/net/wasdev/servlet/nio/AsyncReadListener.java) for clients that are slow to write data, and an [AsyncWriteListener](/src/main/java/net/wasdev/servlet/nio/AsyncWriteListener.java) for clients that are slow to read data.

## Getting Started:

Browse the code to see what it does, or build and run it yourself:

* [Building and running on the command line](/docs/Using-cmd-line.md)
* [Building and running using Eclipse and WebSphere Development Tools (WDT)](/docs/Using-WDT.md)

Once the server has been started, go to [http://localhost:9083/servlet-nio/](http://localhost:9083/servlet-nio/) to interact with the sample.

## More on Servlet NIO
* [JSR 340: Java Servlet 3.1](https://jcp.org/en/jsr/detail?id=340)
    * Section 2.3.3.3 - Asynchronous processing
    * Section 5.3 - Non-blocking IO

## Running with Gradle

This project can also be built and run with Gradle. The provided `build.gradle` file applies the Liberty Gradle Plug-in and is configured to automatically download and install Liberty with Java EE Web Profile 7 runtime from Maven Central. The Liberty Gradle Plug-in has built-in tasks that can be used to create, configure, and run the application on the Liberty server.

Use the following steps to run the application with Gradle:

1. Execute the full Gradle build. The Liberty Gradle Plug-in will download and install the Liberty server.
    ```bash
    $ gradle clean build
    ```

2. To start the server with the Servlet sample execute:
    ```bash
    $ gradle libertyStart
    ```

    Alternatively, execute the run command:
    ```bash
    $ gradle libertyRun --no-daemon
    ```

Once the server has started, the application will be available under [http://localhost:9080/sample.javaee7.jsonp](http://localhost:9080/sample.javaee7.jsonp).

3. To stop the server, execute:
    ```bash
    $ gradle libertyStop
    ```  

## Notice

© Copyright IBM Corporation 2015, 2017.

## License

```text
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
````
