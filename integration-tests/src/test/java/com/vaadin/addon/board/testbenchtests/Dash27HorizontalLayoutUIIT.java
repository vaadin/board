package com.vaadin.addon.board.testbenchtests;

import static com.vaadin.addon.board.testUI.UIFunctions.ID_PREFIX;
import static com.vaadin.addon.board.testUI.UIFunctions.SWITCH;
import static com.vaadin.addon.board.testbenchtests.TestFunctions.genericAssertHeight;
import static com.vaadin.addon.board.testbenchtests.TestFunctions.genericAssertWidth;

import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import com.vaadin.addon.board.testUI.Dash27LayoutComponents;
import com.vaadin.testbench.elements.ButtonElement;

/**
 *
 */
public class Dash27HorizontalLayoutUIIT extends AbstractParallelTest {
  @Override
  protected Class<?> getUIClass() {
    return Dash27LayoutComponents.HorizontalLayoutUI.class;
  }


  Supplier<WebElement> buttonMiddleSupplier = () -> $(ButtonElement.class)
      .id(ID_PREFIX + 2);

  @Test
  public void testGenericWidth()
      throws Exception {
    genericAssertWidth.accept(buttonSwitchSupplier, buttonMiddleSupplier);
  }

  @Test
  public void testGenericHeight()
      throws Exception {
    genericAssertHeight.accept(buttonSwitchSupplier, buttonMiddleSupplier);
  }



}
