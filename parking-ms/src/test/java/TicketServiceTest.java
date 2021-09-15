import com.delphi.nice.training.dto.TicketDto;
import com.delphi.nice.training.service.ParkingServiceImpl;
import com.delphi.nice.training.service.TicketService;
import com.delphi.nice.training.service.TicketServiceImpl;
import com.delphi.nice.training.writer.JSONWriter;
import org.json.simple.JSONObject;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = TicketServiceImpl.class)
@RunWith(SpringRunner.class)
public class TicketServiceTest {

    private File ticketTestFile = new File("src/test/resources/testTicketData.json");

    private TicketDto ticketDto;

    public void fillTheTempFileForTests(File ticketFile, String userName) {
        List<JSONObject> array = new ArrayList<>();
        JSONObject jsonObject = new JSONObject();
        ticketDto = new TicketDto();
        jsonObject.put("uuid", ticketDto.getUuid());
        jsonObject.put("entranceTime", ticketDto.getEntranceDateTime().toString());
        jsonObject.put("user", userName);
        array.add(jsonObject);
        new JSONWriter(ticketFile.getAbsolutePath()).writeToFile(array);
    }

    @Test
    public void shouldGenerateTicketInFile() {
//        TicketService ticketService = new TicketServiceImpl(ticketTestFile.getAbsolutePath());
//        ticketService.createTicket();

    }

    @Test
    public void TicketGenerateReturnFalse() {
//        TicketService ticketService =
//                new TicketServiceImpl(new ParkingServiceImpl("src/test/resources/testParkArea.json"), "src/test/resources/testTicket.json");
//
//        Assert.assertFalse(ticketService.generateTicket());
    }

//    @After
//    public void restoreFile() {
//        new File("src/test/resources/testTicket.json").delete();
//    }
}