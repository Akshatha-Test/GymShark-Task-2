package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import utils.SeleniumCommands;

import java.util.List;
import java.util.stream.Collectors;

import static utils.SeleniumCommands.getCommands;
import static utils.StringUtils.extractVariantIDFromString;

public class BagPage {

  private static final By BAG_PAGE = By.cssSelector("[data-locator-id='miniBag-component']");
  private static final By BAG_ITEMS = By.cssSelector("[data-locator-id^='miniBag-miniBagItem']");
  public static final String GS_LOCATOR_ATTRIBUTE = "data-locator-id";
  private static final By REMOVE_ICON = By.cssSelector(".product-card_remove-button__IPz1b");
  private static final By EMPTY_BAG = By.xpath("//*[text() = 'Your bag is empty']");
  
  public BagPage() {
    getCommands().waitForAndGetVisibleElementLocated(BAG_PAGE);
  }

  public List<Long> getVariantIdsInBag() {
    return getBagItems().stream()
      .map(this::getVariantId)
      .collect(Collectors.toList());
  }

  private List<WebElement> getBagItems() {
    return getCommands().waitForAndGetAllVisibleElementsLocated(BAG_ITEMS);
  }

  private long getVariantId(WebElement bagItem) {
    return extractVariantIDFromString(getCommands().getAttributeFromElement(bagItem, GS_LOCATOR_ATTRIBUTE));
  }

  public BagPage selectRemoveIcon() {
	    getCommands().waitForAndClickOnElementLocated(REMOVE_ICON);
	    return this;
  }
  
  public BagPage emptyBag() {
	    getCommands().waitForAndGetAllVisibleElementsLocated(EMPTY_BAG);
	  
	    return this;
  }
  
  /*public BagPage emptyBag() {
	    getCommands().waitForAndGetAllVisibleElementsLocated(EMPTY_BAG);
	    return this;
}*/
}
