Dependencies and Prerequisites
================================

### Summary of Prerequisites: 
* [Java 8 JDK](#java-jdk)
* [Spring Tools 4](#spring-tools-4)
* [MySQL Server 5.1 or later](#mysql)
* [MySQL Workbench](#mysql)
* [Apache Tomcat 9 \(optional\)](#apache-tomcat)


<a id="java-jdk"></a>Java 8 JDK 
--------------------
Download the appropriate installer for your operating system from the [Java 8 download page.](https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html)

### Windows Installation 

The preferred Windows installer is **jdk-8u251-windows-x64_bin.exe**.

#### Creating PATH Enviroment Variable 

1. Go to the **Control Panel** and then **System**.

2. Enter **Advanced** and then **Enviroment Variables**.

3. Add the location of the bin folder to the **PATH** variable in **System Variables**. This location will typically look like this:
```
C:\Program Files\Java\jdk1.8.0_251\bin

```

<a id="spring-tools-4"></a>Spring Tools 4
--------------------
We recommend using the Spring Tools 4 Suite with Eclipse integration.

You can download the appropriate installer for your operating system from [spring.io/tools](https://spring.io/tools).

Once installed, go to [start.spring.io](https://start.spring.io/) and generate a Spring Boot Setup with the following configuration:

![Spring Boot Setup Configuration](pictures/SpringBootSetup20191210.png)

Importing the generated folder into Spring Tools 4 will give you the proper configuration for this project.

<a id="mysql"></a>MySQL Server and MySQL Workbench
---------------------
This project requires **MySQL 5.1 or later**. 

You can download the appropriate installer for your operating system from [mysql.com/downloads](https://dev.mysql.com/downloads/installer/). There are two installlers on the download page. For this project it is recommended you download **mysql-installer-community-8.0.19.0**.

You will need to login to an Oracle account or create a new Oracle account to begin the download. 

### Windows Installation 
After running the installer you will be prompted to select which MySQL products to install. Select MySQL Server and MySQL Workbench. 

![MySQL Installation Product Selection](pictures/mysql_installation.PNG)

Once MySQL Server and MySQL Workbench have been installed, you will be prompted to configure the products. It is recommended to use the default configuration settings. 

When creating the MySQL database, the user is **root@localhost** and the password can found in **src/main/resources/application.properties**.

### MacOS Installation

MySQL Workbench may require a MacOS system update. 

<a id="apache-tomcat"></a>Apache Tomcat 9
----------------------
**If you are using Spring Tools 4 with Maven you do not need to download Apache Tomcat 9.** 
Only download Apache Tomcat 9 if you're are using the regular Eclipse IDE for development.

You can download Apache Tomcat 9 at [https://tomcat.apache.org/download-90.cgi](https://tomcat.apache.org/download-90.cgi).

It is recommended to download one of the Core packages under the Binary Distributions section. 

### Windows Installation 
Download the Tomcat 64-bit-Windows.zip Binary Distribution and extract the contents to your preferred destination. 

See **RUNNING.txt** within the extracted **apache-tomcat-9.0.30** folder for instructions on how to setup Apache Tomcat 9. 