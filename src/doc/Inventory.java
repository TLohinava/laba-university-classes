package doc;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class Inventory {

    private static final Logger LOGGER = LogManager.getLogger(Inventory.class);

    public static <T> void printInventoryList(List<T> items) {
        HashMap<Integer, T> inventory = new HashMap<>();
        for(T item : items) {
            inventory.put(items.indexOf(item), item);
        }
        LOGGER.info(inventory);
    }
}
