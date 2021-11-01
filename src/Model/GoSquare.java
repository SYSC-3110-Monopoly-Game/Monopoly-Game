package Model;


public class GoSquare extends Square{
    private final int addMoney;

    public GoSquare(String name, int number, int money) {
        super(name, number);
        this.addMoney = money;
    }

    private int getAddMoney() {
        return addMoney;
    }

    @Override
    public void landOn(Player p) {
        int money = this.getAddMoney();
        System.out.println("!!!You will get some money!!!");
        System.out.println(p.getName() + " got $" + money);
    }
}
