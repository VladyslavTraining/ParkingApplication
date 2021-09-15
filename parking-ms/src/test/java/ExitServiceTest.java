import com.delphi.nice.training.service.*;
import com.delphi.nice.training.writer.JSONWriter;
import org.json.simple.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ExitServiceTest {

//    private final TicketService ticketService =
//            new TicketServiceImpl(new ParkingServiceImpl("src/test/resources/testParkArea.json"), "src/test/resources/testTicket.json");

    private final ExitService exitService =
            new ExitServiceImpl("src/test/resources/testTicket.json", "src/test/resources/testParkArea.json");

    public void fillTheTempFileForTests(boolean arg, String file) {
        List<JSONObject> array = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("parkingSlot", i);
            jsonObject.put("isParked", arg);
            array.add(jsonObject);
        }
        new JSONWriter(file).writeToFile(array);
    }

//    @Before
    public void init() {
        fillTheTempFileForTests(false, "src/test/resources/testParkArea.json");
    }

//    @Test
    public void shouldReturnTrueIfIdPresentInFile() {
//        ticketService.generateTicket();
//        assertTrue(exitService.exit(ticketService.getTicketID()));
    }

//    @Test
    public void shouldReturnFalseIfIdNotExistInFile() {
        assertFalse(exitService.exit(12345));
    }

//    @After
    public void refill() {
        fillTheTempFileForTests(false, "src/test/resources/testParkArea.json");
        new File("src/test/resources/testTicket.json").delete();
    }
}
