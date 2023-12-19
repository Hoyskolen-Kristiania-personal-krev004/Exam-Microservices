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
## Spring Cloud Eureka
I have chosen to utilize Spring Cloud Eureka instead of HashiCorp Consul for Service Discovery and Registry. I have elected to use Eureka, as it is easier to set up and integrate in Spring projects, and I find my current experience with Consul to be too insufficient to take advantage of the depth that it offers compared to other Service Discovery Tools.

