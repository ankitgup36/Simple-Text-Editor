import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.print.PrinterException;

public class simpleTextEditor implements ActionListener{
    JFrame jFrame ;
    JTextArea t1 ;
    JMenuBar bar ;
    JMenu file , edit , close ;
    JMenuItem open , save , New , print ;
    JMenuItem cut , copy , paste ;
    JMenuItem clse ;
    simpleTextEditor(){

        jFrame = new JFrame("simple text editor");
        jFrame.setVisible(true);
        jFrame.setBounds(10,10,800,1000);
        t1 = new JTextArea("Welcome to the Simple Text Editor");
        t1.setVisible(true);
        jFrame.add(t1);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        bar = new JMenuBar();
        jFrame.setJMenuBar(bar);

        // creating menues

        file = new JMenu("File");
        edit = new JMenu("Edit");
        close = new JMenu("Close");
        bar.add(file); bar.add(edit) ; bar.add(close);

        // file menu - submenus

        open = new JMenuItem("Open");
        save = new JMenuItem("Save");
        print = new JMenuItem("Print");
        New = new JMenuItem("New");
        file.add(New) ; file.add(open) ; file.add(print) ; file.add(save);

        // edit menu - submenues

        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");
        edit.add(cut) ; edit.add(copy) ; edit.add(paste) ;

        // close menu - submenues

        clse = new JMenuItem("Close Editor");
        close.add(clse);

        // add action listeners

        open.addActionListener(this);
        clse.addActionListener(this);
        paste.addActionListener(this);
        cut.addActionListener(this);
        copy.addActionListener(this);
        print.addActionListener(this);
        New.addActionListener(this);
        save.addActionListener(this);
    }

    public static void main(String[] args) {
        simpleTextEditor n = new simpleTextEditor() ;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();

        // cut functionality
        if(s.equals("Cut")){
            t1.cut();
        }

        // copy functionality
        else if(s.equals("Copy")){
            t1.copy();
        }

        // paste functionality
        else if(s.equals("Paste")){
                t1.paste();
        }

        // print functionality
        else if (s.equals("Print")){
            try{
                t1.print();
            }
            catch (PrinterException x){
                throw new RuntimeException(x) ;
            }
        }

        // new functionality
        else if(s.equals("New")){
            t1.setText(" ");
        }

        // close functionaly
        else if(s.equals("Close Editor")){
            jFrame.setVisible(false);
        }

        // open functionality
        else if(s.equals("Open")){
            JFileChooser j = new JFileChooser();
            int ans = j.showOpenDialog(null);
            if(ans==JFileChooser.APPROVE_OPTION){
                File file = new File(j.getSelectedFile().getAbsolutePath());
                String s1 = "" , s2 ="" ;
                try {
                    BufferedReader b1 = new BufferedReader(new FileReader(file));
                    while((s1= b1.readLine())!=null){
                        s2+= s1+"\n" ;
                    }
                    t1.setText(s2);
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }

        // save functionality
        else if(s.equals("Save")){
            // initialising a file reader
                JFileChooser j = new JFileChooser();
                int ans =  j.showSaveDialog(null);
                if(ans==JFileChooser.APPROVE_OPTION){
                    File file = new File(j.getSelectedFile().getAbsolutePath());
                    try {
                        BufferedWriter writer = new BufferedWriter(new FileWriter(file , false));
                        writer.write(t1.getText());
                        writer.flush();
                        writer.close();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        }
    }
