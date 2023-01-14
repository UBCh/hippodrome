import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.List;
import java.util.concurrent.TimeUnit;

class MainTest {


    @Disabled("Test Passing, Temporarily Disabled")
    @Test
    @DisplayName("Main method. should run no longer than 22 seconds")
    @Timeout(value = 22, unit = TimeUnit.SECONDS)
    public void shouldRunMain() throws Exception {
	List<Horse> horses = List.of(
		new Horse("Буцефал", 2.4),
		new Horse("Туз Пик", 2.5),
		new Horse("Зефир", 2.6),
		new Horse("Пожар", 2.7),
		new Horse("Лобстер", 2.8),
		new Horse("Пегас", 2.9),
		new Horse("Вишня", 3)
	);
	Hippodrome hippodrome = new Hippodrome(horses);

	for (int i = 0; i < 100; i++) {
	    hippodrome.move();
	    watch(hippodrome);
	    TimeUnit.MILLISECONDS.sleep(200);
	}

	String winnerName = hippodrome.getWinner().getName();
	System.out.println("Победил " + winnerName + "!");
    }

    private static void watch(Hippodrome hippodrome) throws Exception {
	hippodrome.getHorses().stream()
		.map(horse -> ".".repeat((int) horse.getDistance()) + horse.getName())
		.forEach(System.out::println);
	System.out.println("\n".repeat(10));
    }



}



