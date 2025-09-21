# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is a Spring Boot 3.5.5 application demonstrating CORS (Cross-Origin Resource Sharing) implementation using Java 21. The application serves as a backend resource server to demonstrate different CORS scenarios: simple requests, preflighted requests, and credentialed requests.

## Build and Development Commands

### Building and Running
- **Build**: `./gradlew build`
- **Run application**: `./gradlew bootRun`
- **Run tests**: `./gradlew test`
- **Clean build**: `./gradlew clean build`

The application runs on port 10000 by default (configured in `src/main/resources/application.yml`).

### Testing
- Uses JUnit Platform for testing
- Test command: `./gradlew test`
- Test classes are located in `src/test/java/`

## Architecture and Key Components

### Package Structure
- `com.github.jaewookmun.corsresources` - Root package
  - `api/` - REST controllers
  - `config/` - Spring configuration classes
  - `filter/` - Custom CORS filter implementations

### CORS Implementation Strategy

The application demonstrates three distinct CORS handling approaches:

1. **Simple Request CORS** (`/cors/simple-request`)
   - Handled by `SimpleRequestCorsFilter`
   - Sets basic `Access-Control-Allow-Origin: *` header
   - Applied via filter registration in `FilterConfig`

2. **Preflighted Request CORS** (`/cors/preflighted-request`)
   - Handled by `PreflightedRequestCorsFilter`
   - Supports OPTIONS preflight requests
   - Configures allowed methods, headers, and cache settings
   - Handles custom headers like `Custom-Header-Text`

3. **Credentialed Request CORS** (`/cors/credentialed-request`)
   - Handled by `CredentialedRequestCorsFilter`
   - Sets `Access-Control-Allow-Credentials: true` for cookie/session support
   - Uses specific allowed origins (localhost:3000, 127.0.0.1:3000) instead of wildcard
   - Supports authentication headers and session management
   - Handles both preflight OPTIONS and actual requests

### Key Files
- `FilterConfig.java` - Registers CORS filters for specific URL patterns
- `SimpleRequestCorsFilter.java` - Basic CORS handling for simple requests
- `PreflightedRequestCorsFilter.java` - Advanced CORS with preflight support
- `CredentialedRequestCorsFilter.java` - CORS with credentials and origin validation
- `ApiController.java` - REST endpoints demonstrating different CORS scenarios

### Filter Registration Pattern
The application uses `FilterRegistrationBean` to apply different CORS filters to specific endpoints rather than global CORS configuration. This allows demonstrating different CORS behaviors on different endpoints.

## Development Notes

- Uses Lombok for logging (`@Slf4j`)
- Spring Boot auto-configuration enabled
- Custom filters extend `OncePerRequestFilter`
- Filter order and URL pattern mapping configured in `FilterConfig`