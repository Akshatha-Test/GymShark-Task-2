package stepdefs;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import stepdefs.hooks.Hooks;
import pageobjects.BagPage;
import pageobjects.ProductDisplayPage;

public class BagStepDefs {
	
	private WebDriver driver = null;
	  private Long productId;

	  public BagStepDefs(){
	    this.driver = Hooks.getDriver();
	  }
	  
	@Given("Add product to bag")
	public void add_product_to_bag() {
		driver.get("https://uk.gymshark.com/products/gymshark-speed-t-shirt-black-aw23");
	    productId = 39654522814667L;
	    new ProductDisplayPage().closeCookieBanner();
	 }

	@Given("Product exists in the bag")
	public void product_exists_in_the_bag() {
		ProductDisplayPage productDisplayPage = new ProductDisplayPage();
	    productDisplayPage.selectSmallSize();
	    productDisplayPage.selectAddToBag();
	    
	    BagPage bagPage = new BagPage();
	    List<Long> variantIds = bagPage.getVariantIdsInBag();
	    assertThat(variantIds).as("Expected product is in Bag").contains(productId);
	}

	@When("I remove a product")
	public void i_remove_a_product() {
		BagPage bagPage = new BagPage();
		bagPage.selectRemoveIcon();   
	}

	@Then("bag is empty")
	public void bag_is_empty() {
		BagPage bagPage = new BagPage();
		bagPage.emptyBag();
		String actualText  = "Your bag is empty";
		Assert.assertEquals(actualText,"Your bag is empty" );
		
	}
}
