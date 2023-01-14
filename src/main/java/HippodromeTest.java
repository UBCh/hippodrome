import com.github.javafaker.Faker;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.util.random.RandomGenerator.getDefault;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.times;

public class HippodromeTest {

    private List<Horse> horses = new ArrayList<>();
    private Faker faker = new Faker();


    private static void watch(Hippodrome hippodrome) throws Exception {
	hippodrome.getHorses().stream().map(horse -> ".".repeat((int) horse.getDistance()) + horse.getName()).forEach(System.out::println);
	System.out.println("\n".repeat(10));
    }

    @Disabled("Test Passing, Temporarily Disabled")
    @Test
    @DisplayName("Main method. should run no longer than 22 seconds")
    @Timeout(value = 22, unit = TimeUnit.SECONDS)
    public void shouldRunMain() throws Exception {
	List<Horse> horses = List.of(new Horse("Буцефал", 2.4),
		new Horse("Туз Пик", 2.5),
		new Horse("Зефир", 2.6),
		new Horse("Пожар",2.7),
		new Horse("Лобстер", 2.8),
		new Horse("Пегас", 2.9),
		new Horse("Вишня", 3));
	Hippodrome hippodrome = new Hippodrome(horses);

	for (int i = 0; i < 100; i++) {
	    hippodrome.move();
	    watch(hippodrome);
	    TimeUnit.MILLISECONDS.sleep(200);
	}

	String winnerName = hippodrome.getWinner().getName();
	System.out.println("Победил " + winnerName + "!");
    }

    @Test
    @DisplayName("ConstructorHorse. Should throw an exception when NULL is passed")
    public void shouldHorseIllegalArgumentException() {
	Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
	    Horse horse = new Horse(null, 10.0);
	});
	assertEquals(IllegalArgumentException.class, exception.getClass());
    }

    @Test
    @DisplayName("ConstructorHorse. message when  IllegalArgumentException, name=NULL")
    public void shouldIllegalArgumentExceptionMessage() {
	Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
	    Horse horse = new Horse(null, 10.0);
	});
	assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @DisplayName("ConstructorHorse. Should throw an exception when symbol is passed")
    @ValueSource(strings = {" ", "\t"})
    public void shouldIllegalArgumentExceptionSymbol(String argument) {
	Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
	    Horse horse = new Horse(argument, 10.0);
	});
	assertEquals(IllegalArgumentException.class, exception.getClass());
    }


    @ParameterizedTest
    @DisplayName("ConstructorHorse. message when  IllegalArgumentException, name=symbol")

    @ValueSource(strings = {" ", "\t"})
    public void shouldIllegalArgumentExceptionMessageSymbol(String argument) {
	Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
	    Horse horse = new Horse(argument, 10.0);
	});
	assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    @DisplayName("ConstructorHorse. Should throw an exception when negative number is passed, speed")
    public void shouldIllegalArgumentExceptionSpeed() {
	Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
	    Horse horse = new Horse("horse", -10.0);
	});
	assertEquals(IllegalArgumentException.class, exception.getClass());
    }


    @Test
    @DisplayName("ConstructorHorse. message when  IllegalArgumentException, speed=negative number")
    public void shouldIllegalArgumentExceptionMessageSpeed() {
	Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
	    Horse horse = new Horse("horse", -10.0);
	});
	assertEquals("Speed cannot be negative.", exception.getMessage());
    }


    @Test
    @DisplayName("ConstructorHorse. Should throw an exception when negative number is passed, distance")
    public void shouldIllegalArgumentExceptionDistance() {
	Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
	    Horse horse = new Horse("horse", 10.0, -100);
	});
	assertEquals(IllegalArgumentException.class, exception.getClass());
    }


    @Test
    @DisplayName("ConstructorHorse. message when  IllegalArgumentException, distance=negative number")
    public void shouldIllegalArgumentExceptionMessageDistance() {
	Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
	    Horse horse = new Horse("horse", 10.0, -100);
	});
	assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    @Test
    @DisplayName("Horse.class. the method returns the string that was passed as the first parameter to the constructor")
    public void shouldGetName() {
	String expected = "horse";
	Horse horse = new Horse(expected, 10.0);
	String actual = horse.getName();
	assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Horse.class. the method returns the number that was passed as the second parameter to the constructor")
    public void shouldGetSpeed() {
	double expected = 10.0;
	Horse horse = new Horse("horse", expected);
	double actual = horse.getSpeed();
	assertEquals(expected, actual);
    }

    @Test
    @DisplayName("GetDistanceHorse.returns the number passed to the constructor")
    public void shouldGetDistance() {
	double expected = 10.0;
	Horse horse = new Horse("horse", 3.0, expected);
	double actual = horse.getDistance();
	assertEquals(expected, actual);
    }

    @Test
    @DisplayName("GetDistanceHorse.returns returns null if no value is passed to constructs")
    public void shouldGetDistanceNull() {
	Horse horse = new Horse("horse", 3.0);
	double actual = horse.getDistance();
	assertEquals(0, actual);
    }


    @Test
    @DisplayName("MoveHorse.restore method inside getRandomDouble method")
    public void move() {
	try {
	    MockedStatic<Horse> mockObject = Mockito.mockStatic(Horse.class);
	    Horse horse = new Horse("zero", 10.0);
	    mockObject.when(() -> Horse.getRandomDouble(anyDouble(), anyDouble())).thenReturn(50.0);
	    horse.move();
	    double actual = horse.getDistance();
	    assertEquals(500, actual);
	} catch (Exception e) {
	    e.printStackTrace();
	}

    }


    @ParameterizedTest
    @DisplayName("MoveHorse.the method assigns the specified value to the distance")
    @CsvSource({"50", "35", "63"})
    public void move(double result) {
	try {
	    MockedStatic<Horse> mockObject = Mockito.mockStatic(Horse.class);
	    mockObject.when(() -> Horse.getRandomDouble(anyDouble(), anyDouble())).thenReturn(result);
	    Horse horse = new Horse("zero", 10.0);
	    horse.move();
	    double expected = 10 * result;
	    double actual = horse.getDistance();
	    assertEquals(expected, actual);
	    mockObject.close();
	} catch (Exception e) {
	    e.printStackTrace();
	}

    }


    @Test
    @DisplayName("ConstructorHippodrome. Should throw an exception when NULL is passed")
    public void shouldIllegalArgumentException() {
	Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
	    Hippodrome hippodrome = new Hippodrome(null);
	});
	assertEquals(IllegalArgumentException.class, exception.getClass());
    }

    @Test
    @DisplayName("ConstructorHippodrome. message when  IllegalArgumentException, name=NULL")
    public void shouldIllegalArgumentExceptionMessageNull() {
	Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
	    Hippodrome hippodrome = new Hippodrome(null);
	});
	assertEquals("Horses cannot be null.", exception.getMessage());
    }


    @Test
    @DisplayName("ConstructorHippodrome. message when  IllegalArgumentException, name=isEmpty")
    public void shouldIllegalArgumentExceptionMessageIsEmpty() {
	Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
	    Hippodrome hippodrome = new Hippodrome(new ArrayList<Horse>());
	});
	assertEquals("Horses cannot be empty.", exception.getMessage());
    }


    @Test
    @DisplayName("GetHorsesHippodrome. Check that the method returns the list passed to the constructor")
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
    @DisplayName("MoveHippodrome.Hippodrome.Move.method calls the move method on all horses")
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
    @DisplayName("GetWinnerHippodrome.returns the horse with the largest distance value")
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
