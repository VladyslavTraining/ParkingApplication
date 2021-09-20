//import com.delphi.nice.training.ticket.Ticket;
//import com.delphi.nice.training.exception.UserNotFoundException;
//import com.delphi.nice.training.writer.JSONWriter;
//import org.json.simple.JSONObject;
//import org.junit.Test;
//
//import java.io.File;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotNull;
//
//
//public class TicketServiceTest {
//
//    private File ticketTestFile = new File("src/test/resources/testTicketData.json");
//    private File staticFile = new File("src/test/resources/staticTestTicketData.json");
//
//    private Ticket ticket;
//
//    public void fillTheTempFileForTests(File ticketFile) {
//        List<JSONObject> array = new ArrayList<>();
//        JSONObject jsonObject = new JSONObject();
//        ticket = new Ticket();
//        jsonObject.put("uuid", ticket.getUuid());
//        jsonObject.put("entranceTime", ticket.getEntranceDateTime().toString());
//        array.add(jsonObject);
//        new JSONWriter(ticketFile.getAbsolutePath()).writeToFile(array);
//    }
//
//    @Test
//    public void shouldGenerateTicketInFile() {
//        TicketService ticketService = new TicketServiceImpl(ticketTestFile.getAbsolutePath());
//        Ticket ticket = ticketService.createTicket();
//        assertNotNull(ticket);
//    }
//
//    @Test
//    public void TicketGenerateReturnFalse() {
//        TicketService ticketService = new TicketServiceImpl(ticketTestFile.getAbsolutePath());
//        fillTheTempFileForTests(ticketTestFile);
//        List<JSONObject> allTickets = ticketService.getAllTickets();
//        assertEquals(1, allTickets.size());
//    }
//
//    @Test
//    public void shouldReturnTicketDtoById() {
//        TicketService ticketService = new TicketServiceImpl(staticFile.getAbsolutePath());
//        JSONObject ticket = ticketService.getTicket(1585937);
//        assertNotNull(ticket);
//    }
//
//    @Test(expected = UserNotFoundException.class)
//    public void shouldThrowException() {
//        TicketService ticketService = new TicketServiceImpl(staticFile.getAbsolutePath());
//        ticketService.getTicket(123L);
//    }
//}