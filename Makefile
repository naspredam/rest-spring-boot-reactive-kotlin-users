start:
	./gradlew build
	docker build -t rest-spring-boot-kotlin-users .
	docker-compose up

stop:
	docker-compose down
