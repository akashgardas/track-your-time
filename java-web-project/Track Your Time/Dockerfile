# ----- STAGE 1: The "Build" Stage -----
# We start with a Maven/Java 21 image to build the project
FROM maven:3.9-eclipse-temurin-21 AS build

# Set the working directory inside the builder
WORKDIR /app

# Copy JUST the pom.xml first to cache dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the rest of the source code
COPY src ./src

# Run the Maven build to create the .war file
# This runs "mvn clean install"
RUN mvn package -DskipTests


# ----- STAGE 2: The "Run" Stage -----
# Now we start fresh with the Tomcat server
FROM tomcat:10.1-jdk21

# Remove the default welcome page
RUN rm -rf /usr/local/tomcat/webapps/ROOT

# Copy the .war file we created in the "build" stage
# from its "target" folder into Tomcat's webapps folder.
COPY --from=build /app/target/Track_Your_Time.war /usr/local/tomcat/webapps/ROOT.war