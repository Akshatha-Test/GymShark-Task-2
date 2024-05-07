Feature: Remove product from the bag

Background: Adding product to a bag
@test
  Scenario: Remove a product from bag
  Given Add product to bag
    Given Product exists in the bag
    When I remove a product
    Then bag is empty