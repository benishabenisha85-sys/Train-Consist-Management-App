import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.*;
import java.util.stream.Collectors;

class TrainConsistManagementAppTest {

    class Bogie {
        String name;
        int capacity;

        Bogie(String name, int capacity) {
            this.name = name;
            this.capacity = capacity;
        }
    }

    // Loop-based filtering
    List<Bogie> filterWithLoop(List<Bogie> bogies) {
        List<Bogie> result = new ArrayList<>();
        for (Bogie b : bogies) {
            if (b.capacity > 60) {
                result.add(b);
            }
        }
        return result;
    }

    // Stream-based filtering
    List<Bogie> filterWithStream(List<Bogie> bogies) {
        return bogies.stream()
                .filter(b -> b.capacity > 60)
                .collect(Collectors.toList());
    }

    @Test
    void testLoopFilteringLogic() {
        List<Bogie> bogies = List.of(
                new Bogie("Sleeper", 72),
                new Bogie("AC Chair", 60),
                new Bogie("First Class", 24)
        );

        List<Bogie> result = filterWithLoop(bogies);

        assertEquals(1, result.size());
        assertEquals("Sleeper", result.get(0).name);
    }

    @Test
    void testStreamFilteringLogic() {
        List<Bogie> bogies = List.of(
                new Bogie("Sleeper", 72),
                new Bogie("AC Chair", 60),
                new Bogie("First Class", 24)
        );

        List<Bogie> result = filterWithStream(bogies);

        assertEquals(1, result.size());
        assertEquals("Sleeper", result.get(0).name);
    }

    @Test
    void testLoopAndStreamResultsMatch() {
        List<Bogie> bogies = List.of(
                new Bogie("Sleeper", 80),
                new Bogie("AC Chair", 75),
                new Bogie("First Class", 50)
        );

        List<Bogie> loopResult = filterWithLoop(bogies);
        List<Bogie> streamResult = filterWithStream(bogies);

        assertEquals(loopResult.size(), streamResult.size());
    }

    @Test
    void testExecutionTimeMeasurement() {
        List<Bogie> bogies = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            bogies.add(new Bogie("Sleeper", i));
        }

        long start = System.nanoTime();

        filterWithStream(bogies);

        long end = System.nanoTime();

        long elapsed = end - start;

        assertTrue(elapsed > 0);
    }

    @Test
    void testLargeDatasetProcessing() {
        List<Bogie> bogies = new ArrayList<>();

        for (int i = 0; i < 10000; i++) {
            bogies.add(new Bogie("Bogie" + i, i));
        }

        List<Bogie> result = filterWithStream(bogies);

        // capacities > 60 → from 61 to 9999
        assertEquals(10000 - 61, result.size());
    }
}