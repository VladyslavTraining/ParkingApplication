import com.delphi.nice.training.service.ParkingServiceImpl;
import com.delphi.nice.training.service.TicketService;
import com.delphi.nice.training.service.TicketServiceImpl;
import com.delphi.nice.training.writer.JSONWriter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.*;
import org.junit.runners.MethodSorters;

import java.io.File;
import java.util.HashMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TicketServiceTest {
    private TicketService ticketService = new TicketServiceImpl(new ParkingServiceImpl("src/test/resources/testParkArea.json"), "src/test/resources/testTicket.json");
    @Test
    public void aTicketGenerateReturnTrue() {
        Assert.assertTrue(ticketService.generateTicket());
    }

    @Test
    public void bTicketGenerateReturnFalse() {
        Assert.assertFalse(ticketService.generateTicket());
    }
    @AfterClass
    public static void restoreFile()
    {
        HashMap<String, Object> parkSpot = new HashMap<>();
        parkSpot.put("parkingSlot", 1);
        parkSpot.put("isParked", false);
        JSONArray jsonArray = new JSONArray();
        jsonArray.add(new JSONObject(parkSpot));
        new JSONWriter(jsonArray, "src/test/resources/testParkArea.json").writeToFile();
        new File("src/test/resources/testTicket.json").delete();
    }
}
