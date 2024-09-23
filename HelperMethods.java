import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

public class HelperMethods {

    public static String filename = "C:\\student\\acct.dat";
    public static boolean saved = true;

    public static CheckingAccount currentAccount;
    public static Vector<CheckingAccount> dataStore = new Vector<>();
    public static int accountIndex;



    /*
    Methods are used for file input/output
     */
    public static void readElements() {
        if (!saved) {
            askSave();
        }

        chooseFile(1);
        try {
            accountIndex = 0;
            FileInputStream fis = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(fis);
            dataStore = (Vector<CheckingAccount>)in.readObject();
            in.close();
            saved = true;

            currentAccount = dataStore.get(accountIndex);
        } catch (IOException e) {
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }
    }

    public static void writeElements() {

        chooseFile(2);

        try {
            FileOutputStream fos = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(fos);

            out.writeObject(dataStore);
            saved = true;
            out.close();

        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void chooseFile(int ioOption) {
        int status, confirm;

        String message = "Would you like to use the current default file: \n" + filename;
        confirm = JOptionPane.showConfirmDialog(null, message);
        if (confirm == JOptionPane.YES_OPTION)
            return;

        JFileChooser chooser = new JFileChooser();
        if (ioOption == 1)
            status = chooser.showOpenDialog(null);
        else
            status = chooser.showSaveDialog(null);
        if (status == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            filename = file.getPath();
        }
    }

    public static void askSave() {
        int confirm;

        String message = "The data in the application is not saved.\n" +
                "Would you like to save before exiting the application?";

        confirm = JOptionPane.showConfirmDialog(null, message);
        if (confirm == JOptionPane.YES_OPTION)
            writeElements();
        else {
            return;
        }
    }
     /*
     End of methods used for input/output
      */


    /*
    Methods used for New Account Creation
     */
    public static void addNewAcc() {
        saved = false;

        String name = getName();
        double initialBalance = getInitialBalance();

        currentAccount = new CheckingAccount(name, initialBalance);
        dataStore.addElement(currentAccount);
        accountIndex = dataStore.size()-1;

        String message = String.format("New Account Added for %s", name);
        Main.ta.setText(message);
    }

    public static double getInitialBalance() {
        String initBalanceStr = JOptionPane.showInputDialog("Enter the Initial Balance: ");
        double initBalance = Double.parseDouble(initBalanceStr);

        return initBalance;
    }

    public static String getName() {
        String name = JOptionPane.showInputDialog("Enter the Name: ");
        return name;
    }
     /*
     End of methods used for New Account Creation
      */


    /*
    Methods used for listing text such as transactions
     */
    public static void listTransactions() {
        String message;

        try {
            message = String.format("List All Transactions\nName: %s\n", currentAccount.getName());

            String balance = currentAccount.getBalance() < 0 ? "($" +
                    Math.abs(currentAccount.getBalance()) + ")" : "$" + currentAccount.getBalance();
            message += String.format("Balance: %s\nTotal Service Charge: %.2f\n\n", balance, currentAccount.getTotalServiceCharge());
            message += String.format("%-3s\t%-10s\t\t%-10s\n", "ID", "Type", "Amount");

            for (int i = 0; i < currentAccount.getTransCount(); i++) {
                message += currentAccount.getTrans(i).toString() + "\n";
            }
            Main.ta.setText(message);

        } catch (NullPointerException e) {
            message = "You must select an account first.";
            JOptionPane text = new JOptionPane(message, JOptionPane.ERROR_MESSAGE);
            JDialog dialog = text.createDialog("Error");
            dialog.setAlwaysOnTop(true);
            dialog.setVisible(true);
        }
    }

    public static void listChecks() {
        String message;

        try {
            message = String.format("List All Checks\nName: %s\n", currentAccount.getName());

            String balance = currentAccount.getBalance() < 0 ? "($" +
                    Math.abs(currentAccount.getBalance()) + ")" : "$" + currentAccount.getBalance();
            message += String.format("Balance: %s\nTotal Service Charge: %.2f\n\n", balance, currentAccount.getTotalServiceCharge());
            message += String.format("%-3s\t%-10s\t\t%-10s\n", "ID", "Check", "Amount");

            for (int i = 0; i < currentAccount.getTransCount(); i++) {
                if (currentAccount.getTrans(i).getTransId().equals("Check")) {
                    message += currentAccount.getTrans(i).toStringCheck() + "\n";
                }
            }

            Main.ta.setText(message);

        } catch (NullPointerException e) {
            message = "You must select an account first.";
            JOptionPane text = new JOptionPane(message, JOptionPane.ERROR_MESSAGE);
            JDialog dialog = text.createDialog("Error");
            dialog.setAlwaysOnTop(true);
            dialog.setVisible(true);
        }
    }

    public static void listDeposits() {
        String message;

        try {
            message = String.format("List All Deposits\nName: %s\n", currentAccount.getName());

            String balance = currentAccount.getBalance() < 0 ? "($" +
                    Math.abs(currentAccount.getBalance()) + ")" : "$" + currentAccount.getBalance();
            message += String.format("Balance: %s\nTotal Service Charge: %.2f\n\n", balance, currentAccount.getTotalServiceCharge());
            message += String.format("%-3s\t%-10s\t\t%-10s\t%-10s\n", "ID", "Cash", "Check", "Amount");

            for (int i = 0; i < currentAccount.getTransCount(); i++) {
                if (currentAccount.getTrans(i).getTransId().equals("Deposit")) {
                    message += currentAccount.getTrans(i).toStringDeposit() + "\n";
                }
            }

            Main.ta.setText(message);

        } catch (NullPointerException e) {
            message = "You must select an account first.";
            JOptionPane text = new JOptionPane(message, JOptionPane.ERROR_MESSAGE);
            JDialog dialog = text.createDialog("Error");
            dialog.setAlwaysOnTop(true);
            dialog.setVisible(true);
        }
    }

    public static void listServChg() {
        String message;

        try {
            message = String.format("List All Service Charges\nName: %s\n", currentAccount.getName());

            String balance = currentAccount.getBalance() < 0 ? "($" +
                    Math.abs(currentAccount.getBalance()) + ")" : "$" + currentAccount.getBalance();
            message += String.format("Balance: %s\nTotal Service Charge: %.2f\n\n", balance, currentAccount.getTotalServiceCharge());
            message += String.format("%-3s\t%-10s\n", "ID", "Amount");

            for (int i = 0; i < currentAccount.getTransCount(); i++) {
                if (currentAccount.getTrans(i).getTransId().equals("Svc. Charg.")) {
                    message += String.format("%-3d\t%-10.2f\n",
                            currentAccount.getTrans(i).getTransNumber(), currentAccount.getTrans(i).getTransAmt());
                }
            }

            Main.ta.setText(message);

        } catch (NullPointerException e) {
            message = "You must select an account first.";
            JOptionPane text = new JOptionPane(message, JOptionPane.ERROR_MESSAGE);
            JDialog dialog = text.createDialog("Error");
            dialog.setAlwaysOnTop(true);
            dialog.setVisible(true);
        }
    }

    public static void listAccounts() {
        String message;

        try {
            message = String.format("List of All Accounts\n\n");
            for (int i = 0; i < dataStore.size(); i++) {
                CheckingAccount account = dataStore.get(i);

                message += String.format("Name: %s\n", account.getName());

                String balance = account.getBalance() < 0 ? "($" +
                        Math.abs(account.getBalance()) + ")" : "$" + account.getBalance();
                message += String.format("Balance: %s\n", balance);
                message += String.format("Total Service Charge: $%.2f\n\n", account.getTotalServiceCharge());
            }

            Main.ta.setText(message);

        } catch (NullPointerException e) {
            message = "No account found in memory.";
            JOptionPane text = new JOptionPane(message, JOptionPane.ERROR_MESSAGE);
            JDialog dialog = text.createDialog("Error");
            dialog.setAlwaysOnTop(true);
            dialog.setVisible(true);
        }
    }
     /*
     End of methods used for listing text such as transactions
      */


    /*
    Methods used for entering a transaction
     */
    public static void enterTrans() {
        try {
            // validate if user is signed in an account
            currentAccount.getName();
        } catch (NullPointerException e) {
            String message = "You must select an account first.";
            JOptionPane text = new JOptionPane(message, JOptionPane.ERROR_MESSAGE);
            JDialog dialog = text.createDialog("Error");
            dialog.setAlwaysOnTop(true);
            dialog.setVisible(true);
            return;
        }

        DecimalFormat fmt = new DecimalFormat("$0.00");
        saved = false;

        // get transaction code
        int transactionCode = getTransCode();

        if (transactionCode != 0) {
            if (transactionCode == 1) {
                String input = JOptionPane.showInputDialog("Enter the check number:");
                int checkNumber = Integer.parseInt(input);

                double amount = checkAmount();
                String transaction = "Check";
                double currentServiceCharge = 0.15;
                Check trans = new Check(currentAccount.transCount, 1, amount, checkNumber);
                currentAccount.addTrans(trans);

                currentAccount.setBalance(transactionCode, amount);
                currentAccount.setTotalServiceCharge(currentServiceCharge);

                // update the data stored at the current account
                dataStore.set(accountIndex, currentAccount);

                String currentBalance = currentAccount.getBalance() < 0 ? "($" +
                        Math.abs(currentAccount.getBalance()) + ")" : "$" + currentAccount.getBalance();

                JOptionPane.showMessageDialog(null, currentAccount.getName() + "'s account.\n"
                        + "Transaction: " + transaction + " #" + checkNumber + " in amount of "
                        + fmt.format(amount) + "\n"
                        + "Current Balance: " + currentBalance + "\n"
                        + "Service charge: " + transaction + " --- charge "
                        + fmt.format(currentServiceCharge) + "\n" + currentAccount.message
                        + "Total Service Charge: " + fmt.format(currentAccount.getTotalServiceCharge()) + "\n");
            }

            if (transactionCode == 2) {

                double cash, check;
                JTextField cashField = new JTextField();
                JTextField checkField = new JTextField();
                double currentServiceCharge = 0.10;
                String transaction = "Deposit";

                JPanel panel = new JPanel();
                panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
                panel.add(new JLabel("Cash"));
                panel.add(cashField);
                panel.add(new JLabel("Check"));
                panel.add(checkField);

                int result = JOptionPane.showConfirmDialog(null, panel, "Enter Deposit", JOptionPane.OK_CANCEL_OPTION);

                if (result == JOptionPane.OK_OPTION) {

                    String input1 = cashField.getText();
                    if (input1 == null || input1.equals("")) {
                        cash = 0;
                    } else {
                        cash = Double.parseDouble(input1);
                    }

                    String input2 = checkField.getText();
                    if (input2 == null || input2.equals("")) {
                        check = 0;
                    } else {
                        check = Double.parseDouble(input2);
                    }

                    double totalDeposit = cash + check;

                    Deposit newDeposit = new Deposit(currentAccount.transCount, 2, check, cash);
                    currentAccount.addTrans(newDeposit);

                    currentAccount.setBalance(transactionCode, totalDeposit);
                    currentAccount.setTotalServiceCharge(currentServiceCharge);

                    // update the data stored
                    dataStore.set(accountIndex, currentAccount);

                    String currentBalance = currentAccount.getBalance() < 0 ? "($" + Math.abs(currentAccount.getBalance()) + ")" : "$"
                            + currentAccount.getBalance();

                    JOptionPane.showMessageDialog(null, currentAccount.getName() + "'s Account.\n"
                            + "Transaction: " + transaction + " in amount of " + fmt.format(totalDeposit) + "\n"
                            + "Current Balance: " + currentBalance + "\n"
                            + "Service charge: " + transaction + " --- charge " + fmt.format(currentServiceCharge)
                            + "\n" + currentAccount.message
                            + "Total Service Charge: " + fmt.format(currentAccount.getTotalServiceCharge()) + "\n");
                }
            }

        } else {
            String transaction = "End";
            String currentBalance = currentAccount.getBalance() < 0 ? "($" +
                    Math.abs(currentAccount.getBalance()) + ")" : "$" + currentAccount.getBalance();

            String finalBalance = currentAccount.finalBalance() < 0 ? "($" + Math.abs(currentAccount.finalBalance()) + ")" : "$" +
                    currentAccount.finalBalance();

            JOptionPane.showMessageDialog(null, " Transaction: " + transaction
                    + "\n Current Balance: " + currentBalance
                    + "\n Total Service Charge: " + fmt.format(currentAccount.getTotalServiceCharge())
                    + "\n Final Balance: " + finalBalance
                    + "\n");
        }
    }

    public static int getTransCode() {
        String m = "Enter Transaction Code: \n";
        String tranCount = JOptionPane.showInputDialog(m);
        int transactionCode = Integer.parseInt(tranCount);

        return transactionCode;
    }

    public static double checkAmount() {
        String depositAmt = JOptionPane.showInputDialog("Enter check amount: ");
        double checkAmt = Double.parseDouble(depositAmt);
        return checkAmt;
    }
     /*
     End of methods used for entering a transaction
      */


    /*
    Method used for finding and opening an account
     */
    public static void findAccount() {
        String name = JOptionPane.showInputDialog("Enter the account name: ");
        boolean accountFound = false;

        for (int i = 0; i < dataStore.size(); i++) {
            CheckingAccount account = dataStore.get(i);

            if (name.equalsIgnoreCase(account.getName())) {
                currentAccount = account;
                accountIndex = i;
                accountFound = true;
                String message = String.format("Account found for %s", name);
                Main.ta.setText(message);
                break;
            }
        }

        if (!accountFound) {
            String message = String.format("Account not found for %s", name);
            Main.ta.setText(message);
        }
    }

}
