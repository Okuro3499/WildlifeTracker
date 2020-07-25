import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AnimalTest {
    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void animal_instantiatesCorrectly_true() {
        Animal testAnimal = new Animal ("Elephant", "Zone A");
        assertEquals(true, testAnimal instanceof Animal);
    }

    @Test
    public void getName_instantiatesWithNameCorrectly() {
        Animal testAnimal = new Animal ("Elephant", "Zone A");
        assertEquals("Elephant", testAnimal.getName());
    }

    @Test
    public void getLocation_instantiatesWithLocationCorrectly(){
        Animal testAnimal = new Animal ("Elephant", "Zone A");
        assertEquals("Zone A", testAnimal.getLocation());
    }

    @Test
    public void equals_returnsTrueIfNameAndLocationAreSame_true () {
        Animal firstAnimal = new Animal ("Elephant", "Zone A");
        Animal anotherAnimal = new Animal ("Elephant", "Zone A");
        assertTrue(firstAnimal.equals(anotherAnimal));
    }

    @Test
    public void all_returnsAllInstancesOfAnimals_true() {
        Animal firstAnimal = new Animal ("Elephant", "Zone A");
        firstAnimal.save();
        Animal secondAnimal = new Animal ("Rhino", "Zone B");
        secondAnimal.save();
        assertEquals(true, Animal.all().get(0).equals(firstAnimal));
        assertEquals(true, Animal.all().get(1).equals(secondAnimal));
    }

    @Test
    public void save_assignsIdToObject() {
        Animal testAnimal = new Animal ("Elephant", "Zone A");
        testAnimal.save();
        Animal savedAnimal = Animal.all().get(0);
        assertEquals(testAnimal.getId(), savedAnimal.getId());
    }

    @Test
    public void find_returnsAnimalWithSameId_secondAnimal() {
        Animal firstAnimal = new Animal ("Elephant", "Zone A");
        firstAnimal.save();
        Animal secondAnimal = new Animal ("Rhino", "Zone B");
        secondAnimal.save();
        assertEquals(Animal.find(secondAnimal.getId()), secondAnimal);
    }
}