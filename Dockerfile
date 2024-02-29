FROM openjdk:17-slim

MAINTAINER utkarshsh1424@outlook.com

COPY /build/libs/accounts-0.0.1-SNAPSHOT.jar accounts-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "accounts-0.0.1-SNAPSHOT.jar"]