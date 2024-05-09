FROM openjdk:17
ADD build/libs/test-todo-app-0.0.1-SNAPSHOT.jar todoapp.jar
ENTRYPOINT ["java", "-jar", "/todoapp.jar"]