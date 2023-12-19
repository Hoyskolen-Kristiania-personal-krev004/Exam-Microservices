# Exam-Microservices
Repo relating to the Exam of PG3402 Microservices


![An artistic representation of the Architecture](updated_architecture.png?raw=true "Title")

## Services
### Service-Registry
* Uses Spring Cloud Netflix Eureka to register and keep track of running microservices.
* Viewable on address http://localhost:8761 when running by default.
### Config-Server
* Gets configurations for the different Microservices from a git-repository
* Runs on port 8888 by default
* Git repository is available at address https://github.com/Hoyskolen-Kristiania-personal-krev004/config-server-repo
### API-Gateway
* Routes incoming API request to its intended microservice
* Implements load-balancing for the connected microservices
* Runs on port 9191 by default
### User-Service
* Creates, gets, posts and puts User Objects
* Is connected to the user database
* Runs on port 8081 by default
### Rentable-Service
* Creates, gets and posts Rentable Objects
* Can return a combined object made up of a User Object & a Rentable Object
* Is connected to the rentable database
* Is dependent on the User-Service
* Runs on port 8080 by default
### Contract-Service
* Creates, gets and posts Contract Objects
* Can return a combined object made up of a User Object, a Rentable Object, and a Contract Object
* Is connected to the contract database
* Is dependent on the User-Service & the Rentable-Service
* Sends an Asynchronous event to the Transaction-Service upon the creation of a contract
* Runs on port 8082 by default
### Transaction-Service
* Creates and posts Transaction Objects
* Is dependent on the User-Service & the Rentable-Service
* Sends an Asynchronous event to the User-Service upon receiving an event from Contract-Service
* Runs on port 8083 by default

## User Manual
### Startup
#### This assumes that you have a MYSQL server running locally in the computer
#### You will have to create the following databases(schemas): 
* exam_rentables_db
* exam_users_db
* exam_contracts_db
#### You will also have to change the values for MYSQL_USR and MYSQL_PSW, so they match the ones required for your local MYSQL server
#### The Services need to be started locally in the following sequence:
* RabbitMQ (I have used a docker-image of release `3.13.0-rc.3-management` and the command `docker run -rm -it -p 15672:15672 -p 5672:5672 rabbitmq:3.13.0-rc.3-management`)
* Service-Registry
* Config-Server
* API-Gateway
* User-Service
* Rentable-Service
* Contract-Service
* Transaction-Service
* Additionally, there is attached a Zipkin jar file that can be run to see distributed tracing on http://127.0.0.1:9411/ using the command `java -jar zipkin-server-3.0.0-rc0-exec.jar` in the terminal
### Interaction with the application
#### Unfortunately I was unable to finish a frontend in time for the deadline, as such you will have to use Postman API platform to interact with the application
* To interact with the application you can access the APIs directly or utilize the gateway on port 9191 in the following manner:
   * http://localhost:9191/api/users
   * http://localhost:9191/api/rentables
   * http://localhost:9191/api/contracts
   * http://localhost:9191/api/transactions
#### Below I have outlined the format for the different apis and which methods that they handle
* users take POST, GET and PUT
  * The post method takes a JSON as an argument and is called directly on the api/users/ endpoint with the following JSON format
    * {"username": "xxx", "password": "xxx", "FirstName": "xxx", "lastName": xxx, "email": "xxx@xxx.xx", "currentBalance": x}
  * The get methods take a Long or a String as an argument on two separate endpoints: /api/users/`long` and /api/users/usr/`string`
    * `long` is the user id while the `string` is a username(both of these relies on the database having an existing user to call)
  * The put methods takes these formats, where `x` is a `Long` and `y` is an `int`:
    * http://localhost:9191/api/users/x/outgoing-payment?value=y
    * http://localhost:9191/api/users/x/incoming-payment?value=y
* rentables take POST & GET 
  * The post method takes a JSON as an argument and is called directly on the api/rentables/ endpoint with the following JSON format
    * {"rentableName": "xxx", "rentableDesc": "yyy", "ownerId" x}
  * The get method takes a Long (here `x`) for an argument in the following format, and returns a rentable object, as well as the owner of said object if they exist in the database, and a default owner if they don't exist
    * http://localhost:9191/api/rentables/x
* contracts take POST & GET
  * The post method takes a JSON as an argument and is called directly on the api/contracts/ endpoint with the following JSON format and sends an event to transaction service
    * {"startDate": "xxxx.xx.xx.xxxx", "endDate": "xxxx.xx.xx.xxxx", "ownerId" x, "renterId": x, "rentableId" x, "rentalCost": x}
  * The get method takes a Long (here `x`) for an argument in the following format, and returns the contract, the rentable object, the owner of said object, and the renter
    * http://localhost:9191/api/contracts/x
* transactions take POST
  * The post method takes a JSON as an argument and is called directly on the api/transactions/transaction endpoint with the following JSON format and sends an event to transaction service
    * {"receiverId" x, "senderId": x, "value": x}
  ### DISCLAIMER: NO GOOD PROGRAM IS COMPLETE WITHOUT AN UNSOLVABLE BUG. THIS APPLICATION IS NO DIFFERENT. THERE IS A KNOWN ISSUE WITH THIS ENDPOINT. WHEN THIS ENDPOINT IS CALLED IT WILL SUCCESSFULLY CALL UPON THE SAME METHODS AS /api/users/x/incoming-payment?value=y & /api/users/x/outgoing-payment?value=y BUT NO UPDATE WILL BE MADE TO THE DB. USE THE PUT METHODS FOR THE USER ENDPOINT TO SIMULATE WHAT THIS ENDPOINT SHOULD DO
## User Stories
* As an item-owner, I would like to easily be able to rent out items and collect payment in one place.
* As a renter, I would like to have a large selection of potential items to rent
* I have elected to not implement the feedback functionality first outlined in my Arbeidskrav, as it proved to be a rather large undertaking, while being the least important story.

I would like to thank a fellow student for the help in testing my application. I have had constant issues with the load-balancing for no discernible reason, and his help proved vital in troubleshooting and figuring out if the problem was the code or the environment.
If you are also having some trouble with services timing out for no discernible reason, change the pathing in the API-Gateway from lb://`SERVICE` to the specific location like http://localhost:`port`
* In rentable-service's APIClient change `@FeignClient(name = "USER-SERVICE")` to `@FeignClient(url= http://localhost:8081, value = "USER-SERVICE")`
* In contract-service userAPIClient change `@FeignClient(name = "USER-SERVICE")` to `@FeignClient(url= http://localhost:8081, value = "USER-SERVICE")`
* And in RentableAPIClient change `@FeignClient(name = "RENTABLE-SERVICE")` to `@FeignClient(url= http://localhost:8080, value = "RENTABLE-SERVICE")`