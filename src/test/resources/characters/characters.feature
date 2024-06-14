Feature: Characters

    Scenario Outline: Search characters by name
        Given The user wants to search a characters by name in the Rick and Morty API
        When The user sends a request to search characters by name "<name>"
        Then The user receives a list of characters that contain "<name>"

        Examples:
            | name   |
            | Rick   |
            | Morty  |
            | Summer |


    Scenario Outline: Check the endpoint content
        Given The user wants to search a characters by name in the Rick and Morty API
        When The user sends a request to search characters by name "<name>"
        Then The user receives a list of characters that contain "<name>"
        Then The list contain every information about the Characters

        Examples:
            | name   |
            | Rick   |
            | Morty  |
            | Summer |

    
    Scenario: Search by an incomplete name
        Given The user wants to search a characters by name in the Rick and Morty API
        When The user sends a request to search characters by name "Jer"
        Then The user receives a list of characters that contain "Jerry"

    
    Scenario: Search by an invalid name
        Given The user wants to search a characters by name in the Rick and Morty API
        When The user sends a request to search characters by name "invalidName"
        Then The user receives a list of recommended characters