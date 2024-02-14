package persistence;


import java.io.IOException;
import java.util.List;

import model.Location;
import model.LocationCollection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JsonReaderTest extends JsonTest {
    public JsonReaderTest() {
    }

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFileExists.json");

        try {
            LocationCollection loc = reader.read();
            Assertions.fail("IOException expected");
        } catch (IOException var3) {
        }

    }

    @Test
    void testReaderEmptyCollection() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyCollection.json");

        try {
            LocationCollection loc = reader.read();
            Assertions.assertEquals("My test collection", loc.getCollectionName());
            Assertions.assertEquals(0, loc.getsize());
        } catch (IOException var3) {
            Assertions.fail("Couldn't read from file");
        }

    }

    @Test
    void testReaderGeneralCollection() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralCollection.json");

        try {
            LocationCollection loc = reader.read();
            Assertions.assertEquals("My test collection", loc.getCollectionName());
            List<Location> locations = loc.getLocations();
            Assertions.assertEquals(2, locations.size());
            this.checkLocation("Bali", "Beach", 1, true, (Location) locations.get(0));
            this.checkLocation("Vancouver", "City", 5, true, (Location) locations.get(1));
        } catch (IOException var4) {
            Assertions.fail("Couldn't read from file");
        }

    }
}


