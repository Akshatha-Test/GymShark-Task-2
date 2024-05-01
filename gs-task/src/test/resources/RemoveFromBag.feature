Feature: Remove product from the bag

Background: Adding product to a bag
	Given Add product to bag

  Scenario: Remove a product from bag
    Given Product exists in the bag
    When I remove a product
    Then bag is empty
    
    #@Manual
    #Scenario: Removing multiple products from bag
    #Given Products exists in the bag
    #When I remove more than one product
    #Then bag is empty