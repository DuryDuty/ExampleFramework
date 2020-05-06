Feature: Verify functionality of API


  Scenario Outline: Verify adding place to API
    Given Add place Payload with "<name>", "<language>", "<address>"
    When user calls "AddPlaceAPI" with "POST" request
    Then the response returns "200"
    And "status" in response body is "OK"
    And "scope" in response body is "APP"

    Examples:
    | name    | language    | address     |
    | noodle  | EN          | 1 noodle st |
    | cakes   | EN          | 12 cake st  |
