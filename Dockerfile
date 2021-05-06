FROM tomcat:latest
# Dummy text to test 
COPY ./target/SFB*.war /usr/local/tomcat/webapps/SFB.war
