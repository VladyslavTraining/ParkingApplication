import com.delphi.nice.training.reader.JSONReader;
import com.delphi.nice.training.service.ParkingService;
import com.delphi.nice.training.service.ParkingServiceImpl;
import com.delphi.nice.training.writer.JSONWriter;
import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


public class ParkingServiceTest {

    private final File testFile = new File("src/test/resources/testParkArea.json");

    public void fillTheTempFileForTests(boolean arg, File file) {
        List<JSONObject> array = new ArrayList<>();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("parkingSlot", 1);
        jsonObject.put("isParked", arg);
        array.add(jsonObject);
        new JSONWriter(file.getAbsolutePath()).writeToFile(array);
    }

    @Before
    public void init() {
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

    @Test(expected = FileNotFoundException.class)
    public void noFileParkTest() {
        ParkingService parkingService = new ParkingServiceImpl("notExistingFile");
    }

    @Test
    public void ifPlaceTakenSlotShouldBeChangeOnTrue() {
        ParkingServiceImpl parkingService = new ParkingServiceImpl(testFile.getAbsolutePath());
        parkingService.takeFreeParkSpot();
        JSONObject obj = new JSONReader().getJsonArr(testFile.getAbsolutePath()).get(0);
        assertTrue((boolean)obj.get("isParked"));
    }

}
