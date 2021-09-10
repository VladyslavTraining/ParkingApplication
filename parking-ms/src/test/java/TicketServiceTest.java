import com.delphi.nice.training.service.ParkingServiceImpl;
import com.delphi.nice.training.service.TicketService;
import com.delphi.nice.training.service.TicketServiceImpl;
import com.delphi.nice.training.writer.JSONWriter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.*;

import java.io.File;
import java.util.HashMap;

public class TicketServiceTest {

    private final TicketService ticketService =
            new TicketServiceImpl(new ParkingServiceImpl("src/test/resources/testParkArea.json"), "src/test/resources/testTicket.json");

    @Before
    public void setUp() {
        fill(false);
    }

    public void fill(boolean arg) {
        HashMap<String, Object> parkSpot = new HashMap<>();
        parkSpot.put("parkingSlot", 1);
        parkSpot.put("isParked", arg);
        JSONArray jsonArray = new JSONArray();
        jsonArray.add(new JSONObject(parkSpot));
        new JSONWriter(jsonArray, "src/test/resources/testParkArea.json").writeToFile();
    }

    @Test
    public void TicketGenerateReturnTrue() {
        Assert.assertTrue(ticketService.generateTicket());
    }

    @Test
    public void TicketGenerateReturnFalse() {
        fill(true);
        Assert.assertFalse(ticketService.generateTicket());
    }

    @After
    public void restoreFile() {
        new File("src/test/resources/testTicket.json").delete();
    }
}
