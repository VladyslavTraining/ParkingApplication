FROM java:latest
WORKDIR /app
ADD . .
RUN mvn install
