FROM tomcat:latest
# Dummy text to test 
 COPY ./target/SF*.war /usr/local/tomcat/webapps
