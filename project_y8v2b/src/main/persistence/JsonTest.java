package persistence;


import model.Location;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkLocation(String name, String type, int rating, boolean isVisited, Location l) {
        assertEquals(name, l.getName());
        assertEquals(type, l.getType());
        assertEquals(rating, l.getRating());
        assertEquals(isVisited, l.getVisitingStatus());
    }
}


