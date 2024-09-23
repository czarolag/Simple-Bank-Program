public class Check extends Transaction {

    private int checkNumber;

    public Check(int tCount, int tId, double tAmt, int checkNumber) {
        super(tCount, tId, tAmt);
        this.checkNumber = checkNumber;
    }

    public int getCheckNumber() {
        return checkNumber;
    }

    public void setCheckNumber(int checkNumber) {
        this.checkNumber = checkNumber;
    }

    @Override
    public String toStringCheck() {
        return String.format("%-3d\t%-10d\t\t$%-10.2f",
                getTransNumber(), getCheckNumber(),getTransAmt());
    }
}