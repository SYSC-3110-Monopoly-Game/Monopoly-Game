package Model;

import java.awt.Color;

public class RailRoadSquare extends PropertySquare {

    public RailRoadSquare(String name, int number, int buy, int rent, Color color) {
        super(name, number, buy, rent, color);
    }

    @Override
    public String toXML() {
        StringBuffer string = new StringBuffer();
        string.append("<Square type=\"RailRoad\">\n");
        StringBuffer tempName = new StringBuffer(this.getName());
        for (int i = 0; i < tempName.length(); i++) {
            if (tempName.charAt(i) == '&') {
                tempName.replace(i, i + 1, "&amp;");
            }
        }
        string.append("<Name>" + tempName.toString() + "</Name>\n");
        string.append("<Number>" + this.getNumber() + "</Number>\n");
        string.append("<Price>" + this.getPrice() + "</Price>\n");
        string.append("<RentPrice>" + this.getRentFee() + "</RentPrice>\n");
        String colorS = Integer.toString(this.getColor().getRGB());
        string.append("<Color>" + colorS + "</Color>\n");
        // call back: Color c = new Color(Integer.parseInt(colorS));
        if (this.getOwner() != null) {
            string.append("<Owner>" + this.getOwner().getName() + "</Owner>\n");
        } else {
            string.append("<Owner></Owner>\n");
        }
        string.append("</Square>\n");

        return string.toString();
    }
}


