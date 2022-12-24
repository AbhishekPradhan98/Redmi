#Author: your.email@your.domain.com




Feature: Title of your feature
  I want to use this template for my feature file

    Scenario Outline: Title of your scenario outline
    Given i open browser with url "https://www.facebook.com/"
    Then i should see login page
    When i enter my user is as "<email>"
    And i enter my password as "<pwd>"
    
    Examples: 
      |      email                |  pwd   | 
      | abhishekgaur138@gmail.com |   1234 | 


    Scenario Outline: facebook
    Given i open browser with url "https://www.facebook.com/"
    Then i should see login page
    When i click create account