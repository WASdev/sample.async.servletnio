## Building and running the sample using the command line

### Clone Git Repo
:pushpin: [Switch to Eclipse example](/docs/Using-WDT.md/#clone-git-repo)

```bash

$ git clone https://github.com/WASdev/sample.async.servletnio.git

```

### Building the sample
:pushpin: [Switch to Eclipse example](/docs/Using-WDT.md/#building-the-sample-in-eclipse)

This sample can be built using [Maven](#apache-maven-commands).

## Running with Maven

This project can be built with [Apache Maven](http://maven.apache.org/). The project uses [Liberty Maven Plug-in][] to automatically download and install Liberty with Java EE7 Full Platform runtime from the Maven Central. Liberty Maven Plug-in is also used to create, configure, and run the application on the Liberty server. 

Use the following steps to run the application with Maven:

1. Execute full Maven build. This will cause Liberty Maven Plug-in to download and install Liberty profile server.
    ```bash
    $ mvn clean install
    ```
    
2. To run the server with the Servlet sample execute:
    ```bash
    $ mvn liberty:run-server or,
    $ mvn liberty:start-server
    ```

* `run-server` runs the server in the foreground.
* `start-server` runs the server in the background. 

3. Confirm web browser opens on "http://localhost:9083/servlet-nio/" to run samples

## Running with Gradle

This project can also be built and run with [Gradle]. The provided `build.gradle` file applies the [Liberty Gradle Plug-in] and is configured to automatically download and install Liberty with the Liberty Java EE Web Profile 7 runtime from Maven Central. The Liberty Gradle Plug-in has built-in tasks that can be used to create, configure, and run the application on the Liberty server.

Use the following steps to run the application with Gradle:

1. Execute the full Gradle build. The Liberty Gradle Plug-in will download and install the Liberty server.
    ```bash
    $ ./gradlew clean build
    ```

2. To start the server with the Servlet sample execute:
    ```bash
    $ ./gradlew libertyStart
    ```

    Alternatively, execute the run command:
    ```bash
    $ ./gradlew libertyRun --no-daemon
    ```

Once the server has started, the application will be available under [http://localhost:9080/servlet-nio](http://localhost:9080/servlet-nio).

3. To stop the server, execute:
    ```bash
    $ ./gradlew libertyStop
    ```  

[Liberty Maven Plug-in]: https://github.com/WASdev/ci.maven
