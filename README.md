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

## Task board

The current tasks in progress and tasks for future work live here https://trello.com/b/0PdeVtSD/kalah-board

# Guide for Integrators

TBC - need to link to generated swagger docs
