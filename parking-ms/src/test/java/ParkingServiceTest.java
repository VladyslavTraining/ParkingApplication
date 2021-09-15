import com.delphi.nice.training.reader.JSONReader;
import com.delphi.nice.training.reader.Reader;
import com.delphi.nice.training.service.ParkingService;
import com.delphi.nice.training.service.ParkingServiceImpl;
import com.delphi.nice.training.writer.JSONWriter;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ParkingServiceTest {

    private File testFile = new File("src/test/resources/testParkArea.json");

//    @Before
    public void init() {
        fillTheTempFileForTests(false, testFile);
    }

//    @Test(expected = IllegalStateException.class)
//    public void noFileParkTest() {
//        ParkingService parkingService = new ParkingServiceImpl("src/test/resources/emptyParkArea.json");
//        parkingService.park();
//    }

//    @Test
    public void shouldParkOnFirstSpot() {
//        ParkingService parkingService = new ParkingServiceImpl("src/test/resources/testParkArea.json");
//        Assert.assertEquals(1, parkingService.park());
    }

//    @Test
    public void changeIsParkedOnTrue() {
//        fillTheTempFileForTests(true, testFile);
//        Reader reader = new JSONReader();
//        List<JSONObject> array = reader.getJsonArr("src/test/resources/testParkArea.json");
//        JSONObject jsonObject = array.get(0);
//        Assert.assertTrue((boolean) jsonObject.get("isParked"));
    }

    public void fillTheTempFileForTests(boolean arg, File file) {
        List<JSONObject> array = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("parkingSlot", i);
            jsonObject.put("isParked", arg);
            array.add(jsonObject);
        }
        new JSONWriter(file.getAbsolutePath()).writeToFile(array);
    }


//    @Test
    public void ifFreeSlotPresentShouldBeTrueTest() {
        ParkingServiceImpl parkingService = new ParkingServiceImpl(testFile.getAbsolutePath());
        assertTrue(parkingService.isFreeSlotPresent());
    }

//    @Test
    public void ifFreeSlotNotPresentShouldBeFalseTest() {
        fillTheTempFileForTests(true, testFile);
        ParkingServiceImpl parkingService = new ParkingServiceImpl(testFile.getAbsolutePath());
        assertFalse(parkingService.isFreeSlotPresent());
    }

//    @Test
    public void ifSlotTakenShouldBeReturnNumberSlot() {
//        ParkingServiceImpl parkingService = new ParkingServiceImpl(testFile.getAbsolutePath());
//        assertEquals(1, parkingService.park());
    }

//    @AfterAll
    static void deleteFile() {
        new File("src/test/resources/emptyParkArea.json").delete();
    }
}
