# Java EE7: Non-blocking Servlet IO [![Build Status](https://travis-ci.org/WASdev/sample.async.servletnio.svg?branch=master)](https://travis-ci.org/WASdev/sample.async.servletnio)

**This repository is no longer being maintained and has been archived. It is left for reference and available for use as-is with the advisory that it depends old dependencies which may have security vulnerabilties.**


Java EE7 added support for Non-blocking Servlet IO (NIO) in Servlet 3.1, which enables reading and writing data without blocking a thread, which can be very useful when faced with slow clients. The new function builds upon support for asynchronous processing that was introduced in Servlet 3.0, and will only work with async-enabled apps.

This sample contains a few variations to illustrate how Non-blocking IO in Servlet 3.1 works:

* *Plain*: [PlainServlet](/src/main/java/net/wasdev/servlet/nio/PlainServlet.java) is just a plain/synchronous servlet
* *Async*: [AsyncServlet](/src/main/java/net/wasdev/servlet/nio/AsyncServlet.java) defines an asynchronous servlet using attribuets defined in the Servlet 3.0 specification.
* *Async with Runnable*: [AsyncServletRunnable](/src/main/java/net/wasdev/servlet/nio/AsyncServletRunnable.java) is an asynchronous servlet that uses a runnable to offload work to a different thread.
* *Async with NIO*: [AsyncServletNIO](/src/main/java/net/wasdev/servlet/nio/AsyncServletNIO.java) is a servlet that uses an [AsyncReadListener](/src/main/java/net/wasdev/servlet/nio/AsyncReadListener.java) for clients that are slow to write data, and an [AsyncWriteListener](/src/main/java/net/wasdev/servlet/nio/AsyncWriteListener.java) for clients that are slow to read data.

## Getting Started:

Browse the code to see what it does, or build and run it yourself:

* [Building and running on the command line using Maven and Gradle](/docs/Using-cmd-line.md)
* [Building and running using Eclipse and WebSphere Development Tools (WDT)](/docs/Using-WDT.md)

Once the server has been started, go to [http://localhost:9083/servlet-nio/](http://localhost:9083/servlet-nio/) to interact with the sample.

## More on Servlet NIO
* [JSR 340: Java Servlet 3.1](https://jcp.org/en/jsr/detail?id=340)
    * Section 2.3.3.3 - Asynchronous processing
    * Section 5.3 - Non-blocking IO

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
[Liberty Maven Plug-in]: https://github.com/WASdev/ci.maven
[Liberty Gradle Plug-in]: https://github.com/WASdev/ci.gradle
[Gradle]: https://gradle.org
[ci.gradle]: https://github.com/WASdev/ci.gradle
