# Satellite integration
Satellite integration system which allows to control the parts of the satellite with a RabbitMQ broker mediating the transfer

== Steps

* Start RabbitMQ server:

[source]
----
rabbitmq-server
----


* Start the satellite command processor:

[source]
----
cd saterllite-integration-handler
mvn spring-boot:run
----

* Start the Command Dispatcher:

[source]
----
cd saterllite-integration-dispatcher
mvn spring-boot:run
----
