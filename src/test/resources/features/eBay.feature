Feature: eBay shopping cart
  Test adding and deleting from shopping cart

  Scenario: Add and remove item from eBay shopping cart
    Given A shopper searches for "Books"
    Then the shopper is shown a list of books, and clicks first item
    And the shopper adds the first item to their cart
    Then the item is displayed in their cart
    And the shopper deletes the item from their cart
    Then the cart is now empty
