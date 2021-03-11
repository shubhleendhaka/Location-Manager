package persistence;


import java.io.IOException;
import java.util.List;
import model.Location;
import model.LocationCollection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JsonWriterTest extends JsonTest {
    public JsonWriterTest() {
    }

    @Test
    void testWriterInvalidFile() {
        try {
            new LocationCollection("My test collection");
            JsonWriter writer = new JsonWriter("./data/my\u0000illegal:fileName.json");
            writer.open();
            Assertions.fail("IOException was expected");
        } catch (IOException var3) {
        }

    }

    @Test
    void testWriterEmptyCollection() {
        try {
            LocationCollection loc = new LocationCollection("My test collection");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyCollection.json");
            writer.open();
            writer.write(loc);
            writer.close();
            JsonReader reader = new JsonReader("./data/testWriterEmptyCollection.json");
            loc = reader.read();
            Assertions.assertEquals("My test collection", loc.getCollectionName());
            Assertions.assertEquals(0, loc.getsize());
        } catch (IOException var4) {
            Assertions.fail("Exception should not have been thrown");
        }

    }

    @Test
    void testWriterGeneralCollection() {
        try {
            LocationCollection loc = new LocationCollection("My test collection");
            loc.addLocation(new Location("Bali", "Beach", 1, true));
            loc.addLocation(new Location("Vancouver", "City", 5, true));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralCollection.json");
            writer.open();
            writer.write(loc);
            writer.close();
            JsonReader reader = new JsonReader("./data/testWriterGeneralCollection.json");
            loc = reader.read();
            Assertions.assertEquals("My test collection", loc.getCollectionName());
            List<Location> locations = loc.getLocations();
            this.checkLocation("Bali", "Beach", 1, true, (Location)locations.get(0));
            this.checkLocation("Vancouver", "City", 5, true, (Location)locations.get(1));
        } catch (IOException var5) {
            Assertions.fail("Exception should have not been thrown");
        }

    }
}

