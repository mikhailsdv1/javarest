# Vauedhire Backend API

Below are the supported endpoints: 
1. http://valuedhirebackend:11003/jobseeker/profile
2. http://valuedhirebackend:11003/jobseeker/preferences
3. http://valuedhirebackend:11003/employer/job-post
4. http://valuedhirebackend:11003/employer/profile

Internally we use a separate ES index for each entity: profile, preferences, job-post, company-profile.  All endpoints support CRUD operations and follow standard REST convention. Most of the APIs accept data in JSON format in the body and ID in the path. UserId is automatically loaded from the security context and is appened to the saved data in meta section during create operation 

Below are examples of request and response for CRUD operations

# jobseeker/profile endpoint


## Create a job seeker profile
POST https://vhwus2devapi-03.azurewebsites.net/jobseeker/profile

Request:
```
{
   "personal":{
      "name":"John Doe",
      "headline":"Programmer",
      "phase":"actively searching | open to exploring | not open to considering",
      "total-yoe":10,
      "total-people-mgmt-yoe":3,
      "picture":"http://site/a.png",
      "email":"john@example.com",
      "phone":"+1 (912) 555-4321",
      "website":"http://johndoe.com",
      "summary":"Lorem ipsum"
   }  
}
```

Response:
```
{
    "seqNo": 8,
    "primaryTerm": 1,
    "id": "v6ZS7HUB44kszUw3wcsD"
}
```



## Load a job seeker profile
GET https://vhwus2devapi-03.azurewebsites.net/jobseeker/profile

Response:
```
{
    "_seq_no": 9,
    "_index": "jobseeker-profile",
    "_type": "_doc",
    "_source": {
        "meta": [
            {
                "date-created-utc": "2020-11-21T19:41:57.513Z",
                "user-id": "anonymousUser",
                "status": "created"
            }
        ],
        "personal": {
            "phase": "actively searching | open to exploring | not open to considering",
            "summary": "Lorem ipsum",
            "website": "http://johndoe.com",
            "total-yoe": 10,
            "phone": "+1 (912) 555-4321",
            "name": "John Doe!!!",
            "total-people-mgmt-yoe": 3,
            "headline": "Programmer",
            "picture": "http://site/a.png",
            "email": "john@example.com"
        }
    },
    "_id": "v6ZS7HUB44kszUw3wcsD",
    "sort": [
        1605987717513
    ],
    "_score": null,
    "_primary_term": 1
}
```

## Update a job seeker profile
POST https://vhwus2devapi-03.azurewebsites.net/jobseeker/profile/v6ZS7HUB44kszUw3wcsD?seq_no=8&primary_term=1

Request
```
{
   "personal":{
      "name":"John Doe!!!",
      "headline":"Programmer",
      "phase":"actively searching | open to exploring | not open to considering",
      "total-yoe":10,
      "total-people-mgmt-yoe":3,
      "picture":"http://site/a.png",
      "email":"john@example.com",
      "phone":"+1 (912) 555-4321",
      "website":"http://johndoe.com",
      "summary":"Lorem ipsum"
   }  
}
```

Response:
```
{
    "seqNo": 9,
    "primaryTerm": 1,
    "id": "v6ZS7HUB44kszUw3wcsD"
}
```

## Delete a job seeker profile
DELETE https://vhwus2devapi-03.azurewebsites.net/jobseeker/profile

Response:
```
{
   "shardInfo":{
      "total":2,
      "successful":1,
      "failures":[
         
      ],
      "failed":0,
      "fragment":false
   },
   "shardId":{
      "index":{
         "name":"jobseeker-profile",
         "uuid":"_na_",
         "fragment":false
      },
      "id":-1,
      "indexName":"jobseeker-profile",
      "fragment":true
   },
   "id":"1214",
   "type":"_doc",
   "version":5,
   "seqNo":44,
   "primaryTerm":1,
   "result":"DELETED",
   "index":"jobseeker-profile",
   "fragment":false
}
```


# employee/profile endpoint
Requests and responses for the employee/profile endpoint are similar to the jobseeker/profile enpoint, see above
