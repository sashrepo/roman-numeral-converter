# roman-numeral-converter

**roman-numeral-converter** is a java based spring-boot application that exposes a GET based REST API to convert integer
into it's respective roman numeral representation.
> Developed by: Saravanesh Selvaraj

## Table of Contents

- [Frameworks and Technologies Used](#frameworks-and-technologies-used)
- [Architecture](#architecture)
- [Packaging Layout](#packaging-layout)
- [How to build and deploy the stack?](#how-to-build-and-deploy-the-stack)
- [Setting up the Environment](#setting-up-the-environment)

### Frameworks and Technologies Used

* Java 11
* spring-boot
* Maven
* Junit 5
* Mockito
* ELK (ElasticSearch, Logstash, Kibana)
* Prometheus
* Grafana
* Docker

### Architecture

![img_1.png](images/roman-numeral-converter-architecture.png)

### Packaging Layout

![img_2.png](images/application-packaging-layout.png)

### How to build and deploy the stack?

1. Clone the git repo

```
git clone https://github.com/sashrepo/roman-numeral-converter.git
```

2. Pre-requisite checks for required frameworks

```
java -version
docker -v
docker-compose -v
mvn -v
```

3. For convenience to run the application, I have bundled all the required commands in one shell script, you can just
   run the shell script or run individual commands in the shell script by yourself to start the whole application stack
   along with the devops capabilities as shown in the architecture diagram,

```
cd docker
sh runWholeStack.sh
```

the shell script, first runs a maven package command and then uses docker compose to spin the whole docker infra. The
whole process should take around 2 to 3 mins depending on the underlying machine, you should see a similar output as
shown below with status of various services,
![img_3.png](images/docker-compose-status.png)
Use the below command to refresh the status of the services until you see all the services Up and elasticsearch, kibana
and apm server reported as Up (healthy)

```
docker-compose -f docker-compose.yml ps
```

4. Verify if the deployed services are up and running,

> roman-numeral-converter App - http://localhost:8080/actuator/health
>
> REST API specs - http://localhost:8080/swagger-ui.html
>
> APM Server - http://localhost:8200/
>
> Elasticsearch - http://localhost:9200/
>
> Prometheus - http://localhost:9090/
>
> Grafana - http://localhost:3000/

### Setting up the Environment

