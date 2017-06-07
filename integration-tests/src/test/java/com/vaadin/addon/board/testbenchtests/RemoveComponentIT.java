package com.vaadin.addon.board.testbenchtests;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import com.vaadin.addon.board.testUI.RemoveComponentUI;
import com.vaadin.board.elements.BoardElement;

public class RemoveComponentIT extends AbstractParallelTest {

    @Override
    protected Class<?> getUIClass() {
        return RemoveComponentUI.class;
    }

    @Test
    public void basicLayout_removeComponentFromRow_removedComponentsNotShown() {
        BoardElement board =$(BoardElement.class).first();

        List<WebElement> children = board.getRow(0).getDirectChildren();
        Assert.assertEquals("Board should have 2 children", 2, children.size());

    }

}
