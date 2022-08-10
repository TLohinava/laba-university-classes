package doc;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import structure.Faculty;

public class Inventory {

    private static final Logger LOGGER = LogManager.getLogger(Faculty.class);

    public <T> void printInventoryList(T[] items) {
        for(T item : items) {
            LOGGER.info(item);
        }
    }
}
