Feature: Create new user using api Reqres POST

  Scenario Outline: Validate post user api works correctly
    Given I hit the url of create new user api endpoint
    When I pass the url in the request api user
    And I pass the request body of new user name <UserName>
    And I pass the request body of new user job <UserJob>
    Then I receive the response code create user as 201
    And I verify the response contains the user name <UserName>
    And I verify the response contains the user job <UserJob>
    Examples:
    | UserName | UserJob |
    | morpheus | leader  |