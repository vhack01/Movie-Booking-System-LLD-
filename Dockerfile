FROM ubuntu:latest
LABEL authors="vishwas"

WORKDIR /app

COPY . /app

EXPOSE 5001

CMD ["java", "Main"]