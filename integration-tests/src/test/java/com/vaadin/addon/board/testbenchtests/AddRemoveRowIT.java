package com.vaadin.addon.board.testbenchtests;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import com.vaadin.addon.board.testUI.AddRemoveRowUI;
import com.vaadin.testbench.By;
import com.vaadin.testbench.elements.ButtonElement;

public class AddRemoveRowIT extends AbstractParallelTest {

    @Override
    protected Class<?> getUIClass() {
        return AddRemoveRowUI.class;
    }

    @Test
    public void basicLayout_removeComponentFromRow_removedComponentsNotShown() {
        WebElement board =getDriver().findElement(By.tagName("vaadin-board"));
        ButtonElement btn1 = $(ButtonElement.class).caption("Button 1").first();
        ButtonElement btn2 = $(ButtonElement.class).caption("Button 3").first();

        Assert.assertEquals("Btn1 should have Y == 0", board.getLocation().getY(), btn1.getLocation().getY());
        Assert.assertEquals("Btn2 should have Y == 0", board.getLocation().getY(), btn2.getLocation().getY());

    }

}
