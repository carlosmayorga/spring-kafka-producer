# :octocat: spring-kafka-producer
A Spring Kafka :envelope: producer :outbox_tray:


## First download Kafka https://kafka.apache.org/downloads
### Importants commands
- Zookeeper: `./zookeeper-server-start.sh ../config/zookeeper.properties`
- Broker 0: `./kafka-server-start.sh ../config/server.properties`
- Broker 1: `./kafka-server-start.sh ../config/server1.properties`
- Broker 2: `./kafka-server-start.sh ../config/server2.properties`
- Lists all Topics: `./kafka-topics.sh --zookeeper localhost:2181 --list`
- Inspect a Topic: `./kafka-topics.sh --zookeeper localhost:2181 --describe --topic library-events`
- Start the console consumer from term: `./kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic library-events`


## Start the Spring App
1. gitpull
2. eclipse --> Run as --> `Maven install`
3. eclipse --> Run as --> `Spring Boot App`
