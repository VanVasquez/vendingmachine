import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class StoredItems {
    private final List<Item> itemsList = new ArrayList<>();

    public List<Item> getItemsList() {
        return itemsList;
    }
    public void readXMLFile(String filePath) {
        try {
            // Create a DocumentBuilder
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Parse the XML file
            Document document = builder.parse(new File(filePath));

            // Normalize the XML structure
            document.getDocumentElement().normalize();

            // Get the root element
            Element root = document.getDocumentElement();

            // Get all child nodes of the root element
            NodeList itemList = root.getElementsByTagName("item");

            // Iterate over the item nodes
            for (int i = 0; i < itemList.getLength(); i++) {
                Node itemNode = itemList.item(i);

                // Display only if the node is an element node
                if (itemNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element itemElement = (Element) itemNode;
                    String itemName = itemElement.getAttribute("name");

                    // Get the calorie element
                    Element calorieElement = (Element) itemElement.getElementsByTagName("calorie").item(0);
                    float calorieValue = Float.parseFloat(calorieElement.getAttribute("value"));

                    // Get the price element
                    Element priceElement = (Element) itemElement.getElementsByTagName("price").item(0);
                    float priceValue = Float.parseFloat(priceElement.getAttribute("value"));

                    Item item = new Item(itemName, 0, priceValue, calorieValue);
                    itemsList.add(item);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void displayItems() {
        for (int i = 0; i < itemsList.size(); i++) {
            Item item = itemsList.get(i);
            System.out.println("[" + i + "]==========================");
            System.out.println("    Item: " + item.getItemName());
            System.out.println("    Price: " + item.getPrice());
            System.out.println("    Calorie: " + item.getCalories());
            System.out.println();
        }
    }
}
