package objects;

import javafx.beans.property.SimpleSetProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * @author - Andrey Myssak
 * @since - 09.10.2017
 */
public class Coordinate {
    private final SimpleStringProperty value1;
    private final SimpleStringProperty value2;

    public Coordinate(String value1, String value2) {
        this.value1 = new SimpleStringProperty(value1);
        this.value2 = new SimpleStringProperty(value2);
    }

    public String getValue1() {
        return value1.get();
    }

    public SimpleStringProperty value1Property() {
        return value1;
    }

    public void setValue1(String value1) {
        this.value1.set(value1);
    }

    public String getValue2() {
        return value2.get();
    }

    public SimpleStringProperty value2Property() {
        return value2;
    }

    public void setValue2(String value2) {
        this.value2.set(value2);
    }
}
