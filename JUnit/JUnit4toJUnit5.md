# Dependencies and setup

This section describes how we can create a maven project that can compile and run unit tests in JUnit 5. 
At the end of this section , we can:
   * Get the required dependencies with maven
   * Understand how we can configure [Maven Surefire Plugin](https://maven.apache.org/surefire/maven-surefire-plugin/)
   * Learn how we can run our unit tests with maven.
   
## Getting required dependencies

   * In order to use JUnit 5, we need to use Java jdk version 8.

   * Declare ```junit-jupiter-api``` dependency in ```pom.xml``` .
 This dependency provides the public API for writing tests and extensions. 
``` 
 <dependency>
     <groupId>org.junit.platform</groupId>
     <artifactId>junit-platform-launcher</artifactId>
     <version>1.3.2</version>
     <scope>test</scope>
 </dependency>
 <dependency>
     <groupId>org.junit.jupiter</groupId>
     <artifactId>junit-jupiter-engine</artifactId>
     <version>5.3.2</version>
     <scope>test</scope>
 </dependency>
 
```
   ### Add support for JUnit 4 tests cases.
    
   If we need to migrate from JUnit 4 to J Unit 5 and keep tests written in JUnit 4,
   add dependency for that:
   ```
   <dependency>
       <groupId>org.junit.vintage</groupId>
       <artifactId>junit-vintage-engine</artifactId>
       <version>5.3.2</version>
       <scope>test</scope>
   </dependency>
   
   ```
   
   ### Maven
   Starting with version 2.22.0, Maven Surefire provides native support for executing tests on the JUnit Platform.
   
   
   ### Configure test  Engines
   
   To configure support for JUnit Jupiter based tests, configure test scoped dependencies on the JUnit Jupiter API and 
   the JUnit Jupiter TestEngine implementation similar to the following.
   
   ```
   <build>
       <plugins>
           <plugin>
               <artifactId>maven-surefire-plugin</artifactId>
               <version>2.22.0</version>
           </plugin>
       </plugins>
   </build>
   
   ```
   
   Check [pom.xml](../pom.xml) for list of dependencies. To check the basic setup run 
   [JUnit5](../src/test/java/learning/JUnit5/LifecycleTest.java). 
   
   Let´s start writing JUnit-5 tests ! [Go](../src/test/java/learning/JUnit5/annotations)
