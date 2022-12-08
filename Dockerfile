FROM gradle:7.5.1-jdk11-alpine AS BUILD_IMAGE
RUN mkdir /home/src
COPY ./ /home/app/
WORKDIR /home/app
RUN gradle build

FROM openjdk:11-jre-slim
RUN mkdir /home/app
WORKDIR /home/app
COPY --from=BUILD_IMAGE /home/app/build/libs/Converter-0.0.1-SNAPSHOT.jar .
CMD ["java","-jar","Converter-0.0.1-SNAPSHOT.jar"]