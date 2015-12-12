# El-Reddit Search

###### A Restful Webservice for searching comments using Reddit API.

## Technologies Used:
- Maven 4.0
- Java 1.8
- Jackson API
- Jersey 2.22.1
- JUnit 4
- Embedded Jetty Container.

## Running the application:
The application is packaged as a 'war'.
###### Steps to execute:
- Clone the repository
- Java version should be 1.8 (To avoid any compilation errors).
- Build the application using the following command: mvn clean install
- Deploy the war file to a Tomcat server.

## RESTful Web Service End Point:
|              URI                   |                               Description                               |
|------------------------------------|-------------------------------------------------------------------------|
|/elreddit-search/search?q={query}   | Search Reddit Comments and sort them according the 'author' attribute   | 


 