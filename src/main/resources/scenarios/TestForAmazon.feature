Feature: Test for books Java in Amazon

  Background: Starting page amazon
    Given Open amazon "https://www.amazon.com/"

  Scenario: Open website amazon and choose books and enter Java
    When Click on dropdown card
    And Choose books
    And Enter "Java" in searching field
    And Press button to search
    And Take all books param from the received page
    And Find particular book and click "Head First Java: A Brain-Friendly Guide"
    Then Check that the book is on the list "Head First Java: A Brain-Friendly Guide" by "Kathy Sierra"