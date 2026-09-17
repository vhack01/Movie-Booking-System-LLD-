FROM eclipse-temurin:21-jdk
LABEL authors="vishwas"

WORKDIR /app

COPY . /app

RUN javac Main.java

EXPOSE 5001

CMD ["java", "Main"]