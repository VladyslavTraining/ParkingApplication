mvn clean install
docker rmi -f parking
docker build -f Dockerfile -t parking .
docker run -p 8080:8080 parking