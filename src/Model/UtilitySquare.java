package Model;

import java.awt.Color;
public class UtilitySquare extends PropertySquare{

    public UtilitySquare(String name, int number, int buy, int rent, Color color) {
        super(name, number, buy, rent, color);
    }

    @Override
    public String toXML() {
        StringBuffer string = new StringBuffer();
        string.append("<Square type=\"Utility\">");
        string.append("<Name>"+this.getName()+"</Name>");
        string.append("<Number>"+this.getNumber()+"</Number>");
        string.append("<Price>"+this.getPrice()+"</Price>");
        string.append("<RentPrice>"+this.getRentFee()+"</RentPrice>");
        String colorS = Integer.toString(this.getColor().getRGB());
        string.append("<Color>"+colorS+"</Color>");
        // call back: Color c = new Color(Integer.parseInt(colorS));
        if(this.getOwner() != null) {
            string.append("<Owner>" + this.getOwner().getName() + "</Owner>");
        } else {
            string.append("<Owner> </Owner>");
        }
        string.append("</Square>");

        return string.toString();
    }
}
