Cinchring

* Run 'vagrant up'
* if this is the first time running this box, you may need to manually fix a network problem
    * start the gui and see if you're getting a cloud-init-nonet error waiting for 120 seconds for network device if so...
        * wait the 120 seconds for it to give up
        * while waiting check out this thread... https://github.com/mitchellh/vagrant/issues/3860
        * the message 'Waiting for network configuration...' will now show.  Wait for it to give up again
        * once the login prompt shows on the gui 
            * log in
            * edit the /etc/network/interfaces file
            * in the #VAGRANT-BEGIN ... #VAGRANT-END section leave the 'auto eth1' line, but remove the next two lines and then save the file
            * shut down the vm (vagrant halt)
    *  if you get an authorization error when running vagrant up... 
        * review this thread, particularly the comment made by sthum on Apr 20 https://github.com/mitchellh/vagrant/issues/5186
        * log into the vm using the gui
        * run the following commands inside the vm, from the home directory of the vagrant user
            * wget --no-check-certificate https://raw.githubusercontent.com/mitchellh/vagrant/master/keys/vagrant.pub -O .ssh/authorized_keys
            * chmod 700 .ssh
            * chmod 600 .ssh/authorized_keys
            * chown -R vagrant:vagrant .ssh
* connect to mysql database instance on instance of vagrant machine user cinchring pw cinchring
* change password for cinchring user
* edit application.properties giving the new password for the cinchring user
* ssh into the vagrant instance 'vagrant ssh'
* cd /vagrant
* run './mvnw spring-boot:run' to build and run the web server
* (or you can run './mvnw clean package' to build an executable jar, and then run 'java -jar target/cinchring-0.0.1-SNAPSHOT.jar' to run the web server
