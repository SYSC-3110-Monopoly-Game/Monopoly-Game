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
    public static JailSquare jail;
    public static ArrayList<Color> colors = new ArrayList<>();
    private final Square[] squares;
    Color[] set = {Color.CYAN, Color.PINK, Color.ORANGE, Color.RED, Color.YELLOW, Color.GREEN, Color.GRAY, Color.BLUE, Color.BLACK, Color.WHITE};

    public MonopolyBoard(String path) throws ParserConfigurationException, IOException, SAXException {
        squares = new Square[SIZE];
        makeSquaresFromXML(path);
        jail = (JailSquare) getSquareAt(Constants.JAIL_SQUARE_INDEX);
        GoToJailSquare goToJail = (GoToJailSquare) getSquareAt(Constants.GO_TO_JAIL_SQUARE_INDEX);
        goToJail.setJail(jail);
        colors.addAll(Arrays.asList(set));
    }

    public Square[] getSquares() {
        return squares;
    }

    public Square getSquareAt(int index) {
        return squares[index];
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
     *
     * @param fileName: file name of the xml file
     */
    public void exportToXML(String fileName) {
        try {
            FileWriter writer = new FileWriter(fileName, true);
            writer.write("<Board>");
            for (Square s : squares) {
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
     *
     * @param type:      type of the square
     * @param variables: variables contains squares' information
     */
    private void loadToSquare(String type, HashMap variables) {
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
        if (variables.get("Color") != Color.LIGHT_GRAY) {
            c = (Color) variables.get("Color");
        }
        String temp = (String) variables.get("JailMap");
        String[] map = temp.split(",");
        HashMap<String, Integer> hashMap = new HashMap<>();
        if (temp.length() != 0) {
            for (String string : map) {
                String[] sp = string.split("=");
                hashMap.put(sp[0], Integer.parseInt(sp[1]));
            }
        }

        s = switch (type) {
            case "Property" -> new PropertySquare(name, number, price, rentPrice, c, housePrice, houseAmount, hotelAmount, owner);
            case "RailRoad" -> new RailRoadSquare(name, number, price, rentPrice, c);
            case "Utility" -> new UtilitySquare(name, number, price, rentPrice, c);
            case "Jail" -> new JailSquare(name, number, price, hashMap);
            case "Tax" -> new TaxSquare(name, number, price);
            case "Go" -> new GoSquare(name, number, price);
            case "GoToJail" -> new GoToJailSquare(name, number);
            case "FreeParking" -> new FreeParkingSquare(name, number);
            default -> new GoSquare(name, number, price);
        };
        squares[(int) variables.get("Number")] = s;
    }

    /**
     * load the file from path
     *
     * @param path: file name of the xml file
     */
    private void makeSquaresFromXML(String path) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();

        HashMap<String, Object> square = new HashMap<>(Map.of("Name", "", "Owner", "", "Number", 0,
                "Price", 0, "RentPrice", 0, "HousePrice", 0, "HouseAmount", 0,
                "HotelAmount", 0, "Color", Color.LIGHT_GRAY, "JailMap", ""));

        boolean[] load = {false, false, false, false, false, false, false, false, false, false};
        final String[] variables = {"Name", "Owner", "Number", "Price", "RentPrice", "HousePrice", "HouseAmount",
                "HotelAmount", "Color", "JailMap"};

        final HashMap<String, Integer> squareType = new HashMap<>(Map.of(
                "Property", 0, "RailRoad", 0, "Utility", 0, "Jail", 0
                , "Tax", 0, "Go", 0, "GoToJail", 0, "FreeParking", 0));

        saxParser.parse(path, new DefaultHandler() {

            @Override
            public void startElement(String uri, String localName, String qName, Attributes attributes) {
                if (qName.equals("Square")) {
                    squareType.put(attributes.getValue("type"), 1);
                } else {
                    for (int i = 0; i < variables.length; i++) {
                        if (qName.equals(variables[i])) {
                            load[i] = true;
                        }
                    }
                }
            }

            @Override
            public void endElement(String uri, String localName, String qName) {
                if (qName.equals("Square")) {
                    for (String name : squareType.keySet()) {
                        if (squareType.get(name) == 1) {
                            loadToSquare(name, square);
                            squareType.put(name, 0);
                            square.put(variables[0], "");
                            square.put(variables[1], "");
                            square.put(variables[9], "");
                        }
                    }
                } else {
                    for (int i = 0; i < variables.length; i++) {
                        if (qName.equals(variables[i])) {
                            load[i] = false;
                        }
                    }
                }
            }

            @Override
            public void characters(char[] ch, int start, int length) {
                String temp = new String(ch, start, length);

                for (int i = 0; i < load.length; i++) {
                    if (load[i]) {
                        if (i < 2 || i == 9) {
                            square.put(variables[i], square.get(variables[i]) + temp);
                        } else if (i < 8) {
                            int num = Integer.parseInt(temp);
                            square.put(variables[i], num);
                        } else {
                            Color c = new Color(Integer.parseInt(temp));
                            square.put(variables[i], c);
                        }
                        break;
                    }
                }
            }
        });
    }
}