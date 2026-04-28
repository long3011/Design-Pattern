package solutions.facade;

import java.io.IOException;

public class FacadeDemo {
    public static void main(String[] args) {
        ApiAccessFacade facade = new ApiAccessFacade();

        try {
            String joke = facade.getAttributeValueFromJson("https://api.chucknorris.io/jokes/random", "value");
            System.out.println("Chuck Norris joke: " + joke);
        } catch (IOException | IllegalArgumentException e) {
            System.out.println("Failed to load Chuck Norris joke: " + e.getMessage());
        }

        try {
            String eurRate = facade.getAttributeValueFromJson("https://api.fxratesapi.com/latest", "EUR");
            System.out.println("Exchange rate from USD to EUR: " + eurRate);
        } catch (IOException | IllegalArgumentException e) {
            System.out.println("Failed to load agify.io data: " + e.getMessage());
        }

        demonstrateErrorHandling(facade);
    }

    private static void demonstrateErrorHandling(ApiAccessFacade facade) {
        try {
            facade.getAttributeValueFromJson("https://api.chucknorris.io/jokes/random", "missingAttribute");
        } catch (IllegalArgumentException e) {
            System.out.println("Expected missing attribute error: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Unexpected I/O error while testing missing attribute: " + e.getMessage());
        }

        try {
            facade.getAttributeValueFromJson("ht!tp://broken-url", "value");
        } catch (IOException e) {
            System.out.println("Expected invalid URL/request error: " + e.getMessage());
        }
    }
}

