import java.io.*;
public class Transaction implements Serializable {

    // current value of the transCount
    private int transNumber;

    private int transId;
    private double transAmt;

    public Transaction(int number, int id, double amount) {
        transNumber = number;
        transId = id;
        transAmt = amount;
    }

    public int getTransNumber() {
        return transNumber;
    }

    public String getTransId() {

        String type = "";

        if (transId == 1) {
            type = "Check";
        }
        if (transId == 2) {
            type = "Deposit";
        }
        if (transId == 3) {
            type = "Svc. Charg.";
        }
        return type;
    }

    public double getTransAmt() {
        return transAmt;
    }

    // For easier printing
    @Override
    public String toString() {
        return String.format("%-3d\t%-10s\t\t$%-10.2f", getTransNumber(), getTransId(), getTransAmt());
    }

    public String toStringCheck() {
        return "";
    }

    public String toStringDeposit() {
        return "";
    }
}
