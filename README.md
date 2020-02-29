[![Build Status](https://travis-ci.org/NetworkedCoders/speech-rank.svg?branch=master)](https://travis-ci.org/NetworkedCoders/speech-rank)

# speech-rank
Web application to rate and comment on speeches from IT conferences

## Project development

### Prerequisites

-   JDK 7+
-   Node.js (4 < version < 12)
-   Gradle 2.11 (optional, ./gradlew will install if missing)

### Install dependencies

`npm install`

### Build frontend resources

`npm run gulp` will build on-start and watch later changes

### Run frontend server

In a new terminal window run

`npm run local-web-server`

### Build and run backend server

`./gradlew run`

### Notes

Please note that since we use Google YouTube Data API, an APIKey needs to be provided but must not be stored on GitHub. Therefore it is provided only on VM on koding.com.

### To make the project compile in IntelliJ

1. Install Lombok plugin for Intellij
2. Enable annotations processing (Preferences | Build, Execution, Deployment | Compiler | Annotation Processors)
3. Restart IntelliJ and import dependencies to project
