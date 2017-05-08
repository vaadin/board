package com.vaadin.addon.board.testbenchtests;

import java.util.List;

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
        ButtonElement btn3 = $(ButtonElement.class).caption("Button 3").first();

        List<WebElement> children = board.findElements(By.xpath("//vaadin-board/vaadin-board-row/*"));
        Assert.assertEquals("Board should have 2 children", 2, children.size());

    }

}
