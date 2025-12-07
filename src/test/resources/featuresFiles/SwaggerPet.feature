Feature: Pet API using swagger.io

  Background:
    Given I have the pet endpoint

  Scenario: Create a pet and fetch it
    And I have the pet payload from file "testData.json"
    When I send POST request to create pet
    Then the response status code should be 200
    And I store the pet id from the response
    When I send GET request to fetch that pet
    Then the response status code should be 200
    And the pet name in response should be "Rex"

  # Example PUT scenario (using same or another JSON file)
  Scenario: Update a pet
    And I have the pet payload from file "testData.json"
    When I send PUT request to update pet using file "testData.json"
    Then the response status code should be 200