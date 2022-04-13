import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class RandProductMaker extends JFrame {

    JPanel mainPnl;
    JPanel topPnl;
    JPanel middlePnl;
    JPanel bottomPnl;

    JLabel nameLbl;
    JLabel descriptionLbl;
    JLabel idLbl;
    JLabel costLbl;
    JLabel addLbl;
    JLabel countLbl;

    JTextField nameTF;
    JTextField descriptionTF;
    JTextField idTF;
    JTextField costTF;

    JTextArea countTA;

    JButton addBtn;
    int count = 0;

    ArrayList<Product> item = new ArrayList<>();

    final static int NAME_SIZE = 35;
    final static int DESCRIPTION_SIZE = 75;
    final static int ID_SIZE = 6;
    final static int COST_SIZE = 10;

    private RandomAccessFile raf;

    public RandProductMaker() {
        mainPnl = new JPanel();
        mainPnl.setLayout(new BorderLayout());

        createTopPnl();
        mainPnl.add(topPnl, BorderLayout.NORTH);

        createMiddlePnl();
        mainPnl.add(middlePnl, BorderLayout.CENTER);

        createBottomPnl();
        mainPnl.add(bottomPnl, BorderLayout.SOUTH);

        add(mainPnl);
        setSize(500, 270);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        try {
            raf = new RandomAccessFile("product2.dat", "rw");

        } catch (FileNotFoundException e) {
            System.out.println("Error: " + e);
            System.exit(0);
        }
    }

    private void writeAddress() throws IOException {
        raf.seek(raf.length());
        FixedLengthStringIO.writeFixedLengthString(
                nameTF.getText(), NAME_SIZE, raf);
        FixedLengthStringIO.writeFixedLengthString(
                descriptionTF.getText(), DESCRIPTION_SIZE, raf);
        FixedLengthStringIO.writeFixedLengthString(
                idTF.getText(), ID_SIZE, raf);
        FixedLengthStringIO.writeFixedLengthString(
                costTF.getText(), COST_SIZE, raf);
        FixedLengthStringIO.writeFixedLengthString("\n", 1, raf);
    }

    private void createTopPnl() {
        topPnl = new JPanel();
        topPnl.setLayout(new GridLayout(6,1));

        nameLbl = new JLabel("Name:");
        nameTF = new JTextField(35);
        topPnl.add(nameLbl);
        topPnl.add(nameTF);

        descriptionLbl = new JLabel("Description:");
        descriptionTF = new JTextField(75);
        topPnl.add(descriptionLbl);
        topPnl.add(descriptionTF);

        idLbl = new JLabel("ID:");
        idTF = new JTextField(6);
        topPnl.add(idLbl);
        topPnl.add(idTF);

        costLbl = new JLabel("Cost:");
        costTF = new JTextField(10);
        topPnl.add(costLbl);
        topPnl.add(costTF);
    }

    private void createMiddlePnl() {
        middlePnl = new JPanel();
        middlePnl.setLayout(new GridLayout(1,1));

        addLbl = new JLabel("Add Button:");
        addBtn = new JButton("Add");
        addBtn.addActionListener((ActionEvent ae) -> {
            //put code here that adds it to a doc
            String name = nameTF.getText().trim();
            String description = descriptionTF.getText().trim();
            String ID =  idTF.getText().trim();
            double cost = Double.parseDouble(costTF.getText().trim());

            item.add(new Product(name, description, ID, cost));

            try {
                writeAddress();
            } catch (IOException e) {
                System.out.println("Error: " + e);
            }

            nameTF.setText("");
            descriptionTF.setText("");
            idTF.setText("");
            costTF.setText("");

            count++;
            countTA.setText("");
            countTA.append(String.valueOf(count));
        });

        middlePnl.add(addBtn);
    }

    private void createBottomPnl() {
        bottomPnl = new JPanel();
        bottomPnl.setLayout(new GridLayout(1,1));

        countLbl = new JLabel("Record Count:");
        countTA = new JTextArea(1,5);
        bottomPnl.add(countLbl);
        bottomPnl.add(countTA);
    }
}
