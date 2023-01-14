import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyInt;

@ExtendWith(MockitoExtension.class)
public class HorseTest {


    @Test
    @DisplayName("Constructor. Should throw an exception when NULL is passed")
    public void shouldIllegalArgumentException() {
	Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
	    Horse horse = new Horse(null, 10.0);
	});
	assertEquals(IllegalArgumentException.class, exception.getClass());
    }

    @Test
    @DisplayName("Constructor. message when  IllegalArgumentException, name=NULL")
    public void shouldIllegalArgumentExceptionMessage() {
	Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
	    Horse horse = new Horse(null, 10.0);
	});
	assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @DisplayName("Constructor. Should throw an exception when symbol is passed")

    @ValueSource(strings = {" ", "\t"})
    public void shouldIllegalArgumentExceptionSymbol(String argument) {
	Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
	    Horse horse = new Horse(argument, 10.0);
	});
	assertEquals(IllegalArgumentException.class, exception.getClass());
    }


    @ParameterizedTest
    @DisplayName("Constructor. message when  IllegalArgumentException, name=symbol")

    @ValueSource(strings = {" ", "\t"})
    public void shouldIllegalArgumentExceptionMessageSymbol(String argument) {
	Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
	    Horse horse = new Horse(argument, 10.0);
	});
	assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    @DisplayName("Constructor. Should throw an exception when negative number is passed, speed")
    public void shouldIllegalArgumentExceptionSpeed() {
	Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
	    Horse horse = new Horse("horse", -10.0);
	});
	assertEquals(IllegalArgumentException.class, exception.getClass());
    }


    @Test
    @DisplayName("Constructor. message when  IllegalArgumentException, speed=negative number")
    public void shouldIllegalArgumentExceptionMessageSpeed() {
	Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
	    Horse horse = new Horse("horse", -10.0);
	});
	assertEquals("Speed cannot be negative.", exception.getMessage());
    }


    @Test
    @DisplayName("Constructor. Should throw an exception when negative number is passed, distance")
    public void shouldIllegalArgumentExceptionDistance() {
	Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
	    Horse horse = new Horse("horse", 10.0, -100);
	});
	assertEquals(IllegalArgumentException.class, exception.getClass());
    }


    @Test
    @DisplayName("Constructor. message when  IllegalArgumentException, distance=negative number")
    public void shouldIllegalArgumentExceptionMessageDistance() {
	Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
	    Horse horse = new Horse("horse", 10.0, -100);
	});
	assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    @Test
    public void shouldGetName() {
	String expected = "horse";
	Horse horse = new Horse(expected, 10.0);
	String actual = horse.getName();
	assertEquals(expected, actual);
    }

    @Test
    public void shouldGetSpeed() {
	double expected = 10.0;
	Horse horse = new Horse("horse", expected);
	double actual = horse.getSpeed();
	assertEquals(expected, actual);
    }

    @Test
    @DisplayName("GetDistance.returns the number passed to the constructor")
    public void shouldGetDistance() {
	double expected = 10.0;
	Horse horse = new Horse("horse", 3.0, expected);
	double actual = horse.getDistance();
	assertEquals(expected, actual);
    }

    @Test
    @DisplayName("GetDistance.returns returns null if no value is passed to constructs")
    public void shouldGetDistanceNull() {
	Horse horse = new Horse("horse", 3.0);
	double actual = horse.getDistance();
	assertEquals(0, actual);
    }


    @Test
    @DisplayName("Move.restore method inside getRandomDouble method")
    public void move() {
	try {
	    MockedStatic<Horse> mockObject = Mockito.mockStatic(Horse.class);
	    Horse horse = new Horse("zero", 10.0);
	    mockObject.when(() -> Horse.getRandomDouble(anyDouble(),anyDouble())).thenReturn(50.0);
	    horse.move();
	    double actual = horse.getDistance();
	    assertEquals(500, actual);
	} catch (Exception e) {
	    e.printStackTrace();
	}

    }


    @ParameterizedTest
    @DisplayName("Move.the method assigns the specified value to the distance")
    @CsvSource({
	    "50",
	    "35",
	    "63"})
    public void move( double result) {
	try {
	    MockedStatic<Horse> mockObject = Mockito.mockStatic(Horse.class);
	    mockObject.when(() -> Horse.getRandomDouble(anyDouble(),anyDouble())).thenReturn(result);
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



}