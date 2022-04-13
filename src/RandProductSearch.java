import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class RandProductSearch extends JFrame {

    JPanel mainPnl;
    JPanel searchPnl;
    JPanel displayPnl;

    JTextField searchBar;

    JLabel searchLbl;

    JTextArea displayTA;
    JScrollPane scroller;

    JButton searchBtn;
    JButton pickBtn;

    File f;
    StringBuilder sb;

    public RandProductSearch() {
        mainPnl = new JPanel();
        mainPnl.setLayout(new GridLayout(2, 1));

        createSearchPnl();
        mainPnl.add(searchPnl);

        createDisplayPnl();
        mainPnl.add(displayPnl);

        add(mainPnl);
        setSize(700, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void createSearchPnl() {
        searchPnl = new JPanel();

        pickBtn = new JButton("Pick File");
        searchPnl.add(pickBtn);
        pickBtn.addActionListener((ActionEvent ae) -> {
            JFileChooser chooser = new JFileChooser();

            if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                f = chooser.getSelectedFile();
                try
                {
                    BufferedReader br=new BufferedReader(new FileReader(f));
                    sb = new StringBuilder();
                    String line="";
                    line=br.readLine();
                    while(line!=null)
                    {
                        sb.append(line);
                        sb.append("\n");
                        line=br.readLine();
                    }
                    //displayTA.append(sb.toString());
                }
                catch(Exception e)
                {
                    System.err.println(e.toString());
                }
            }
            else {

            }
        });

        searchLbl = new JLabel("Search Bar");
        searchPnl.add(searchLbl);

        searchBar = new JTextField(10);
        searchPnl.add(searchBar);

        searchBtn = new JButton("Search");
        searchPnl.add(searchBtn);

        searchBtn.addActionListener((ActionEvent ae) -> {

            String sr = searchBar.getText();
            sb.delete(0, sb.length());

            try
            {
                BufferedReader br = new BufferedReader(new FileReader(f));
                String line = "";
                line = br.readLine();
                while(line!=null)
                {
                    String h[] = line.split(", ");
                    int len = h.length;

                    for(int i=0;i<len;i++)
                    {
                        if(h[i].equalsIgnoreCase(sr))
                        {
                            sb.append(line);
                            sb.append("\n");
                        }
                    }
                    line = br.readLine();
                }
                displayTA.append(sb.toString());
            }
            catch(Exception e)
            {
                System.err.println(e.toString());
            }



        });
    }

    public void createDisplayPnl() {
        displayPnl = new JPanel();
        displayTA = new JTextArea(10,50);
        displayTA.setEditable(false);
        scroller = new JScrollPane(displayTA);
        displayPnl.setFont(new Font("Comic Sans MS", Font.PLAIN, 10));
        displayPnl.add(scroller);
    }
}
