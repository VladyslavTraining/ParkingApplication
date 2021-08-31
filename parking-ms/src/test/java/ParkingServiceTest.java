import com.delphi.nice.training.reader.JSONReader;
import com.delphi.nice.training.reader.Reader;
import com.delphi.nice.training.service.ParkingService;
import com.delphi.nice.training.service.ParkingServiceImpl;
import com.delphi.nice.training.writer.JSONWriter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

public class ParkingServiceTest {


    @Test(expected = IndexOutOfBoundsException.class)
    public void noFileParkTest() {
        ParkingService parkingService = new ParkingServiceImpl("src/test/resources/emptyParkArea.json");
        parkingService.park();
    }

    @Test
    public void shouldParkOnFirstSpot() {
        ParkingService parkingService = new ParkingServiceImpl("src/test/resources/testParkArea.json");
        Assert.assertEquals(1, parkingService.park());
        Assert.assertTrue(changeIsParkedOnTrue());
    }

    public boolean changeIsParkedOnTrue() {
        Reader reader = new JSONReader();
        JSONArray array = reader.getJsonArr("src/test/resources/testParkArea.json");
        JSONObject jsonObject = (JSONObject) array.get(0);
        return (boolean) jsonObject.get("isParked");
    }

    @AfterClass
    public static void restoreFile() {
        HashMap<String, Object> parkSpot = new HashMap<>();
        parkSpot.put("parkingSlot", 1);
        parkSpot.put("isParked", false);
        JSONArray jsonArray = new JSONArray();
        jsonArray.add(new JSONObject(parkSpot));
        new JSONWriter(jsonArray, "src/test/resources/testParkArea.json").writeToFile();
    }
}
