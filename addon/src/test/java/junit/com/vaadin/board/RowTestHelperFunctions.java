package junit.com.vaadin.board;

import java.util.function.Function;
import java.util.stream.IntStream;

import com.vaadin.board.Board;
import com.vaadin.board.Row;
import com.vaadin.ui.Button;

/**
 *
 */
public interface RowTestHelperFunctions {

    static Function<Integer, Row> createButtonRow(){
        return (amount) -> {
            final Row row = new Board().addRow();
            IntStream
                .range(0, amount)
                .forEach(i -> row.addComponent(new Button(), 1));
            return row;
        };
    }


}
