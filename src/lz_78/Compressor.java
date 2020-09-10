/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lz_78;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

/**
 *
 * @author mohamed
 */
public class Compressor {

    private Map<Integer, String> dictionary;
    private String data;

    public Compressor(String d) {
        this.data = d;
        this.dictionary = new HashMap<Integer, String>();
    }

    int searchInDictionary(String value) {
        int index = -1;
        for (int key : dictionary.keySet()) {
            if (value.equals(dictionary.get(key))) {
                index = key;
                break;
            }
        }
        return index;
    }
//mohamedd

    public Vector<Tag> compress() {
        Vector<Tag> tages = new Vector<Tag>();
        //System.out.println("-----------> "+this.data);
        String c = "";
        int counter = 0; //for dictionary record
        int index = 0; // for tag record
        for (int i = 0; i < this.data.length(); i++) {
            c += this.data.charAt(i); // for new character to be compressed
            //System.out.println(c);
            // if the char found
            //System.out.println(searchInDictionary(c));
            if (searchInDictionary(c) > -1) {
                // c+=this.data.charAt(i+1);
                for (Integer key : dictionary.keySet()) {
                    if (dictionary.get(key).equals(c)) {
                        index = key;
                    }
                    if (i == this.data.length() - 1) {
                        Tag tag = new Tag(index, data.charAt(i)); // 0 stands for null character
                        tages.add(tag);
                        this.dictionary.put(counter + 1, c);
                        counter++;
                        index = 0;
                        break;
                    }

                }

                continue;

            } else if (searchInDictionary(c) == -1) { // it is not find the character for the first time 
                // tag for the first time should be 0
                Tag myTag = new Tag(index, data.charAt(i));
                tages.add(myTag);

                // dictionary of compressed data
                //stream += c; // data that compressed 
                // String s = "" + c;
                this.dictionary.put(counter + 1, c);
                counter++;
                c = "";
                index = 0;
                // System.out.println(stream);

            }
        }
        //****************************************************************
        return tages;
    }

}
