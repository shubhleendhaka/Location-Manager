package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
//import model.RatingException;


import static org.junit.jupiter.api.Assertions.*;

//unit tests for Location Class
class LocationTest {
    Location l;

    @BeforeEach
    void runBefore() {
        l = new Location("Bali", "Beach", 1, false);

    }

    @Test
    void testConstructor() {
        Location l2;
        l2 = new Location("Vancouver", "City", 5, true);
        assertEquals("Vancouver", l2.getName());
        assertEquals("City", l2.getType());
        assertEquals(5, l2.getRating());
        assertEquals(true, l2.getVisitingStatus());
    }

    @Test
    void testGetterAndSetterName() {
        assertEquals("Bali", l.getName());
        l.setName("NewYork");
        assertEquals("NewYork", l.getName());
    }

    @Test
    void testGetterAndSetterType() {
        assertEquals("Beach", l.getType());
        l.setType("City");
        assertEquals("City", l.getType());
    }

    @Test
    void testGetterAndSetterRating() {
        assertEquals(1, l.getRating());
        l.setRating(5);
        assertEquals(5, l.getRating());
    }

    @Test
    void testGetterAndSetterVisitingStatus() {
        assertEquals(false, l.getVisitingStatus());
        l.setVisitingStatus(true);
        assertEquals(true, l.getVisitingStatus());
    }























  /*  @Test
    void testRatingLessThanOne() {
        try {
            loc = new Location("Bali", "Beach", 0, true);
            assertEquals(0, loc.getRating());
            assertEquals("Bali", loc.getName());
            assertEquals("Beach", loc.getType());
            assertEquals(true, loc.getVisitingStatus());
            fail("I was not expecting this line of code");
        } catch (RatingException re) {
            //expected
        }
    }// when rating is less than one, RatingException is thrown


    @Test
    void testRatingGreaterThanFive() {
        try {
            loc = new Location("Vancouver", "City", 6, true);
            assertEquals(6, loc.getRating());
            fail("I was not expecting this line of code");
        } catch (RatingException re) {
            //expected
        }
    }//when rating is greater than 5, RatingException is thrown

    @Test
    void testRatingIsFive() {
        try {
            loc = new Location("Egypt", "Desert", 5, true);
            assertEquals(5, loc.getRating());
        } catch (RatingException re) {
            fail("I was not expecting this line of code");
        }
    }//Boundary case: when rating is equal to 5, exception is not thrown and Location gets 5 as it's rating

    @Test
    void testRatingIsOne() {
        try {
            loc = new Location("Egypt", "Desert", 1, true);
            assertEquals(1, loc.getRating());
        } catch (RatingException re) {
            fail("I was not expecting this line of code");
        }
    }//Boundary case: when rating is equal to 1, exception is not thrown and Location gets 1 as it's rating

    @Test
    void testRatingIsBetweenOneAndFive() {
        try {
            loc = new Location("Egypt", "Desert", 4, true);
            assertEquals(3, loc.getRating());
        } catch (RatingException re) {
            fail("I was not expecting this line of code");
        }
    }//when rating is between one and five, exception is not thrown and Location gets the rating as it's rating

    @Test
    void testGetterandSetterName(){
        try {
            loc= new Location("Vancouver","City",5,true);
            assertEquals("Vancouver",loc.getName());
            loc.setName("NewYork");
            assertEquals("NewYork",loc.getName());
        } catch (RatingException re) {
            fail("I was not expecting this line of code");
        }

    }


    @Test
    void testGetterandSetterType(){
        try {
            loc= new Location("Miami","Beach",5,true);
            assertEquals("Beach",loc.getType());
            loc.setType("City");
            assertEquals("City",loc.getType());
        } catch (RatingException re) {
            fail("I was not expecting this line of code");
        }

    }

   */


}
