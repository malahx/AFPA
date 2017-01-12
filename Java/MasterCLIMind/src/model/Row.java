/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author gwenole
 */
public class Row {

    private final char first, second, third, fourth;

    public Row(char first, char second, char third, char fourth) {
        this.first = first;
        this.second = second;
        this.third = third;
        this.fourth = fourth;
    }

    public char getFirst() {
        return first;
    }

    public char getSecond() {
        return second;
    }

    public char getThird() {
        return third;
    }

    public char getFourth() {
        return fourth;
    }

}
