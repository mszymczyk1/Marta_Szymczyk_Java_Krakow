import com.ocado.basket.BasketSplitter;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BasketSplitterTests {
    private BasketSplitter basketSplitter;
    private String configFilePath;

    public void setUp() {
        basketSplitter = new BasketSplitter(configFilePath);
    }
    public void setConfigFile(String filePath){
        configFilePath = filePath;

    }

    @Test
    public void testSplitWithEmptyBasket() {
        String absolutePath = "src/main/resources/config.json";
        setConfigFile(absolutePath);
        setUp();
        List<String> items = new ArrayList<>();
        Map<String, List<String>> result = basketSplitter.split(items);
        assertTrue(result.isEmpty());
    }


    @Test
    public void testSplitWithNonEmptyBasket() {
        String absolutePath = "src/main/resources/config.json";
        setConfigFile(absolutePath);
        setUp();
        List<String> items = Arrays.asList("Fond - Chocolate", "Chocolate - Unsweetened", "Nut - Almond, Blanched, Whole",
                "Haggis", "Mushroom - Porcini Frozen", "Cake - Miini Cheesecake Cherry",
                "Sauce - Mint", "Longan", "Bag Clear 10 Lb", "Nantucket - Pomegranate Pear",
                "Puree - Strawberry", "Numi - Assorted Teas", "Apples - Spartan", "Garlic - Peeled",
                "Cabbage - Nappa", "Bagel - Whole White Sesame", "Tea - Apple Green Tea");

        Map<String, List<String>> result = basketSplitter.split(items);
        assertFalse(result.isEmpty());

    }

    @Test
    public void testSplitWithUnknownItems() {
        String absolutePath = "src/main/resources/config.json";
        setConfigFile(absolutePath);
        setUp();
        List<String> items = Arrays.asList("UnknownItem1", "UnknownItem2", "UnknownItem3");
        Map<String, List<String>> result = basketSplitter.split(items);
        assertTrue(result.isEmpty());
    }
}