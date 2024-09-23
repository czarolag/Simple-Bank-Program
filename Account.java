import java.io.*;

public class Account implements Serializable {

    protected String name;
    protected double balance;

    public Account(String accName, double initBalance){
        balance = initBalance;
        name = accName;
    }

    public Account() {

    }

    public String getName(){
        return name;
    }

    public double getBalance(){
        return balance;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setBalance(double balance){
        this.balance = balance;
    }

}