/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lz_78;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.MouseInputListener;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author mohamed
 */
public class Gui {

    private static JFrame myFrame;
    private static JButton browse;
    private static JButton compress;
    private static JButton decompress;
    private static JTextArea containerOfData;
    private static JLabel status;
    private static JFileChooser choose;

    public Gui() {
        //define all attribute 
        myFrame = new JFrame("LZ_78");
        browse = new JButton("Browse");
        compress = new JButton("Compress");
        decompress = new JButton("Decompress");
        containerOfData = new JTextArea();
        status = new JLabel("Operation Performed");
        Font buttonFont = new Font("Times new roman", Font.BOLD, 18);
        myFrame.setSize(800, 900);
        Font containerFont = new Font("Times new roman", Font.BOLD, 16);
        // set locations of each Item
        containerOfData.setBounds(40, 40, 500, 500);
        containerOfData.setFont(containerFont);
        browse.setBounds(538, 40, 90, 60);
        status.setBounds(40, -10, 500, 80);
        compress.setBounds(40, 538, 120, 80);
        decompress.setBounds(402, 538, 140, 80);
        compress.setFont(buttonFont);
        decompress.setFont(buttonFont);
        browse.setFont(buttonFont);
        compress.setForeground(Color.red);
        compress.setBackground(Color.orange);
        browse.setForeground(Color.pink);
        browse.setBackground(Color.BLACK);
        decompress.setForeground(Color.BLUE);
        decompress.setBackground(Color.GREEN);

        //***************
        containerOfData.setLineWrap(true);                               // ينزل على سطر جديد في حال كان عدد الأحرف المدخلة أكبر من عدد الأحرف التي يستطيع السطر إستيعابها textArea هنا جعلنا النص الذي ندخله في كائن الـ
        containerOfData.setWrapStyleWord(true);  // هنا جعلنا الكلمة تظهر على سطر جديد في حال كانت لا تسع في السطر

        //define for browse button
        choose = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("text files", "txt");
        choose.setFileFilter(filter);
        // adding Item in jfram
        myFrame.setLayout(/*new FlowLayout(FlowLayout.LEFT)*/null);
        myFrame.add(status);
        myFrame.add(containerOfData);
        myFrame.add(compress);
        myFrame.add(decompress);
        myFrame.add(browse);

        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // terminate program when closing jframe
        myFrame.setVisible(true); //to show jframe

    }

    public void BehindTheScene() {
        // compress Button
        compress.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                Compressor compressor;
                compressor = new Compressor(containerOfData.getText());
                Vector<Tag> tages = new Vector<Tag>();
                tages = compressor.compress();
                //tages.toString();
                //containerOfData.setText(tages.toString());
                containerOfData.setText("");
                for (int iter = 0; iter < tages.size(); iter++) {
                    containerOfData.append("<" + tages.elementAt(iter).getIndex() + "," + tages.elementAt(iter).getChar() + ">\n");
                }

                status.setText("You have compressed data and get these Tages");

            }
        });

        // Decompress Button
        decompress.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

                Vector<Tag> tage = new Vector<Tag>();
                String tages = containerOfData.getText();
                String compressedTags[];
                compressedTags = tages.split("\n");
                for (int i = 0; i < compressedTags.length; i++) {
                    String ch = compressedTags[i].substring(compressedTags[i].indexOf(","), compressedTags[i].lastIndexOf(">"));
                    char c = ch.charAt(ch.length() - 1);
                    int index = Integer.parseInt((compressedTags[i].substring(1, compressedTags[i].indexOf(","))));
                    //System.out.println(index);
                    Tag t = new Tag(index, c);
                    tage.add(t);
                }
                Decompressor decompressor = new Decompressor(tage);
                containerOfData.setText(decompressor.Decompress());

                status.setText("You have decompressed Tags and get these data");

            }
        });

        // Browse Button 
        browse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                int returnedValue = choose.showOpenDialog(myFrame);
                if (returnedValue == JFileChooser.APPROVE_OPTION) {
                    // File path
                    File selectedFile = choose.getSelectedFile();
                    String filepath = selectedFile.getPath();
                    try {
                        // read data in the file line by line
                        BufferedReader br = new BufferedReader(new FileReader(filepath));
                        String line = "";
                        String text = "";

                        while ((line = br.readLine()) != null) {
                            text += line + "\n";
                        }

                        containerOfData.setText(text);
                        br.close();
                    } catch (IOException ioe) {
         
                        JOptionPane.showMessageDialog(null, ioe.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }

                status.setText("You should Choose text file , get these data");

            }
        });

    }

}
