FROM adoptopenjdk/openjdk11:alpine-jre
ARG JAR_FILE=target/ms-bank-account-*-SNAPSHOT.jar
COPY ${JAR_FILE} ms-bank-account.jar
RUN addgroup -S bootcampgroup && adduser -S bootcampuser -G bootcampgroup
RUN mkdir -p /opt/logs/ms-bank-accounts
RUN chown -R bootcampuser:bootcampgroup /opt/logs/ms-bank-accounts
USER bootcampuser:bootcampgroup
ENTRYPOINT ["java","-jar","/ms-bank-account.jar"]
