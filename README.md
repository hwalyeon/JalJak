# ABWMS
<B> ABWMS </B>

# detail
* Springboot
* mybatis
* jwt

# build
* maven build
```
./mvnw clean package
```
* war file was created in 'target' directory

# execute
```
java -jar ${warfile}
```
or
symbolic link to /etc/init.d
```
sudo systemctl start ${service}
```
need env value
```
sudo -E KEY:VALUE systemctl start ${service}
```

# requirements
* redis - cache, check port
* mariaDB
