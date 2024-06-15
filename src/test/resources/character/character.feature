Feature: Character

  Scenario Outline: Search a character by his id
    Given The user wants to search a characters by id in the Rick and Morty API
    When The user sends a request to search characters by id "<id>"
    Then The user should get the character with id "<id>"

    Examples:
      | id |
      | 1  |

  Scenario Outline: Check the endpoint content
    Given The user wants to search a characters by id in the Rick and Morty API
    When The user sends a request to search characters by id "<id>"
    Then The user should get the character with id "<id>"
    Then The list contain every information about the character

    Examples:
      | id |
      | 1 |