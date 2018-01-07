FROM java:8-jre
MAINTAINER Dmitriy Shevchenko <d.t.shevchenko@gmail.com>

ADD ./target/inner-sensor-simulator.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/inner-sensor-simulator.jar"]


EXPOSE 8102