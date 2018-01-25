# Event monitor

Application Event Monitor REST api
# Overview

This repository contains the source for filtering & exposing events sourced from database.
The database is contained in a nosql database(MongoDB) and is hosted on cloud server(SaaS).

Steps to run & test it locally.

### Prerequisites:

JDK 1.8

MAVEN

### Step-1: Clone this repository

git clone https://github.com/INTHUB/eventmonitor.git
		
### Step-2: Setup dependencies 
Navigate to <downloaded-dir>\eventmonitor and run mvn phase

cd eventmonitor

mvn clean install

### Step-3: The application is packaged as jar(uber jar), can be run directly exposing rest api @ port 8080

Simple run ::- java -jar target/eventmonitor-0.0.1-SNAPSHOT.jar

OR

Debug run ::- java -Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=8000,suspend=n -jar target/eventmonitor-0.0.1-SNAPSHOT.jar

OR

Using the Maven plugin ::- mvn spring-boot:run

Note:- To have service run under custom port, update property "server.port" in application.properties.


### Step-4: Test it.

The application includes REST API Documentation detailing all methods/features available & a play ground to actually test.

Open http://localhost:8080/jsondoc-ui.html

JSONDoc Search for http://localhost:8080/jsondoc Click {Get documentation}

Use any of the HTTP methods & test.



### Detailed description:-

The source is divided into multiple directories(packages), 1 each for specific functionality.

##### controller dir is the starting point for various rest(http) calls

##### repositories dir is an abstract implementation for crud calls()

##### services dir is an extension of "repositories" and has implementation for filtering and custom methods

##### security dir has basic security implemented with 1 each for admin & user roles.

##### models dir is POJO's representation of full event message structure

Note:-  Repositories interfaces can be used to talk to other no/SQL databases(SQL, ORA, DB2, Cassandra, Azure Document, Couchbase, Redis GemFire, Solr ) 



