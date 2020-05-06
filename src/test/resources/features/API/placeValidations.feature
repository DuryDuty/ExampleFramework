Feature: Verify functionality of API


  Scenario: Verify adding place to API
    Given Add place Payload
    When user calls "AddPlaceAPI" with "POST" request
    Then the respose returns "200"
    And "status" in response body is "OK"
    And "scope" in response body is "APP"


