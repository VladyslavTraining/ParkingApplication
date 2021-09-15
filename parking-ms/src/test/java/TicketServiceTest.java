import com.delphi.nice.training.service.ParkingServiceImpl;
import com.delphi.nice.training.service.TicketService;
import com.delphi.nice.training.service.TicketServiceImpl;
import com.delphi.nice.training.writer.JSONWriter;
import org.json.simple.JSONObject;
import org.junit.*;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TicketServiceTest {

    public void fill(boolean arg) {
        HashMap<String, Object> parkSpot = new HashMap<>();
        parkSpot.put("parkingSlot", 1);
        parkSpot.put("isParked", arg);
        List<JSONObject> jsonArray = new ArrayList<>();
        jsonArray.add(new JSONObject(parkSpot));
        new JSONWriter("src/test/resources/testParkArea.json").writeToFile(jsonArray);
    }

    @Test
    public void TicketGenerateReturnTrue() {
        fill(false);
//        TicketService ticketService =
//                new TicketServiceImpl(new ParkingServiceImpl("src/test/resources/testParkArea.json"), "src/test/resources/testTicket.json");
//
//        Assert.assertTrue(ticketService.generateTicket());
    }

    @Test
    public void TicketGenerateReturnFalse() {
        fill(true);
//        TicketService ticketService =
//                new TicketServiceImpl(new ParkingServiceImpl("src/test/resources/testParkArea.json"), "src/test/resources/testTicket.json");
//
//        Assert.assertFalse(ticketService.generateTicket());
    }

    @After
    public void restoreFile() {
        new File("src/test/resources/testTicket.json").delete();
    }
}