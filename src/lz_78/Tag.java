/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lz_78;

/**
 *
 * @author mohamed
 */
public class Tag {

    private int index;
    private char nextChar;

    public Tag(int i, char n) {

        this.index = i;
        this.nextChar = n;
    }

    public char getChar() {
        return this.nextChar;
    }

    public int getIndex() {
        return this.index;
    }

}
