FROM alpine:latest
COPY target/* .
CMD ['mvn quarkus:dev']
