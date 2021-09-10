import com.delphi.nice.training.service.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class MockitoTest {

    @Mock
    private ParkingService parkingService ;

    @Test
    public void shouldReturnTrueIsFreeSlotPresent() {
        TicketService ticketservice = new TicketServiceImpl(parkingService, "src/test/resources/testTicketData.json");
        when(parkingService.park()).thenReturn(1L);
        boolean result = ticketservice.generateTicket();
        Assert.assertFalse(result);
    }

    @Test()
    public void shouldReturnFalseIsSlotPresent() {
        TicketService ticketservice = new TicketServiceImpl(parkingService, "src/test/resources/testTicketData.json");
        when(parkingService.park()).thenThrow(IndexOutOfBoundsException.class);
        boolean result = ticketservice.generateTicket();
        Assert.assertFalse(result);
    }


}
