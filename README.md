# slogging-api

## Requirements

- [SpringBoot 2.7.5](https://spring.io/projects/spring-boot)
- [SpringBoot Cli 2.7.1](https://docs.spring.io/spring-boot/docs/2.7.5/reference/html/cli.html)

## Prepare

```shell
# lisging initializr
spring init --list

# initialize spring boot project
spring init --build=maven --java-version=11 --force \
    --boot-version=2.7.5 \
    --dependencies=web,data-jpa,hateoas,devtools,h2,mariadb,lombok,actuator \
    --groupId=io.vicevil4.slogging \
    --artifactId=slogging-api \
    --name=slogging-api \
    --package-name=io.vicevil4.slogging.api \
    --description="slogging-api" \
    --packaging=jar

# unpack
unzip slogging-api.zip -x .gitignore
rm slogging-api.zip
```

## Dev

```shell
mvn spring-boot:run
```
