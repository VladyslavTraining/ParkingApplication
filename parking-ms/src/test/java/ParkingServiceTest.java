import com.delphi.nice.training.reader.JSONReader;
import com.delphi.nice.training.reader.Reader;
import com.delphi.nice.training.service.ParkingService;
import com.delphi.nice.training.service.ParkingServiceImpl;
import com.delphi.nice.training.writer.JSONWriter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.*;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ParkingServiceTest {

    private File testFile;

    @Test(expected = RuntimeException.class)
    public void noFileParkTest() {
        ParkingService parkingService = new ParkingServiceImpl("src/test/resources/emptyParkArea.json");
        parkingService.park();
    }

    @Test
    public void aShouldParkOnFirstSpot() {
        ParkingService parkingService = new ParkingServiceImpl("src/test/resources/testParkArea.json");
        Assert.assertEquals(1, parkingService.park());
    }

    @Test
    public void bChangeIsParkedOnTrue() {
        Reader reader = new JSONReader();
        JSONArray array = reader.getJsonArr("src/test/resources/testParkArea.json");
        JSONObject jsonObject = (JSONObject) array.get(0);
        Assert.assertTrue((boolean) jsonObject.get("isParked"));
    }

    @AfterClass
    public static void restoreFile() {
        HashMap<String, Object> parkSpot = new HashMap<>();
        parkSpot.put("parkingSlot", 1);
        parkSpot.put("isParked", false);
        JSONArray jsonArray = new JSONArray();
        jsonArray.add(new JSONObject(parkSpot));
        new JSONWriter(jsonArray, "src/test/resources/testParkArea.json").writeToFile();
        new File("src/test/resources/emptyParkArea.json").delete();
    }

    public void fillTheTempFileForTests(boolean arg, File file) {
        try (FileWriter fw = new FileWriter(file)) {
            JSONArray array = new JSONArray();
            for (int i = 1; i <= 3; i++) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("parkingSlot", i);
                jsonObject.put("isParked", arg);
                array.add(jsonObject);
            }
            array.writeJSONString(fw);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Before
    public void init() throws IOException {
        testFile = Files.createTempFile("parkingArea", ".json").toFile();
        fillTheTempFileForTests(false, testFile);
    }

    @Test
    public void ifFreeSlotPresentShouldBeTrueTest() {

        ParkingServiceImpl parkingService = new ParkingServiceImpl(testFile.getAbsolutePath());
        assertTrue(parkingService.isFreeSlotPresent());
    }

    @Test
    public void ifFreeSlotNotPresentShouldBeFalseTest() {
        fillTheTempFileForTests(true, testFile);
        ParkingServiceImpl parkingService = new ParkingServiceImpl(testFile.getAbsolutePath());
        assertFalse(parkingService.isFreeSlotPresent());
    }

    @Test
    public void ifSlotTakenShouldBeReturnNumberSlot() {
        ParkingServiceImpl parkingService = new ParkingServiceImpl(testFile.getAbsolutePath());
        assertEquals(1, parkingService.park());
    }

    @After
    public void deleteFile() {
        testFile.delete();
    }
}
