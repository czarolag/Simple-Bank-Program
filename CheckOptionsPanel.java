import javax.swing.*;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.awt.event.*;

public class CheckOptionsPanel extends JFrameL {
    private static final int WIDTH = 300;
    private static final int HEIGHT = 200;


    private JMenu fileMenu, accMenu, transMenu;
    private JMenuItem openItem, saveItem, newAcc, listTrans,
            listChecks, listDeposits, listServChg, findAcc,
            listAcc, enterTrans;

    private JMenuBar bar;

    public CheckOptionsPanel(String title) {

        super(title);
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        MenuListener ml = new MenuListener();

        // File Menu
        fileMenu = new JMenu("File");
        openItem = new JMenuItem("Open File");
        saveItem = new JMenuItem("Save File");

        openItem.addActionListener(ml);
        saveItem.addActionListener(ml);


        // Accounts Menu
        accMenu = new JMenu("Accounts");
        newAcc = new JMenuItem("Add New Account");
        listTrans = new JMenuItem("List All Transactions");
        listChecks = new JMenuItem("List All Checks");
        listDeposits = new JMenuItem("List All Deposits");
        listServChg = new JMenuItem("List All Service Charges");
        findAcc = new JMenuItem("Find An Account");
        listAcc = new JMenuItem("List All Accounts");

        newAcc.addActionListener(ml);
        listTrans.addActionListener(ml);
        listChecks.addActionListener(ml);
        listDeposits.addActionListener(ml);
        listServChg.addActionListener(ml);
        findAcc.addActionListener(ml);
        listAcc.addActionListener(ml);

        // Transactions Menu
        transMenu = new JMenu("Transactions");
        enterTrans = new JMenuItem("Enter Transaction");

        transMenu.addActionListener(ml);
        enterTrans.addActionListener(ml);


        // add items to file menu
        fileMenu.add(openItem);
        fileMenu.add(saveItem);

        // add items to accounts menu
        accMenu.add(newAcc);
        accMenu.add(listTrans);
        accMenu.add(listChecks);
        accMenu.add(listDeposits);
        accMenu.add(listServChg);
        accMenu.add(findAcc);
        accMenu.add(listAcc);

        // add item to transactions menu
        transMenu.add(enterTrans);


        bar = new JMenuBar();
        bar.add(fileMenu);
        bar.add(accMenu);
        bar.add(transMenu);

        // set menu bar
        setJMenuBar(bar);


        // JFrame to center
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private class MenuListener implements ActionListener {

        public void actionPerformed (ActionEvent event) {
            String source = event.getActionCommand();

            if (source.equals("Open File")) {
                HelperMethods.readElements();
            } else if (source.equals("Save File")) {
                HelperMethods.writeElements();
            } else if (source.equals("Add New Account")) {
                HelperMethods.addNewAcc();
            } else if (source.equals("List All Transactions")) {
                HelperMethods.listTransactions();
            } else if (source.equals("List All Checks")) {
                HelperMethods.listChecks();
            } else if (source.equals("List All Deposits")) {
                HelperMethods.listDeposits();
            } else if (source.equals("List All Service Charges")) {
                HelperMethods.listServChg();
            } else if (source.equals("Find An Account")) {
                HelperMethods.findAccount();
            } else if (source.equals("List All Accounts")) {
                HelperMethods.listAccounts();
            } else if (source.equals("Enter Transaction")) {
                HelperMethods.enterTrans();
            }

        }
    }
}

