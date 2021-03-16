Development Environment Notes
======================================
Table of Contents
-----------------

<!--ts-->
   * [Prerequisites](prerequisites.md)
   * [Running the Project](running.md)
   * [Logging](logging.md)
   * [Testing](testing.md)
   * RESTful Controller Pseudocode
       - [Local Market Agent](lma_pseudocode.md)
       - [Local Market Engine](lme_pseudocode.md)
       - [Transactive Energy User Agent](teua_pseudocode.md)
   * [RESTful Controller Payloads](uri_structure.md)
   * [Position Manager](position_manager.md)
   * [Web Services](web_services.md)
   * [Continuous Integration](travis_CI.md)
   * [Parity Terminal Client](ParityRest\README.md)
<!--te-->

Project Build Steps
-------------------

Most work is in the **development** branch of the repository. Any documentation work is done in the **documentation** branch. 

1.  Download prequisites and tooling as needed. See [Prerequisites.MD](prerequisites.md) for more information. **NOTE: If you are cloning the
    code from GitHub, you need only the Java SDKs, Spring Tool IDE, Apache
    Tomcat Server and MySQL server.** 

2.  Install the MySQL Server on your machine

3.  Clone the current project into your local machine. Checkout may require the
    **-f** flag. (Substitute the repository you're working with as necessary)
```
git clone https://github.com/EnergyMashupLab/NIST-CTS-Agents  
git checkout development  
git pull origin development
```

4.  Import the project into your SpringTool IDE

5.  Select options from the popup menu from right-clicking the project to build
    the project

## Additional Information

NIST-CTS-Agents uses Spring Tools 4 and Maven for enterprise tools. The [Maven Users Centre](https://maven.apache.org/users/index.html) provides documentation and tutorials on how to use Maven in a development setting.

This project also uses git and Github for its version control needs. If you are unfamiliar with git, tutorials and a cheat sheet can be found at [https://www.atlassian.com/git/tutorials](https://www.atlassian.com/git/tutorials/atlassian-git-cheatsheet)

### Running your code
To run your code in the IDE You should have a working MySQL database locally
with a database named **njit_cts_eml**. See the [Running documentation](running.md) for more information.

For more detail on creating the MySQL database. Login user (root\@localhost)
and password are in src/main/resources/application.properties. 

In Eclipse
```
(right click on project -> run as -> Spring boot app
```
This will start your tomcat server automatically.

### Testing

See [Testing documentation](testing.md) for details.

