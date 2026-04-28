# Facade module

This module demonstrates a facade for API access.

## Main facade

`solutions.facade.ApiAccessFacade` exposes one method:

```java
String getAttributeValueFromJson(String urlString, String attributeName)
        throws IllegalArgumentException, IOException;
```

It hides three subsystems:
- HTTP GET request handling
- JSON response parsing
- recursive attribute lookup in objects and arrays

## Demo classes

- `solutions.jokes.JokeClient`: baseline direct implementation
- `solutions.facade.FacadeDemo`: usage through facade with Chuck Norris API and agify.io

## Run

```powershell
mvn -pl facade test
mvn -pl facade -DskipTests exec:java -Dexec.mainClass="solutions.jokes.JokeClient"
mvn -pl facade -DskipTests exec:java -Dexec.mainClass="solutions.facade.FacadeDemo"
```

