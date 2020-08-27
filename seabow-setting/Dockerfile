FROM java:8u111-jdk
ADD target/*.jar seabow-setting.jar
EXPOSE 8081
ENV TZ=Asia/Shanghai
ENTRYPOINT ["java","-jar","/seabow-setting.jar","--spring.profiles.active=prod"]