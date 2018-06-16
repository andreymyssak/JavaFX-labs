package objects;

import javafx.beans.property.SimpleStringProperty;

public class Coordinate {
    private final SimpleStringProperty value1;
    private final SimpleStringProperty value2;
    private final SimpleStringProperty value3;
    private final SimpleStringProperty value4;
    private final SimpleStringProperty value5;

    public Coordinate(String value1, String value2, String value3, String value4, String value5) {
        this.value1 = new SimpleStringProperty(value1);
        this.value2 = new SimpleStringProperty(value2);
        this.value3 = new SimpleStringProperty(value3);
        this.value4 = new SimpleStringProperty(value4);
        this.value5 = new SimpleStringProperty(value5);
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

    public String getValue3() {
        return value3.get();
    }

    public SimpleStringProperty value3Property() {
        return value3;
    }

    public void setValue3(String value3) {
        this.value3.set(value3);
    }

    public String getValue4() {
        return value4.get();
    }

    public SimpleStringProperty value4Property() {
        return value4;
    }

    public void setValue4(String value4) {
        this.value4.set(value4);
    }

    public String getValue5() {
        return value5.get();
    }

    public SimpleStringProperty value5Property() {
        return value5;
    }

    public void setValue5(String value5) {
        this.value5.set(value5);
    }
}
