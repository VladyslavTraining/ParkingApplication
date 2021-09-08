import com.delphi.nice.training.service.ParkingService;
import com.delphi.nice.training.service.ParkingServiceImpl;
import com.delphi.nice.training.service.TicketService;
import com.delphi.nice.training.service.TicketServiceImpl;
import org.mockito.Mockito;

public class MockitoTest {

    ParkingService parkingService = Mockito.mock(ParkingServiceImpl.class);
    TicketService ticketService = Mockito.mock(TicketServiceImpl.class);


}
