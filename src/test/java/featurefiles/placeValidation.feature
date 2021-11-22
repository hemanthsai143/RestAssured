Feature: validating place API's

@AddPlace
Scenario Outline: Verify if place is being added successfully through Add place API
Given Add Place payload with "<name>" "<language>" "<address>"
When user calls "addPlaceAPI" with "post" http request
Then the API call got success with status code 200
And "status" in response body is "OK"
And "scope" in response body is "APP"
And verify place_Id created maps to "<name>" using "getPlaceAPI"
 
Examples:
|name           |language  |address|
|Frontline house|French-IN|29, side layout, cohen 09|
#|Epping         |America  |30, side layout, cohen 10|

@DeletePlace
Scenario: Verify if Delete place functionality is working
Given Delete place payload
When user calls "deletePlaceAPI" with "post" http request
Then the API call got success with status code 200
And "status" in response body is "OK"

