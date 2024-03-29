# Welcome to the DHT Logger Tutorial!

## Quick links

* [UCW Platform][1]
* [UCW Platform Documentation][2]
* [GitHub][3]
* [Issues][4]

## Description

The DHT Logger is an example how to implement a data logger for DHTx sensors for UCW Portal.

### Quick start

 * cd "project root"
 * ./build.sh
 * ./run-portal.sh
 * Browse to [http://localhost:9601](http://localhost:9601).
 * Use default user "admin" and password "admin" to login


#### Sensor Data Generator

The DHT Logger uses data stream <b>ucw-dhtlogger</b> to read data for temperature, humidity 
and heat index from DHTx sensors. Data can be generated when you enable generator in 
<i>pom.xml</i> file by changing to following

<code>\<jvmArgs\>-Ducw.dhtlogger.generator.enabled=true\</jvmArgs\></code> 

[1]: https://unitycloudware.com
[2]: https://docs.unitycloudware.com
[3]: https://github.com/unitycloudware
[4]: https://jira.unitycloudware.com/browse/UCW