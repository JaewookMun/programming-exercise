# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is a Spring Boot 3.5.5 application demonstrating CORS (Cross-Origin Resource Sharing) functionality using Java 21 and Gradle. The project uses Spring Web MVC with Thymeleaf templating engine for web development.

## Build and Development Commands

### Building the Application
```bash
./gradlew build
```

### Running the Application
```bash
./gradlew bootRun
```

### Running Tests
```bash
./gradlew test
```

### Running a Single Test Class
```bash
./gradlew test --tests "com.github.jaewookmun.corsweb.CorsWebApplicationTests"
```

## Project Structure

- **Main Application**: `src/main/java/com/github/jaewookmun/corsweb/CorsWebApplication.java` - Standard Spring Boot main class
- **Web Layer**: `src/main/java/com/github/jaewookmun/corsweb/web/` - Contains web controllers and MVC components
- **Resources**: 
  - `src/main/resources/application.yml` - Spring Boot configuration
  - `src/main/resources/templates/` - Thymeleaf templates (empty)
  - `src/main/resources/static/` - Static web resources (empty)

## Key Dependencies

- Spring Boot Starter Web - REST API and web functionality
- Spring Boot Starter Thymeleaf - Template engine
- Spring Boot DevTools - Development-time features
- Lombok - Boilerplate code reduction
- JUnit 5 - Testing framework

## Architecture Notes

This is a minimal Spring Boot application focused on CORS demonstration. The web layer is located in the `com.github.jaewookmun.corsweb.web` package where web controllers should be implemented. The application uses standard Spring Boot conventions and is configured via `application.yml`.