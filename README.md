# GitHub API README

This repository contains the implementation of a GitHub API that allows API consumers to retrieve information about repositories owned by a user.
The API supports only JSON format.
### API Endpoints can be found with swagger: http://localhost:8080/api/swagger-ui/index.html in case of successfully started application
### API Endpoint also provided below:

#### Endpoint: GET /api/github/users/{username}/repositories
Request
    Method: GET
    Headers:
        Accept: application/json or Accept: */*
    Path Variables: username -> required
Response
  On success:
    Status Code: 200 OK
    Content-Type: application/json

JSON Response Format example:
    
    [
    {
        "repositoryName": "AccountantApplication",
        "ownerUsername": "bohd-kovalenko",
        "branches": [
            {
                "name": "master",
                "lastCommitSha": "6aac55d74c4ca0347b3d9f93c64be396e5b7fb97"
            }
        ]
    },
    {
        "repositoryName": "Binary-Search-Tree",
        "ownerUsername": "bohd-kovalenko",
        "branches": [
            {
                "name": "master",
                "lastCommitSha": "e21985f958aee6d49d3965b61aa1aed5a142a983"
            }
        ]
    }
    ]
    
  On error (wrong username):
    
    {
    "status": 404,
    "message": "No such user registered on GitHub"
    }

  On error (wrong accept header):

    {
    "status": 406,
    "message": "Could not find acceptable representation"
    }

### Running the API:
  You need Java 17 to be installed
    Clone(git clone https://github.com/bohd-kovalenko/task-atipera-reactive) or download GitHub repository and then simply run the executable task-task-reactive-0.0.1-SNAPSHOT.jar 
