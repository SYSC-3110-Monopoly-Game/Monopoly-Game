package Model;

public class IncomeTaxSquare extends Square {

    private final int incomeTax;

    public IncomeTaxSquare(String name, int number, int tax) {
        super(name, number);
        this.incomeTax = tax;
    }

    private int getIncomeTax() {
        return incomeTax;
    }

    @Override
    public void landOn(Player p) {
        int tax = this.getIncomeTax();
        System.out.println("!!!You need to pay income tax!!!");

        if (p.getCash() - tax >= 0) {
            System.out.println(p.getName() + " has paid $" + tax);
        }else{
            System.out.println("!!!You lose!!!");
        }
    }
}
