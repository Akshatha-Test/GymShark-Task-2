package stepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import pageobjects.BagPage;
import pageobjects.ProductDisplayPage;
import stepdefs.hooks.Hooks;

import java.util.List;
import stepdefs.hooks.Hooks;

import static org.assertj.core.api.Assertions.assertThat;
import static utils.SeleniumCommands.getCommands;

public class ProductStepDefs {

  private final WebDriver driver;
  private Long productId;

  public ProductStepDefs(){
    this.driver = Hooks.getDriver();
  }

  @Given("the user is on a product page")
  public void theUserIsOnAProductPage() {
    driver.get("https://uk.gymshark.com/products/gymshark-speed-t-shirt-black-aw23");
    
    productId = 39654522814667L;
    new ProductDisplayPage().closeCookieBanner();
  }
  
  @When("adding the product to the Bag")
  public void addingTheProductToTheBag() throws Exception {
    ProductDisplayPage productDisplayPage = new ProductDisplayPage();
    productDisplayPage.selectSmallSize();
    getCommands().defaultWait();
    //Thread.sleep(2000);
    productDisplayPage.selectAddToBag();
  }

  @Then("the product should appear in the Bag")
  public void theProductShouldAppearInTheBag() throws InterruptedException {
    BagPage bagPage = new BagPage();
    List<Long> variantIds = bagPage.getVariantIdsInBag();
    assertThat(variantIds).as("Expected product is in Bag").contains(productId);
  }
}
