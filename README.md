# Development

* JDK 1.8 required
* MySQL
    * Start MySQL instance
    * Create DataBase using this cmd: `CREATE DATABASE housemanager CHARACTER SET utf8 COLLATE utf8_general_ci;`

* Build 
    * Change the mysql username / pwd in application.properties

* Running the tests
    * Change the DB connection settings in `sr/test/resources/application-test.properties`

* ENV Variables:
    * `SPRING_DATASOURCE_URL` URL to MYSQL instance
    * `SPRING_DATASOURCE_USERNAME` MYSQL username
    * `SPRING_DATASOURCE_PASSWORD` MYSQL password
    
# Documentation  
After starting the server you can find the API documentation under http://localhost:8080/swagger-ui.html


## Authors

* **Radhouene ROUACHED** - *Initial work* 

## License

GPL 3.0, see [LICENSE](https://www.gnu.org/licenses/gpl-3.0.en.html).
