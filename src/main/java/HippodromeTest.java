import com.github.javafaker.Faker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static java.util.random.RandomGenerator.getDefault;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;

public class HippodromeTest {

    private List<Horse> horses = new ArrayList<>();
    private Faker faker = new Faker();


    @Test
    @DisplayName("Constructor. Should throw an exception when NULL is passed")
    public void shouldIllegalArgumentException() {
	Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
	    Hippodrome hippodrome = new Hippodrome(null);
	});
	assertEquals(IllegalArgumentException.class, exception.getClass());
    }

    @Test
    @DisplayName("Constructor. message when  IllegalArgumentException, name=NULL")
    public void shouldIllegalArgumentExceptionMessageNull() {
	Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
	    Hippodrome hippodrome = new Hippodrome(null);
	});
	assertEquals("Horses cannot be null.", exception.getMessage());
    }


    @Test
    @DisplayName("Constructor. message when  IllegalArgumentException, name=isEmpty")
    public void shouldIllegalArgumentExceptionMessageIsEmpty() {
	Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
	    Hippodrome hippodrome = new Hippodrome(new ArrayList<Horse>());
	});
	assertEquals("Horses cannot be empty.", exception.getMessage());
    }


    @Test
    @DisplayName("GetHorses. Check that the method returns the list passed to the constructor")
    public void shouldGetHorses() {
	 horses = new ArrayList<>();
	for (int i = 0; i < 30; i++) {
	    String name = faker.name().firstName();
	    Double speed = getDefault().nextDouble();
	    horses.add(new Horse(name, speed));
	}
        Hippodrome hippodrome = new Hippodrome(horses);
        List<Horse> actual=hippodrome.getHorses();
        assertEquals(horses.size(),actual.size());
        for (int i = 0; i < horses.size(); i++) {
          assertEquals(horses.get(i).getName(),horses.get(i).getName());
            assertEquals(horses.get(i).getSpeed(),horses.get(i).getSpeed());
        }
    }

    @Test
    @DisplayName("Move.Hippodrome.Move.method calls the move method on all horses")
    public void shouldMove() {
        List<Horse> mockHorses = new ArrayList<>();
        Horse mockHorse= Mockito.mock(Horse.class);
        for (int i = 0; i < 50; i++) {
            mockHorses.add(mockHorse);
        }
        Hippodrome hippodrome = new Hippodrome(mockHorses);
        hippodrome.move();
        Mockito.verify(mockHorse,times(50)).move();
    }

    @Test
    @DisplayName("GetWinner.returns the horse with the largest distance value")
    public void shouldGetWinner() {
            for (int i = 0; i < 5; i++) {
		String name = faker.name().firstName();
		Double speed = getDefault().nextDouble();
		Horse testHorse = new Horse(name, speed);
		testHorse.move();
		horses.add(testHorse);
	    }
        Hippodrome hippodrome = new Hippodrome(horses);
      Horse expected=horses.stream().max((o1, o2) -> Double.compare(o1.getDistance(), o2.getDistance())).get();
        Horse actual=hippodrome.getWinner();
        assertEquals(expected.getName(),actual.getName());
        assertEquals(expected.getDistance(),actual.getDistance());
	    }



    }
