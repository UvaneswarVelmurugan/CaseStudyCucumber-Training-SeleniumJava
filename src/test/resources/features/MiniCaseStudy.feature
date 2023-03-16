Feature: MiniCaseStudy

Scenario: Login into App
Given User is on Launch Page
When User Login
Then Should display Home Page

Scenario: Add items to cart
When Add an item to cart
 | cat | item |
 | Monitors | Apple monitor 24 |
 | Monitors | ASUS Full HD |
Then Items must be added to cart

Scenario: Delete an Item
When List of Items should be available in cart
Then Delete an item from Cart

Scenario: Place Order
When Items should be available in Cart
Then Purchase Items

