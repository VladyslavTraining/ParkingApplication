import com.delphi.nice.training.service.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MockitoTest {

    @Mock
    private ParkingService parkingService;
    @Mock
    private TicketService ticketService;
    @Mock
    private ExitService exitService;

    @Test
    public void shouldReturnTrueIsFreeSlotPresent() {
        when(parkingService.isFreeSlotPresent()).thenReturn(true);
        boolean result = parkingService.isFreeSlotPresent();
        Assert.assertTrue(result);
    }

    @Test
    public void shouldReturnFalseIsSlotPresent() {
        when(parkingService.isFreeSlotPresent()).thenReturn(false);
        boolean result = parkingService.isFreeSlotPresent();
        Assert.assertFalse(result);
    }

    @Test
    public void something() {
        when(parkingService.park()).thenReturn(1L);
        long result = parkingService.park();
        Assert.assertEquals(parkingService.park(), result);
    }

}
