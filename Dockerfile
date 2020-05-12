FROM openjdk:8-jre
ENTRYPOINT ["/usr/local/openjdk-8/bin/java", "-jar", "product-manager-server.jar"]
ADD target/product-manager-server-1.0.0.jar product-manager-server.jar