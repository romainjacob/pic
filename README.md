### HOW TO INSTALL

First we need a docker host with an open tcp port.

* Edit the file /lib/systemd/system/docker.service

```
sudo vi /lib/systemd/system/docker.service
```
* Modify the line that starts with ExecStart to look like this:

```
ExecStart=/usr/bin/docker daemon -H fd:// -H tcp://0.0.0.0:4243 -H unix:///var/run/docker.sock
```
* Restart the Docker service

```
sudo service docker restart
```
* Test that the Docker API is indeed accessible:

```
curl http://localhost:4243/version
```

We need to build the jenkins-slave

```
docker build -t jenkins-slave docker/jenkins-slave/
```

Build the docker-compose file

```
cd docker
docker-compose build
```

Run the docker-compose file

```
docker-compose up
```


