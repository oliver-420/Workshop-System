package at.htl.workshopsystem.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class CarTest {
    @Test
    void testCarToString() {
        Car car = new Car("Golf",
                "VW",
                2010,
                2011,
                "Hans Peter",
                "Diesel",
                "LL-405-IC");
        
    }
}
