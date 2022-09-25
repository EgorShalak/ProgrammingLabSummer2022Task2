package du;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class testDu {

    private List<String> prefix(String... files) {
        return Arrays.stream(files).map(file -> "Input/" + file).collect(Collectors.toList());
    }

    @Test
    void normalTest() {
        assertEquals("604.94 KB;708.41 KB;80.92 KB;",
                new Du(true, false, false, prefix("banan_3.jpg", "dir", "banan_1.jpg"), true).start());

        assertEquals("103.47;708.41;604.94;",
                new Du(false, false, false, prefix("banan_2.jpg", "dir", "banan_3.jpg"), true).start());

        assertEquals("105.95 KB;725.41 KB;619.46 KB;",
                new Du(true, false, true, prefix("banan_2.jpg", "dir", "banan_3.jpg"), true).start());

        assertEquals("1.43 MB;",
                new Du(true, true, true, prefix("banan_1.jpg", "dir", "banan_3.jpg"), true).start());
    }

    @Test
    void throwTest() {
        assertThrows(IllegalArgumentException.class, () ->
                new Launcher().launch((new String[]{"-h", "Nothing.txt"}), true));
        assertThrows(IllegalArgumentException.class, () ->
                new Launcher().launch((new String[]{"-h"}), true));
    }

    @Test
    void measurementTest() {
        assertEquals("9.77", new Du(false, true, false, List.of("No"), true).chooseMeasurement(10000));
        assertEquals("10.00 KB", new Du(true, true, true, List.of("No"), true).chooseMeasurement(10000));
        assertEquals("530.23 MB", new Du(true, true, false, List.of("No"), true).chooseMeasurement(555986943));
        assertEquals("93.13 GB", new Du(true, true, false, List.of("No"), true).chooseMeasurement(100000000000L));
    }
}
