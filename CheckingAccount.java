import java.util.ArrayList;
import java.io.*;

public class CheckingAccount extends Account implements Serializable {

    private double totalServiceCharge = 0.0;
    private int num = 0;
    String message;
    int transCount = 0;
    double transaction;
    public ArrayList<Transaction> transList = new ArrayList<Transaction>();

    public CheckingAccount(String name, double balance) {
        super(name, balance);
    }

    public CheckingAccount() {

    }

    public void setTotalServiceCharge(double currentServiceCharge) {

        Transaction tr = new Transaction(transCount, 3, currentServiceCharge);
        addTrans(tr);
        double addServiceCharge = 0;
        message = "";

        if (num < 1 && balance < 500) {
            // should happen just one time, so :
            message += "\nService charge below 500---Charge $5 \n";
            addServiceCharge += 5;
            num++;
            tr = new Transaction(transCount, 3, 5.0);
            addTrans(tr);
        }
        if (balance < 50) {
            message += "Warning:Balance below $50 \n";
        }
        if (balance < 0) {
            message += "Service charge: below 0---Charge $10 \n";
            addServiceCharge += 10;
            tr = new Transaction(transCount, 3, 10.0);
            addTrans(tr);
        }
        totalServiceCharge = totalServiceCharge + currentServiceCharge + addServiceCharge;
    }

    public void setBalance(int tranC, double transAmt) {
        if (tranC == 1) {
            balance = balance - transAmt;
        }
        if (tranC == 2) {
            balance = balance + transAmt;
        }
    }

    public double finalBalance() {
        return getBalance() - getTotalServiceCharge();
    }

    public void addTrans(Transaction t) {
        transList.add(t);
        transCount++;
    }
    public double getTotalServiceCharge() {
        return totalServiceCharge;
    }

    public Transaction getTrans(int i) {
        return transList.get(i);
    }
    public int getTransCount() {
        return transCount;
    }

}