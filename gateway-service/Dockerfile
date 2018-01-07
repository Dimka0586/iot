FROM java:8-jre
MAINTAINER Dmitriy Shevchenko <d.t.shevchenko@gmail.com>

ADD ./target/gateway-service.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/gateway-service.jar"]

EXPOSE 8100