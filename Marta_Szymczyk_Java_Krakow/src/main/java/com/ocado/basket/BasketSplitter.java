package com.ocado.basket;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.*;

public class BasketSplitter {
    private Map<String, List<String>> productsDelivery;
    public BasketSplitter(String absolutePathToConfigFile) {

        try{
            ObjectMapper mapper = new ObjectMapper();
            File productConfig = new File(absolutePathToConfigFile);
            productsDelivery = mapper.readValue(productConfig, Map.class);
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    public Map<String, List<String>> split(List<String> items) {
        Map<String, List<String>> deliveryFinal = new HashMap<>();
        if(!(productsDelivery.isEmpty() || items.isEmpty()) ) {
            List<DeliveryOption> deliveryOptionsCart;

            deliveryOptionsCart = processBasket(items);
            deliveryFinal = processDelivery(deliveryOptionsCart);
        }
        return deliveryFinal;
    }

    private Map<String, List<String>> processDelivery(List<DeliveryOption> deliveryOptionsCart) {
        Map<String, List<String>> deliveryFinal = new HashMap<>();
        List<DeliveryOption> deliveryOptionsSorted;
        deliveryOptionsSorted = sortDeliveryOptions(deliveryOptionsCart);
        while (!deliveryOptionsSorted.isEmpty() && !deliveryOptionsSorted.get(0).getProducts().isEmpty()) {
            //Sorting our list of suppliers

            sortDeliveryOptions(deliveryOptionsCart);
            // Retrieving frist element and if it doesn't have zero items, we add it to a map with delivery options
            DeliveryOption temp = deliveryOptionsSorted.get(0);
            deliveryFinal.put(temp.getName(), temp.getProducts());

            //Delete the element from the list and the products it delivers
            deliveryOptionsSorted.remove(temp);
            for (String productToDelete : temp.getProducts()) {
                for (DeliveryOption deliveryOptions : deliveryOptionsSorted) {
                    if (deliveryOptions.getProducts().contains(productToDelete)) {
                        deliveryOptions.getProducts().remove(productToDelete);

                        deliveryOptions.setNumberOfDeliverableProducts(deliveryOptions.getProducts().size());
                    }
                }
            }
        }
        return deliveryFinal;
    }

    private List<DeliveryOption> sortDeliveryOptions(List<DeliveryOption> deliveryOptionsCart) {
        Collections.sort(deliveryOptionsCart);
        return deliveryOptionsCart;
    }

    private List<DeliveryOption> processBasket(List<String> items){
        List<DeliveryOption> deliveryOptionsCart = new ArrayList<>();
        List<String> mustHaveDeliveries = new ArrayList<>();
        Map<String, List<String>> deliveryOptionsMap = new HashMap<>();

        // Adding all delivery options available for our basket
        for (String item : items){
            for (Map.Entry<String, List<String>> entry : productsDelivery.entrySet()) {
                String product = entry.getKey();
                if (product.equals(item)) {
                    List<String> deliveries = entry.getValue();
                    for (String delivery : deliveries) {
                        if (!deliveryOptionsMap.containsKey(delivery)) {
                            deliveryOptionsMap.put(delivery, new ArrayList<>());
                        }
                        deliveryOptionsMap.get(delivery).add(product);
                        if (deliveries.size() == 1) {
                            mustHaveDeliveries.add(delivery);
                        }
                    }
                    if(deliveries.size() == 1){
                        mustHaveDeliveries.add(deliveries.get(0));
                    }
                }
            }
        }
        //Adding all products which can be delivered by the specified delivery option
        for (Map.Entry<String, List<String>> entry : deliveryOptionsMap.entrySet()) {
            String deliveryName = entry.getKey();
            List<String> productsDeliverable = entry.getValue();
            DeliveryOption deliveryOption = new DeliveryOption(deliveryName);
            deliveryOption.setProducts(productsDeliverable);
            deliveryOption.setNumberOfDeliverableProducts(productsDeliverable.size());
            if (mustHaveDeliveries.contains(deliveryName)) {
                deliveryOption.setHasForcingProduct(true);
            }
            deliveryOptionsCart.add(deliveryOption);
        }

        return deliveryOptionsCart;
    }
}