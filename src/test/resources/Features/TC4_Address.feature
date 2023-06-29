@Address
Feature: Address Module API Automation

  Scenario Outline: Verify add User Address to the application through API
    Given User add headers and bearer authorization for accessing Address endpoints
    When User add request body for new address "<first_name>","<last_name>","<mobile>","<apartment>","<country>","<zipcode>","<address>" and "<address_type>"
    And User send "POST" request for add User Address endpoint
    Then User verify the status code is 200
    Then User verify the add User Address response message matches "Address added successfully"

    Examples: 
      | first_name | last_name | mobile     | apartment | country | zipcode | address | address_type |
      | Suriya     | Narayanan | 9790383744 | Vikravan  |      13 |  626117 | Chennai | Home         |

  Scenario Outline: Verify Update User Address to the application through API
    Given User add headers and bearer authorization for accessing Update Address endpoints
    When User add request body for Update address "<first_name>","<last_name>","<mobile>","<apartment>","<country>","<zipcode>","<address>" and "<address_type>"
    And User send "PUT" request for Update User Address endpoint
    Then User verify the status code is 200
    Then User verify the Update User Address response message matches "Address updated successfully"

    Examples: 
      | first_name | last_name | mobile     | apartment | country | zipcode | address | address_type |
      | Suriya     | Narayanan | 9790383744 | Vikravan  |      13 |  626117 | Chennai | Home         |

  Scenario: Verify Get User Address to the application through API
    Given User add headers and bearer authorization for accessing Get User Address endpoints
    When User send "GET" request for User Address endpoint
    Then User verify the status code is 200
    Then User verify the Get User Address response message matches "OK"

  Scenario: Verify Get User Delete to the application through API
    Given User add headers and bearer authorization for accessing Get User Delete endpoints
    When User add request body for User Delete Address
    When User send "DELETE" request for Get User Delete endpoint
    Then User verify the status code is 200
    Then User verify the Get User delete Address response message matches "Address deleted successfully"
