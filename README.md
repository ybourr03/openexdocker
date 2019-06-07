# com.openex.docker.demo

OpenEx packaged template to help you start a new OpenEx in a Docker context with a default camel route.

If you need documentation about OpenEx : see here https://sites.google.com/decathlon.com/openex/resources

**TODO** : Put your credentials and directory in docker registry in the ```Jenkinsfile```.



**Prerequisistes :**
* valid maven install: https://maven.apache.org/install.html
* valid jdk8 install

1. First build the packaged server

```shell
mvn clean install
```

The server is generated in ```target``` directory.

2. Then you can run the SpringBoot server

```shell
java -jar target/myapplication-1.0.0-SNAPSHOT.jar
```


**Prerequisistes :**
* valid docker install: https://www.docker.com/community-edition



**Prerequisistes :**
* valid docker install: https://www.docker.com/community-edition
* valid docker-compose install: https://docs.docker.com/compose/install/

This docker-compose will start your Camel route with an ActiveMQ broker and a Hawtio console.

1. First build the image

```shell
docker-compose build
```

2. Then you can start your docker container

```shell
docker-compose up -d
```

You can tail the logs with the following command :

```shell
docker-compose logs -f
```

To stop your container :

```
docker-compose down
```