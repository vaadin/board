package junit.com.vaadin.board.declarative;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.function.Function;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.junit.Test;

/**
 *
 */
public class Declarative001Test {

  Function<String, Elements> convertToElements = (input) -> Jsoup.parseBodyFragment(input).body().children();

  Function<Elements, Boolean> validate = (input) -> {

//<vaadin-board> ..
//  n <vaadin-board-row> ..
//    0..4 elements with board-cols="3" item-number="2" item-size="3/4"

    if (input.size() != 1) return false;

    input.forEach((Element e) -> {
      ((e.nodeName().equals("<vaadin-board>"))
          ? e.childNodes()
          : Collections.<Node>emptyList())
          .stream()
          .map(node -> (node.nodeName().equals("<vaadin-row>"))
              ? true
              : false);
      //TODO next step
    });


    return null;

  };


  @Test
  public void test001() throws Exception {
    final Class<Declarative001Test> aClass = Declarative001Test.class;

    final InputStream inputStream = aClass.getResourceAsStream(aClass.getSimpleName() + ".xml");
    final BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
    String lines = br
        .lines()
        .reduce((s1, s2) -> s1 + s2 + System.lineSeparator())
        .orElse("");

    System.out.println(lines);

    final Elements elements = convertToElements.apply(lines);
    System.out.println("elements = " + elements);


  }
}
