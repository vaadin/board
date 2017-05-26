package com.vaadin.addon.board.testUI;

import static com.vaadin.addon.board.testUI.UIFunctions.testLayout;

import java.util.stream.Stream;

import com.vaadin.board.Board;
import com.vaadin.data.TreeData;
import com.vaadin.data.provider.TreeDataProvider;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Tree;

/**
 *
 */
public class Dash27TreeUI extends AbstractTestUI {
  @Override
  protected void init(VaadinRequest request) {
//    setContent(
//        testLayout().apply(
//            Stream.of(
//                nextElement(),
//                nextElement(),
//                nextElement())
//        ));

    Board board = new Board();
    board.setSizeFull();
    board.addRow(nextElement());
    setContent(board);

  }

  private Tree<String> nextElement() {
    Tree<String> tree = new Tree<>();
    tree.setSizeFull();
    TreeData<String> treeData = new TreeData<>();

// Couple of childless root items
    treeData.addItem(null,"Mercury");
    treeData.addItem(null,"Venus");

// Items with hierarchy
    treeData.addItem(null,"Earth");
    treeData.addItem("Earth","The Moon");

    TreeDataProvider inMemoryDataProvider = new TreeDataProvider<>(treeData);
    tree.setDataProvider(inMemoryDataProvider);
    tree.expand("Earth"); // Expand programmatically
    return tree;
  }
}
