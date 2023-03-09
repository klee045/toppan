# ​Overview

You're on a mission to help the local Library to build an MVP books records system to replace the current paper records they have been using.

This is an MVP for us to gather more feedback. As a result, we have omitted a login feature as we are not sure how we are going to control usage nor what kind of authentication mechanism we will be using.

# Backend

**Spring Boot** is used as the main framework to develop the APIs and model the business requirements.

**PostgreSQL** is used as the database.

## Requirements

1. [Java](https://www.java.com/en/).
2. [Apache Maven](https://maven.apache.org/download.cgi). To set it up, follow the instructions from their [guide](https://maven.apache.org/install.html).

## How to run

1. In your terminal, change directory to the base directory of the application: `cd demo`
2. Run the command: `mvn spring-boot:run`
3. If all is good, you will see the following image:
   ![run_app](https://user-images.githubusercontent.com/66959182/223981072-8d19bc8a-bff3-4fcf-b257-8658e12113fa.png)

4. We can also test it via [Postman](https://www.postman.com/downloads/). Sending a GET request to `http://localhost:8080/book/getTop3ReadBooks?country_code=us` will yield us the following result as seen in the image:
   ![get_req](https://user-images.githubusercontent.com/66959182/223981141-93eb2c76-9d7d-4fd7-8e61-fde60dcb8382.png)

