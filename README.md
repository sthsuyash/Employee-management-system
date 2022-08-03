# Spring Boot backend for Employee Management System and database: MySQL

- ## Develop Spring Boot Backend Application

  Following are five REST APIs (Controller handler methods), developed for Employee resource.

  | S.N. | Api Name          | HTTP Method | Path            | Status Code          | Description                           |
  | ---- | ----------------- | ----------- | --------------- | -------------------- | ------------------------------------- |
  | 1    | GET Employee      | GET         | /employees      | 200<br/>(OK)         | All Employee resources are fetched.   |
  | 2    | POST Employee     | POST        | /employees      | 201<br/>(Created)    | A new Employee resource is generated. |
  | 3    | GET Employee      | GET         | /employees/{id} | 200<br/>(OK)         | One employee resource is fetched.     |
  | 4    | PUT/EDIT Employee | PUT         | /employees/{id} | 200<br/>(OK)         | Employee resource is updated.         |
  | 5    | DELETE Employee   | DELETE      | /employees/{id} | 204<br/>(No Content) | Employee resource is deleted.         |

<br/>

- ## Project Setup

  Using spring initializer web ( [start.spring.io](https://start.spring.io/) ) or in IntelliJ IDEA Ultimate Edition

<br/>

- ## Maven Dependencies

  ```xml

  <dependencies>

      <dependency>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-starter-data-jpa</artifactId>
      </dependency>

      <dependency>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-starter-web</artifactId>
      </dependency>

      <dependency>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-devtools</artifactId>
          <scope>runtime</scope>
          <optional>true</optional>
      </dependency>

      <dependency>
          <groupId>mysql</groupId>
          <artifactId>mysql-connector-java</artifactId>
          <scope>runtime</scope>
      </dependency>
      <dependency>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-starter-test</artifactId>
          <scope>test</scope>
      </dependency>
  </dependencies>

  <build>
      <plugins>
          <plugin>
              <groupId>org.springframework.boot</groupId>
              <artifactId>spring-boot-maven-plugin</artifactId>
          </plugin>
      </plugins>
  </build>
  </project>

  ```
  
<br/>

- ## Configuring application.properties to connect to MySQL database

  ```application.properties
  spring.datasource.url = jdbc:mysql://localhost:3306/ems
  spring.datasource.username = root
  spring.datasource.password = pass

  ## Hibernate Properties
  # The SQL dialect makes Hibernate generate better SQL for the chosen database
  spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQL5InnoDBDialect

  # Hibernate ddl auto (create, create-drop, validate, update)
  spring.jpa.hibernate.ddl-auto = update
  server.servlet.context-path=/springboot-crud-rest
  ```
