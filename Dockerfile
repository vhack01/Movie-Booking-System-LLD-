FROM eclipse-temurin:21-jdk
LABEL authors="vishwas"

WORKDIR /app

COPY . /app

RUN find . -name "*.java" > sources.txt && javac @sources.txt

EXPOSE 5001

CMD ["java", "Main"]