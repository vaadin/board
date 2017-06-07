package com.vaadin.board.elements;

/*
 * #%L
 * Vaadin Spreadsheet Testbench API
 * %%
 * Copyright (C) 2013 - 2016 Vaadin Ltd
 * %%
 * This program is available under Commercial Vaadin Add-On License 3.0
 * (CVALv3).
 * 
 * See the file license.html distributed with this software for more
 * information about licensing.
 * 
 * You should have received a copy of the CVALv3 along with this program.
 * If not, see <http://vaadin.com/license/cval-3>.
 * #L%
 */

import java.util.List;

import org.openqa.selenium.WebElement;

import com.vaadin.testbench.By;
import com.vaadin.testbench.elementsbase.AbstractElement;
import com.vaadin.testbench.elementsbase.ServerClass;

/**
 * This class represents one row inside the Vaadin Board.
 *
 * @author Vaadin Ltd.
 */
@ServerClass("com.vaadin.board.Row")
public class RowElement extends AbstractElement {

    /**
     * Gets direct children from the row. Items from inner rows are not included.
     *
     * @return TestBenchElement direct children from the row.
     */
    public List<WebElement> getDirectChildren(){
        return findElements(By.xpath("*"));
    }
}