# kalah-service

Game service to allow multiple players to play the Mancala (Kalah) board game

# Guide for developers

## Component diagram of service architecture and deployment architecure

The diagram below shows the Component view of the Kalah-service and supporting services. 
 
![Component Diagram](/images/Component_diagram.PNG)
 
The diagram below captures the intended delivery pipeline.

![Delivery Flow](/images/Delivery_flow.PNG)

The steps are as follows

1. Local Testing - Developer should run service and execute feature tests before submitting change;
2. Developer pushes code for review - The change should have sufficient information for the purpose of the change and a link to the original task;
3, 4, 5. Automated pull of source code for review build.  The review build will perform static code analysis and check libraries for valid licenses and vulnerabilities;
6. CI job is executed and a release artifact is pushed to an artifact repository.  The release artifact is automatically versioned.  
   Release notes will be auto generated as part of the release process;
7. The released artifact is pulled from the artifact repository and pushed to Cloud Foundry
8. Feature tests are executed
9. Performance tests are triggered against a NFR environment
 
Note only the items in Green have been implemented the other items are considered not MVP for this task.  However further tasks have been captured for future implementation.

The diagram below shows the service stack

![Stack](/images/service_stack.PNG)

The diagrams below shows the intended flow within the application

### Create Game

![Create Game](/images/Create_game_sequence.PNG)

### Make Move

![Make Move](/images/Make_move_sequence.PNG)

## Executing Service

### Pre requisites

Maven - https://maven.apache.org/download.cgi - Please use latest 3.6.0
Java - Minimum OpenJdk (build 1.8.0_181-8u181

### Local

1. mvn spring-boot:run
2. Start Game - curl -X POST -H "Content-Type:application/json" http://localhost:8080/games
3. Make Move - curl -X PUT -H "Content-Type:application/json" http://localhost:8080/games/{gameid}/pits/{pitid}

Note: Game Id is returned from the call to '/games' and pitid is a value between 0 and 13.

### CF environment

#### Pipeline delivery

To deploy the application there is a delivery pipeline defined at https://app.codeship.com/bbsampleapp.  Service tests are 
executed using https://assertible.com/dashboard#/services/6503b7d4-41ea-4921-bb75-c9bb369ad6ea and https://github.com/bbsampleapp/kalah-test.

#### Direct service route calls (Reduced availability)

1. Push change to repository.  Deployment is automatic.
2. Start Game -  curl -X POST -H "Content-Type:application/json" https://kalah-service.mybluemix.net/games
3. Make Move - curl -X PUT -H "Content-Type:application/json" https://kalah-service.mybluemix.net/games/{gameid}/pits/{pitid}

Note: Game Id is returned from the call to '/games' and pitid is a value between 0 and 13.

#### Service calls routed through gateway-zuul from Internet

1. Push change to repository.  Deployment is automatic.
2. Start Game -  curl -X POST -H "Content-Type:application/json" https://gateway-zuul.mybluemix.net/kalah/v1/games
3. Make Move - curl -X PUT -H "Content-Type:application/json" https://gateway-zuul.mybluemix.net/games/{gameid}/pits/{pitid}

Note: Game Id is returned from the call to '/games' and pitid is a value between 0 and 13.

#### CF CLI

1. cf login -a https://api.ng.bluemix.net -u username -o organisation -s space
2. cf push application from root of kalah-service.  Please ensure mvn clean install has been executed first

## Task board

The current tasks in progress and tasks for future work live here https://trello.com/b/0PdeVtSD/kalah-board

# Guide for Integrators

To view the API please use naviagate to https://kalah-service.mybluemix.net/swagger-ui.html (http://localhost:8080/swagger-ui.html for local environment)
