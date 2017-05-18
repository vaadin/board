package com.vaadin.addon.board.testbenchtests;

import org.junit.Assert;
import org.junit.Test;

import com.vaadin.addon.board.testUI.Dash27BasicComponents;

/**
 *
 */
public class Dash27BasicComponentsFlashUITest extends AbstractParallelTest {
    @Override
    protected Class<?> getUIClass() {
        return Dash27BasicComponents.FlashUI.class;
    }

    @Test
    public void test001()
        throws Exception {
        Assert.fail();
    }
}
