# Gemfire Exercise  [![Run Unit and Integration Tests](https://github.com/ameya2353/GemfireExerciseUBS/actions/workflows/code-unit-test.yml/badge.svg)](https://github.com/ameya2353/GemfireExerciseUBS/actions/workflows/code-unit-test.yml)
 This repo contains the code for gemfire exercise.

### Tasks Give
> 1. Write a program that can take inputs from either XML, JSON and CSV format to persist data in Gemfire Cache.
- I have created a spring boot application that contains both the server and the client cache. 
- Server side has the spring boot app main method, as it is needed for integration testing. The client side does not have it as it is taken care by the test configuration.

- The main logic has namely 3 important parts.
  ### 1.  Converter

    - A converted is responsible to converter the data from the given format to List of persistable objects.
    - In this exercise they are namely [CSVConverter.java](src/main/java/com/ubs/exercise/client/converter/csv/CSVConverter.java) , [JsonConverter.java](src/main/java/com/ubs/exercise/client/converter/json/JsonConverter.java) and [XMlConverter.java](src/main/java/com/ubs/exercise/client/converter/xml/XMLConverter.java).
    - These classes are generic and can be used to convert any given type if Domain object from files to POJOs.
    - Since xml and json are structured data representation the java equivalent can be retrived easily using JAXB and Jackson libraries.
    - CSV on other hand is a linear-structured data representation, hence we need to tell the program how is the mapping done to the POJO.
    - The implementation contains mapper support of all 3 type. But only CSV has been implemented as other were not required.
    - The only purpose of these implementation is to convert data to POJO, if any further format is required then the converter has to be introduced implementing the [IConverter](src/main/java/com/ubs/exercise/client/converter/IConverter.java) interface

    ### 2.  Persister
    
    - A persister is responsible to persist a list of data provided to it. The list must be of Domain object annotated by the Spring Data Geode persistence annotations.
    - This persister can be used in other application as well as it is abstracted out of this particular scenario, hence making it reusable in any scenario where persistence is required.
    - Pesister is of generic type, hence can be used with any given type of Domain object.
    - The implementation of the persister resides in [Persister.java](src/main/java/com/ubs/exercise/client/persister/Persister.java)

    ### 3.  Configuration
    
    - There has to be a connection between the Domain Model and all the beans needed for that particular module to be persisted. Configuration class is used for the same.
    - To simplify the configuration for future perspective [ToCacheConfiguration.java](src/main/java/com/ubs/exercise/client/configurations/ToCachConfiguration.java) has been implemented with all the beans needed to be initialized.This class is generic and can be used for any domain object needed.
    - For this exercise the User class is used, hence [UserToCacheConfiguration.java](src/main/java/com/ubs/exercise/client/configurations/UserToCachConfiguration.java) is created by extending ToCacheConfiguration.java and passing all the details required. The constructor of UserToCachConfiguration specifies which class it was to be defined to ie. [User.java](src/main/java/com/ubs/exercise/client/model/user/User.java)

- Using the above given design I created the code which converts and persists any given data format into the Gemfire Cache.

> 2. Once the data is persisted in cache, there needs to be a server function which will export the data as JSON.
- For this particular task, I have created a Gemfire function [GemfireExerciseFunction](src/main/java/com/ubs/exercise/server/functions/GemfireExerciseFunction.java).
- It's a pretty straight forward class containing 2 methods, one is to export all the data into  JSON array string format and other once is to search for particular data using ID and export it as JSON string
- Since it is going to be run on user region I have autowired the region into the class.
- @GemfireFunction annotation specifies it is a server function and id has to be provided with in this case is **convertAllToJson** and **convertToJson** for 2 functions respectively.

> 3. Best Practices of SOLID principle and Testing pyramid should be followed.
- The design in implementation of the code is done keeping SOLID principles in mind.
- I have implemented Unit testing upon the converters and persister and the UserService and integration testing on the UserService with connection to a local caching server.
- In unit testing senario the cache that is being used is the client cache (ClientRegionShortcut.LOCAL)

**The code base is integrated with Github workflow for CI/CD. The tests are automatically run after the push to master happens.**