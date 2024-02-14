package model;

//import model.RatingException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

        import static org.junit.jupiter.api.Assertions.*;


//Unit tests for LocationCollection class
public class LocationCollectionTest {
    private LocationCollection testCollection;
    private Location l1;
    private Location l2;
    private Location l3;
    Location nullLocation;


    @BeforeEach
    void runBefore() {
        testCollection = new LocationCollection("MyTestCollection"); //construct empty ArrayList<Location>()
        l1 = new Location("Bali", "Beach", 1, false);
        l2 = new Location("Vancouver", "City", 5, true);
        l3 = new Location("Egypt", "Desert", 3, false);
        nullLocation = new Location(null, null, 1, false);

    }

    @Test
    void testConstructor() {
        assertEquals("MyTestCollection", testCollection.getCollectionName());
        assertEquals(0, testCollection.getsize());
    }

    @Test
    void testAddOne() {
        testCollection.addLocation(l1);
        assertEquals(1, testCollection.getsize());

    }

    @Test
    void testAddMultiple() {
        testCollection.addLocation(l1);
        testCollection.addLocation(l2);
        testCollection.addLocation(l3);
        assertEquals(3, testCollection.getsize());
    }

    @Test
    void testAddSameLocationTwice() {
        testCollection.addLocation(l1);
        assertEquals(1, testCollection.getsize());
        testCollection.addLocation(l1);
        assertEquals(1, testCollection.getsize());
    }

    @Test
    void testRemoveOne() {
        testCollection.addLocation(l1);
        assertEquals(1, testCollection.getsize());
        testCollection.removeLocation(l1);
        assertEquals(0, testCollection.getsize());
    }

    @Test
    void testRemoveLocationThatIsNotInCollection() {
        testCollection.addLocation(l2);
        assertEquals(1, testCollection.getsize());
        testCollection.removeLocation(l1);
        assertEquals(1, testCollection.getsize());
    }

    @Test
    void testMarkVisited() {
        testCollection.addLocation(l1);
        testCollection.markVisited(l1);
        assertEquals(true, l1.getVisitingStatus());

        testCollection.addLocation(l2);
        testCollection.markVisited(l2);
        assertEquals(true, l2.getVisitingStatus());

        testCollection.markVisited(l3);
        assertEquals(false, l3.getVisitingStatus());

    }

    @Test
    void testRateLocation() {
        //exception should not be thrown
        testCollection.addLocation(l1);
        try{
            testCollection.rateLocation(l1,2);
        }catch (InvalidRatingException ir){
            fail("Exception should have not been thrown");
        }
        assertEquals(2, l1.getRating());



        try{
            testCollection.rateLocation(l1, 4);
        }catch (InvalidRatingException ir) {
            fail("Exception should have not been thrown");

        }
        assertEquals(4, l1.getRating());



        //boundary cases
        try{
            testCollection.addLocation(l3);
            assertEquals(3, l3.getRating());
            testCollection.rateLocation(l3, 5);
        }catch (InvalidRatingException ir) {
            fail("Exception should have not been thrown");
        }
        assertEquals(5, l3.getRating());


        //not in list

        try{
            assertEquals(5, l2.getRating());
            testCollection.rateLocation(l2, 1);
        }catch(InvalidRatingException ir){
            fail("Exception should have not been thrown");
        }
        assertEquals(5, l2.getRating());



        //exception should be thrown
        testCollection.addLocation(l2);
        try{
            testCollection.rateLocation(l2,6);
            fail("Exception is thrown");
        }catch(InvalidRatingException ir){
            //exception should be thrown
        }



        try{
            testCollection.rateLocation(l2,0);
            fail("Exception is thrown");
        }catch(InvalidRatingException ir){
            //exception should be thrown
        }




    }

    @Test
    void testReturnLocation() {
        testCollection.addLocation(l1);
        assertEquals(l1, testCollection.returnLocation(l1));

        //assertEquals((nullLocation= new Location(null,null,1,false)),
        //  testCollection.returnLocation(l3));

        assertEquals(null, testCollection.returnLocation(l2));


    }

    @Test
    void testGetLocations() {
        testCollection.addLocation(l1);
        testCollection.addLocation(l2);
        assertEquals(2, testCollection.getLocations().size());
    }

}
