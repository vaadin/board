package com.vaadin.addon.board.testbenchtests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.vaadin.addon.board.testUI.ExampleFooUI;
import com.vaadin.testbench.elements.ButtonElement;
import com.vaadin.testbench.elements.LabelElement;

public class ExampleFooUITest extends AbstractParallelTest {

    @Before
    public void setup(){
        openURL();
    }


    @Test
    public void test1() {
        ButtonElement btn1 = $(ButtonElement.class).get(0);
        btn1.click();
        LabelElement label = $(LabelElement.class).get(0);

        Assert.assertEquals("ExampleFoo",label.getText());
    }

    @Override
    protected Class<?> getUIClass() {
        return ExampleFooUI.class;
    }
}
