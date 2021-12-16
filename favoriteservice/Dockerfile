FROM java:8-jre
WORKDIR usr/src
#ENV MONGO_DATABASENAME=favoritesDB
#ENV MONGO_URI=mongodb://mongo/favoritesDb
ADD ./target/favoriteservice-1.0.0.jar /usr/src/favoriteservice-1.0.0.jar
ENTRYPOINT ["java","-jar","favoriteservice-1.0.0.jar"]
