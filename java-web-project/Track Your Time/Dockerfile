# 1. Start with an official Tomcat image that includes Java 21
# This is your "server"
FROM tomcat:10.1-jdk21

# 2. Remove the default welcome page from Tomcat
RUN rm -rf /usr/local/tomcat/webapps/ROOT

# 3. Copy your built .war file from the "target" folder 
# and rename it to ROOT.war. This makes your app
# the default one (e.g., .../Track_Your_Time/ becomes /)
COPY target/Track_Your_Time.war /usr/local/tomcat/webapps/ROOT.war