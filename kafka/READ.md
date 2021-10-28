kafka 部署链接:https://blog.70ci.com/post/736.html

1. 启动 `zookeeper`

```shell
docker run -d --name zookeeper -p 2181:2181 -t zookeeper
```

2. 启动 `kafka`

* 脚本

```shell
docker run -d --name kafka --publish 9092:9092 --link zookeeper --env KAFKA_ZOOKEEPER_CONNECT=zookeeper:2181 --env KAFKA_ADVERTISED_HOST_NAME=你的宿主ip --env KAFKA_ADVERTISED_PORT=9092 --volume /etc/localtime:/etc/localtime wurstmeister/kafka:latest
```

* 实际上

```shell
docker run -d --name kafka --publish 9092:9092 --link zookeeper --env KAFKA_ZOOKEEPER_CONNECT=zookeeper:2181 --env KAFKA_ADVERTISED_HOST_NAME=www.xiaowenshu.cn --env KAFKA_ADVERTISED_PORT=9092 --volume /etc/localtime:/etc/localtime wurstmeister/kafka:latest
```

3. 使用 docker 命令进入 `kafka` 容器内

```shell
docker exec -it 你的kafka容器id /bin/bash
```

4. 进入 `/opt/kafka_xxx/`,创建一个 `Topoic` 名为 `test01`

```shell
bin/kafka-topics.sh --create --zookeeper zookeeper:2181 --replication-factor 1 --partitions 1 --topic test01
```

* 查看 `Topic` 列表

```shell
bin/kafka-topics.sh --list --zookeeper zookeeper:2181  //查看我们的topic列表
```

5. 然后我们运行一个生产者去发送消息

```shell
bin/kafka-console-producer.sh --broker-list localhost:9092 --topic test01
```

6. 再去创建一个消费者接收消息，也可以另起一个连接去接收消息

```shell
bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic test01 --from-beginning
```