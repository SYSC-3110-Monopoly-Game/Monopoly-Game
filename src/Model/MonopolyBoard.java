package Model;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MonopolyBoard {


    private static final int SIZE = 34;
    private final Square[] squares;
    public static JailSquare jail;
    private final int buyPrice = 60, rentPrice = 70;

    Color[] set = {Color.CYAN, Color.PINK, Color.ORANGE, Color.RED, Color.YELLOW, Color.GREEN, Color.GRAY, Color.BLUE, Color.BLACK, Color.WHITE};
    public static ArrayList<Color> colors = new ArrayList<>();


    /*public MonopolyBoard() {
        squares = new Square[SIZE];
        makeSquares();
        colors.addAll(Arrays.asList(set));
    }*/

    public MonopolyBoard(String path) throws ParserConfigurationException, IOException, SAXException {
        squares = new Square[SIZE];
        makeSquaresFromXML(path);
        jail = (JailSquare) getSquareAt(8);
        colors.addAll(Arrays.asList(set));
    }

    public Square[] getSquares() {
        return squares;
    }

    public Square getSquareAt(int index) {
        return squares[index];
    }

    /**
     * Populates the board with Squares.
     */
    private void makeSquares() {
        int i = 0;
        int goMoney = 50;
        int incomeTax = 100;
        int jailFee = 100;

        squares[i] = new GoSquare("GO", i++, goMoney);
        squares[i] = new PropertySquare("Mediterranean Avenue", i++, buyPrice, rentPrice, Color.GRAY);
        squares[i] = new PropertySquare("Baltic Avenue", i++, buyPrice, rentPrice, Color.GRAY);
        squares[i] = new TaxSquare("Income Tax", i++, incomeTax);

        squares[i] = new RailRoadSquare("Reading RailRoad", i++, buyPrice, rentPrice, Color.BLACK);

        squares[i] = new PropertySquare("Oriental Avenue", i++, buyPrice, rentPrice, Color.CYAN);
        squares[i] = new PropertySquare("Vermont Avenue", i++, buyPrice, rentPrice, Color.CYAN);
        squares[i] = new PropertySquare("Connecticut Avenue", i++, buyPrice, rentPrice, Color.CYAN);

        squares[i] = new JailSquare("Jail", i, jailFee);

        jail = (JailSquare) squares[i];
        i++;
        squares[i] = new PropertySquare("St. Charles Place", i++, buyPrice, rentPrice, Color.PINK);
        squares[i] = new UtilitySquare("Electric Company", i++, buyPrice, rentPrice, Color.WHITE);
        squares[i] = new PropertySquare("States Avenue", i++, buyPrice, rentPrice, Color.PINK);
        squares[i] = new PropertySquare("Virginia Avenue", i++, buyPrice, rentPrice, Color.PINK);

        squares[i] = new RailRoadSquare("Pennsylvania RailRoad", i++, buyPrice, rentPrice, Color.BLACK);

        squares[i] = new PropertySquare("St. James Place", i++, buyPrice, rentPrice, Color.ORANGE);
        squares[i] = new PropertySquare("Tennessee Avenue", i++, buyPrice, rentPrice, Color.ORANGE);
        squares[i] = new PropertySquare("New York Avenue", i++, buyPrice, rentPrice, Color.ORANGE);

        squares[i] = new FreeParkingSquare("Free Parking", i++);

        squares[i] = new PropertySquare("Kentucky Avenue", i++, buyPrice, rentPrice, Color.RED);
        squares[i] = new PropertySquare("Indiana Avenue", i++, buyPrice, rentPrice, Color.RED);
        squares[i] = new PropertySquare("Illinois Avenue", i++, buyPrice, rentPrice, Color.RED);

        squares[i] = new RailRoadSquare("B.& O. RailRoad", i++, buyPrice, rentPrice, Color.BLACK);

        squares[i] = new PropertySquare("Atlantic Avenue", i++, buyPrice, rentPrice, Color.YELLOW);
        squares[i] = new PropertySquare("Ventnor Avenue", i++, buyPrice, rentPrice, Color.YELLOW);
        squares[i] = new UtilitySquare("Water Works", i++, buyPrice, rentPrice, Color.WHITE);
        squares[i] = new PropertySquare("Marvin Gardens", i++, buyPrice, rentPrice, Color.YELLOW);

        squares[i] = new GoToJailSquare("Go to jail", i);
        GoToJailSquare gojail = (GoToJailSquare) squares[i++];
        gojail.setJail(jail);

        squares[i] = new PropertySquare("Pacific Avenue", i++, buyPrice, rentPrice, Color.GREEN);
        squares[i] = new PropertySquare("North Carolina Avenue", i++, buyPrice, rentPrice, Color.GREEN);
        squares[i] = new PropertySquare("Pennsylvania Avenue", i++, buyPrice, rentPrice, Color.GREEN);

        squares[i] = new RailRoadSquare("Short Line", i++, buyPrice, rentPrice, Color.BLACK);

        squares[i] = new PropertySquare("Park Place", i++, buyPrice, rentPrice, Color.BLUE);
        squares[i] = new TaxSquare("Luxury Tax", i++, incomeTax);;
        squares[i] = new PropertySquare("Boardwalk", i, buyPrice, rentPrice, Color.BLUE);
    }

    /**
     * Returns the square located at the specified distance from the specified square
     */
    public Square getNextSquare(Square location, int distance) {
        int i = location.getNumber() + distance;
        if (i >= squares.length) return squares[i - SIZE];
        else return squares[i];
    }

    /**
     * Returns the Square where all players should be located at the start of the game.
     */
    public Square startingSquare() {
        return squares[0];
    }

    /**
     * export the game board to path
     * @param fileName: file name of the xml file
     */
    public void exportToXML(String fileName) {
        try {
            FileWriter writer = new FileWriter(fileName, true);
            writer.write("<Board>");
            for(Square s: squares){
                writer.write(s.toXML());
            }
            writer.write("</Board>");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * create a map from the given variables
     * @param type: type of the square
     * @param variables: variables contains squares' information
     */
    private void loadToSquare(String type, HashMap variables){
        Square s;
        String name, owner;
        Color c = Color.LIGHT_GRAY;
        int number, price, rentPrice, housePrice, houseAmount, hotelAmount;
        name = (String) variables.get("Name");
        owner = (String) variables.get("Owner");
        number = (int) variables.get("Number");
        price = (int) variables.get("Price");
        rentPrice = (int) variables.get("RentPrice");
        housePrice = (int) variables.get("HousePrice");
        houseAmount = (int) variables.get("HouseAmount");
        hotelAmount = (int) variables.get("HotelAmount");
        if(variables.get("Color") != Color.LIGHT_GRAY){
            c = (Color) variables.get("Color");
        }

        HashMap<String, Integer> map = (HashMap<String, Integer>) variables.get("JailMap");
        if(type.equals("Property")){
            s = new PropertySquare(name, number, price, rentPrice, c, housePrice, houseAmount, hotelAmount, owner);
        } else if (type.equals("RailRoad")) {
            s = new RailRoadSquare(name, number, price, rentPrice, c);
        } else if (type.equals("Utility")) {
            s = new UtilitySquare(name, number, price, rentPrice, c);
        } else if (type.equals("Jail")) {
            s = new JailSquare(name, number, price, map);
        } else if (type.equals("Tax")) {
            s = new TaxSquare(name, number, price);
        } else if (type.equals("Go")) {
            s = new GoSquare(name, number, price);
        } else if (type.equals("GoToJail")) {
            s = new GoToJailSquare(name, number);
        } else if (type.equals("FreeParking")) {
            s = new FreeParkingSquare(name, number);
        } else {
            s = new GoSquare(name, number, price);
        }
        squares[(int) variables.get("Number")] = s;
    }

    /**
     * load the file from path
     * @param path: file name of the xml file
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    private void makeSquaresFromXML(String path) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();

        HashMap<String, Object> square = new HashMap<>(Map.of("Name",  "","Owner", "","Number", 0,
                "Price", 0, "RentPrice", 0,"HousePrice", 0, "HouseAmount", 0,
                "HotelAmount", 0,"Color", Color.LIGHT_GRAY, "JailMap", new HashMap<String, Integer>()));

        boolean[] load = {false,false,false,false,false,false,false,false,false,false};
        final String[] variables = {"Name", "Owner", "Number", "Price", "RentPrice", "HousePrice", "HouseAmount",
                "HotelAmount", "Color", "JailMap"};

        final HashMap<String, Integer> squareType = new HashMap<>(Map.of(
                "Property", 0, "RailRoad", 0, "Utility", 0, "Jail", 0
                , "Tax", 0, "Go", 0, "GoToJail", 0, "FreeParking", 0));

        saxParser.parse(path, new DefaultHandler(){

            @Override
            public void startElement(String uri, String localName, String qName, Attributes attributes){
                if(qName.equals("Square")){
                    squareType.put(attributes.getValue("type"), 1);
                } else {
                    for(int i=0; i< variables.length; i++){
                        if(qName.equals(variables[i])){
                            load[i] = true;
                        }
                    }
                }
            }

            @Override
            public void endElement(String uri, String localName, String qName){
                if(qName.equals("Square")){
                    for(String name: squareType.keySet()){
                        if(squareType.get(name) == 1){
                            loadToSquare(name, square);
                            squareType.put(name, 0);
                            square.put(variables[0], "");
                            square.put(variables[1], "");
                        }
                    }
                } else {
                    for(int i=0; i< variables.length; i++){
                        if(qName.equals(variables[i])){
                            load[i] = false;
                        }
                    }
                }
            }

            @Override
            public void characters(char[] ch, int start, int length){
                String temp = new String(ch, start, length);

                for(int i=0; i< load.length; i++){
                    if(load[i]){
                        if(i<2){
                            square.put(variables[i], square.get(variables[i])+temp);
                        }
                        else if(i<8){
                            int num = Integer.parseInt(temp);
                            square.put(variables[i], num);
                        }
                        else if(i == 8){
                            Color c = new Color(Integer.parseInt(temp));
                            square.put(variables[i], c);
                        }
                        else {
                            String[] map = temp.split(",");
                            HashMap<String, Integer> hashMap = new HashMap<>();
                            for(String s: map){
                                String[] sp = s.split("=");
                                hashMap.put(sp[0], Integer.parseInt(sp[1]));
                            }
                            square.put(variables[i], hashMap);
                        }
                        break;
                    }
                }
            }
        });
    }
}