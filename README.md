# BasketDeliverySplitter

**Description**

BasketSplitter is used for splitting products from a customer basket into  delivery options. It chooses minimal number of delivery options and at the same time makes the groups of products delivered by the chosen option as large as possible.

**Requirements**

To run BasketSplitter, you'll need:

- Java Runtime Environment (JRE) version 8 or later.
- A configuration file in JSON format containing the mapping of products to delivery options.
- A basket file in JSON format containing all the products chosen by the customer.

**Configuration**

BasketSplitter relies on a configuration file where each product is assigned a list of delivery options. This file should be in JSON format and should be available when running the application. Here's an example of the configuration format:

["Cocoa Butter", "Tart - Raisin And Pecan", "Table Cloth 54x72 White", "Flower - Daisies"]

**Usage**

To use BasketSplitter, follow these steps:

1. Configure the configuration file according to the described format.
2. Create an instance of the BasketSplitter class, providing the path to the configuration file path in the constructor.
3. Call the split(List<String> items) method on the created instance, passing the list of products in the basket as an argument.
4. The split method will return a map containing the split of products into delivery options.

***Example usage:***

List<String> items = Arrays.asList("Fond - Chocolate", "Chocolate - Unsweetened", "Nut - Almond, Blanched, Whole",
                "Haggis", "Mushroom - Porcini Frozen", "Cake - Miini Cheesecake Cherry");
                
BasketSplitter basketSplitter = new BasketSplitter("path/to/config.json");

Map<String, List<String>> delivery = basketSplitter.split(items);

**Testing**

The BasketSplitter project includes a set of unit tests that verify the correctness of the BasketSplitter class for various cases:

- Testing the split of products with an empty basket.
- Testing the split of products with a non-empty basket.
- Testing the split for unknown products in the configuration.
****

**Author:**
Marta Szymczyk 
marta.m.szymczyk@gmail.com
