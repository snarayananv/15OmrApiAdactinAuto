@City
Feature: City Module API Automation

  Scenario: Verify User Get CityList through API
    Given User add headers for CityList
    When User add request body for CityList state_id
    When User send "POST" request for CityList endpoint
    Then User verify the status code is 200
    Then User verify the CityList response message matches "Yercaud" and save CityId
