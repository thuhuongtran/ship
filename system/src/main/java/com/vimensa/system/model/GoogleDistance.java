package com.vimensa.system.model;

import java.util.List;

public class GoogleDistance {
    public List<String> destination_addresses;
    public List<String> origin_addresses;
    public List<Row> rows;

    public class Row {
        public List<Element> elements;

    }


    public class Element {
        public General duration;
        public General distance;
        public String status;

    }

    public class General {
        public String text;
        public int value;


        public String toString() {
            return "duration{" + "text=" + text + ", value=" + value + '}';
        }


    }
}
