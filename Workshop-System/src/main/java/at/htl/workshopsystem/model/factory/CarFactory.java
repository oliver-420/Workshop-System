package at.htl.workshopsystem.model.factory;

import at.htl.workshopsystem.model.Car;
import com.fasterxml.jackson.databind.JsonNode;

public class CarFactory {
    public static Car createCarFromJsonNode(JsonNode node) {
        String model = node.get("model").asText();
        String manufacturer = node.get("make").asText();
        int productionYear = node.get("year").asInt();
        String fuel = node.get("fuel_type").asText();

        return new Car(model, manufacturer, productionYear, fuel);
    }
}
