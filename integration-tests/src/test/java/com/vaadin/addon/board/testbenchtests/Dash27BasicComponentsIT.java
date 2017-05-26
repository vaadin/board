package com.vaadin.addon.board.testbenchtests;

import static com.vaadin.addon.board.testUI.UIFunctions.ID_PREFIX;
import static com.vaadin.addon.board.testbenchtests.TestFunctions.genericAssertHeight;
import static com.vaadin.addon.board.testbenchtests.TestFunctions.genericAssertWidth;

import java.util.function.Supplier;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebElement;
import com.vaadin.addon.board.testUI.AbstractTestUI;
import com.vaadin.addon.board.testUI.Dash27BasicComponents;
import com.vaadin.addon.frp.Pair;
import com.vaadin.testbench.elements.AbstractComponentElement;
import com.vaadin.testbench.elements.ButtonElement;
import com.vaadin.testbench.elements.CheckBoxElement;
import com.vaadin.testbench.elements.CheckBoxGroupElement;
import com.vaadin.testbench.elements.ColorPickerElement;
import com.vaadin.testbench.elements.ComboBoxElement;
import com.vaadin.testbench.elements.DateFieldElement;
import com.vaadin.testbench.elements.FlashElement;
import com.vaadin.testbench.elements.GridElement;
import com.vaadin.testbench.elements.LabelElement;
import com.vaadin.testbench.elements.LinkElement;
import com.vaadin.testbench.elements.PanelElement;
import com.vaadin.testbench.elements.PasswordFieldElement;
import com.vaadin.testbench.elements.ProgressBarElement;
import com.vaadin.testbench.elements.RadioButtonGroupElement;
import com.vaadin.testbench.elements.SliderElement;
import com.vaadin.testbench.elements.TabSheetElement;
import com.vaadin.testbench.elements.TwinColSelectElement;
import com.vaadin.testbench.elements.VideoElement;

/**
 *
 */
public class Dash27BasicComponentsIT {

  static Stream<
      Pair<
          Class<? extends AbstractComponentElement>,
          Class<? extends AbstractTestUI>>> testCombos() {
    return Stream.of(
        new Pair<>(ButtonElement.class, Dash27BasicComponents.ButtonUI.class),
        new Pair<>(CheckBoxGroupElement.class, Dash27BasicComponents.CheckBoxGroupUI.class),
        new Pair<>(CheckBoxElement.class, Dash27BasicComponents.CheckBoxUI.class),
        new Pair<>(ColorPickerElement.class, Dash27BasicComponents.ColorPickerUI.class),
        new Pair<>(ComboBoxElement.class, Dash27BasicComponents.ComboBoxUI.class),
        new Pair<>(DateFieldElement.class, Dash27BasicComponents.DateFieldUI.class),
        new Pair<>(FlashElement.class, Dash27BasicComponents.FlashUI.class),
        new Pair<>(GridElement.class, Dash27BasicComponents.GridUI.class),
        new Pair<>(LabelElement.class, Dash27BasicComponents.LabelUI.class),
        new Pair<>(LinkElement.class, Dash27BasicComponents.LinkUI.class),
        new Pair<>(PanelElement.class, Dash27BasicComponents.PanelUI.class),
        new Pair<>(PasswordFieldElement.class, Dash27BasicComponents.PasswordFieldUI.class),
        new Pair<>(ProgressBarElement.class, Dash27BasicComponents.ProgressBarUI.class),
        new Pair<>(RadioButtonGroupElement.class, Dash27BasicComponents.RadioButtonGroupUI.class),
        new Pair<>(SliderElement.class, Dash27BasicComponents.SliderUI.class),
        new Pair<>(TabSheetElement.class, Dash27BasicComponents.TabSheetUI.class),
//        new Pair<>(TreeElement.class, Dash27BasicComponents.TreeUI.class), // Tree is a Composite
        new Pair<>(TwinColSelectElement.class, Dash27BasicComponents.TwinColSelectUI.class),
        new Pair<>(VideoElement.class, Dash27BasicComponents.VideoUI.class)
    );
  }


  @RunWith(value = Parameterized.class)
  public static class GenericTest extends AbstractParallelTest {

    @Parameterized.Parameter
    public Pair<
        Class<? extends AbstractComponentElement>,
        Class<? extends AbstractTestUI>> nextTestCombo;

    @Parameterized.Parameters(name = "{index}: nextTestCombo - {0}")
    public static Object[] data() {
      return testCombos().toArray();
    }

    public Supplier<WebElement> middleElementSupplier() {
      return () -> $(middleElementClass())
          .id(ID_PREFIX + 1);
    }

    @Test
    public void testGenericWidth() throws Exception {
      genericAssertWidth.accept(buttonSwitchSupplier, middleElementSupplier());
      compareScreen(nextTestCombo.getT2().getSimpleName());
    }

    @Test
    public void testGenericheight() throws Exception {
      genericAssertHeight.accept(buttonSwitchSupplier, middleElementSupplier());
    }

    @Override
    protected Class<? extends AbstractTestUI> getUIClass() {
      return nextTestCombo.getT2();
    }

    public Class<? extends AbstractComponentElement> middleElementClass() {
      return nextTestCombo.getT1();
    }

  }

}
