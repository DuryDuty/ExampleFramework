Feature: Verify functionality of API


  Scenario Outline: Add place to API
    Given payload generated with "<name>", "<language>", "<address>"
    When user calls "addPlace" with "POST" request
    Then the response returns "200"
    And "status" in response body is "OK"
    And place_id is stored in a hashmap

    Examples:
    | name    | language    | address     |
    | noodle2 | EN          | 1 noodle st |
    | cakes   | EN          | 12 cake st  |

  Scenario Outline: Get places from API
    Given GET request has "<name>" place_id parameter
    When user calls "getPlace" with "GET" request
    Then the response returns "200"
    And "name" in response body is "<name>"

    Examples:
      | name    |
      | noodle2 |
      | cakes   |
