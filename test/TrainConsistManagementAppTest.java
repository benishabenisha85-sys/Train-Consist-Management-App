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

    // Reduce method
    int totalCapacity(List<Bogie> bogies) {
        return bogies.stream()
                .map(b -> b.capacity)
                .reduce(0, Integer::sum);
    }

    @Test
    void testReduce_TotalSeatCalculation() {
        List<Bogie> bogies = List.of(
                new Bogie("Sleeper", 72),
                new Bogie("AC Chair", 60),
                new Bogie("First Class", 24)
        );

        int total = totalCapacity(bogies);

        assertEquals(156, total); // 72+60+24
    }

    @Test
    void testReduce_MultipleBogiesAggregation() {
        List<Bogie> bogies = List.of(
                new Bogie("Sleeper", 80),
                new Bogie("AC Chair", 70)
        );

        int total = totalCapacity(bogies);

        assertEquals(150, total);
    }

    @Test
    void testReduce_SingleBogieCapacity() {
        List<Bogie> bogies = List.of(
                new Bogie("Sleeper", 72)
        );

        int total = totalCapacity(bogies);

        assertEquals(72, total);
    }

    @Test
    void testReduce_EmptyBogieList() {
        List<Bogie> bogies = new ArrayList<>();

        int total = totalCapacity(bogies);

        assertEquals(0, total);
    }

    @Test
    void testReduce_CorrectCapacityExtraction() {
        List<Bogie> bogies = List.of(
                new Bogie("Sleeper", 50),
                new Bogie("AC Chair", 30)
        );

        int total = totalCapacity(bogies);

        assertEquals(80, total);
    }

    @Test
    void testReduce_AllBogiesIncluded() {
        List<Bogie> bogies = List.of(
                new Bogie("Sleeper", 40),
                new Bogie("AC Chair", 30),
                new Bogie("First Class", 20)
        );

        int total = totalCapacity(bogies);

        assertEquals(90, total);
    }

    @Test
    void testReduce_OriginalListUnchanged() {
        List<Bogie> bogies = new ArrayList<>();
        bogies.add(new Bogie("Sleeper", 72));
        bogies.add(new Bogie("AC Chair", 60));

        List<Bogie> copy = new ArrayList<>(bogies);

        totalCapacity(bogies);

        assertEquals(copy.size(), bogies.size());
        assertEquals(copy.get(0).name, bogies.get(0).name);
    }
}