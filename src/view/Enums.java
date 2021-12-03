package view;

public enum Enums {
    BUY("Buy"),
    SELL("Sell"),
    ROLL_DICE("Roll Dice"),
    NEXT_TURN("Next Turn"),
    BUILD_ON_PROPERTY("Build on property"),
    SELL_HOUSES_HOTELS("Sell Houses/Hotels"),
    HOTEL("Hotel"),
    HOUSE("House"),
    DOUBLES(""), NO_DOUBLES(""), BANKRUPT(""), WINNER(""), BUILD(""), SELLH("");


    private final String text;

    /**
     * @param text
     */
    Enums(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
