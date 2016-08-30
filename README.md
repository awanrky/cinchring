Cinchring

* Run 'vagrant up'
* connect to mysql database instance on instance of vagrant machine user cinchring pw cinchring
* create a new database for cinchring 'create database cinchring;'
* change password for cinchring user
* edit application.properties giving the new password for the cinchring user
* ssh into the vagrant instance 'vagrant ssh'
* cd /vagrant
* run './mvnw spring-boot:run' to build and run the web server
* (or you can run './mvnw clean package' to build an executable jar, and then run 'java -jar target/cinchring-0.0.1-SNAPSHOT.jar' to run the web server
