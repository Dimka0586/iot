FROM java:8-jre
MAINTAINER Dmitriy Shevchenko <d.t.shevchenko@gmail.com>

ADD ./target/config-service.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/config-service.jar"]
HEALTHCHECK --interval=10s --timeout=3s CMD curl -f http://localhost:8101/health || exit 1

EXPOSE 8101