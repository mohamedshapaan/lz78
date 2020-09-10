package lz_78;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

/**
 *
 * @author mohamed
 */
public class Decompressor {

    private Vector<Tag> tages;  //to be Decompressed  
    private Map<Integer, String> dictionary; //to fill again

    public Decompressor(Vector<Tag> myTages) {
        this.tages = new Vector<Tag>();
        this.tages = myTages;
        this.dictionary = new HashMap<Integer, String>();
        this.dictionary.clear();
        dictionary.put(0, "0"); // '0'stands for null character

    }

    public String Decompress() {
        String stream = "";
        int counter = 0;
        for (int i = 0; i < tages.size(); i++) {
            String deData = "";

            char tagChar = this.tages.get(i).getChar();
            int tagIndex = this.tages.get(i).getIndex();

            if (tagIndex == 0) {
                deData += tagChar;
                this.dictionary.put(counter + 1, deData);
                stream += deData;
                counter++;
                //deData="";
            } else if (tagIndex > 0) {

                deData += dictionary.get(tagIndex);
              /*  if (tagChar != '0') {}*/
                    deData += tagChar;
                

                dictionary.put(counter + 1, deData);
                stream += deData;
                counter++;

            }
        }

        return stream;
    }

}
