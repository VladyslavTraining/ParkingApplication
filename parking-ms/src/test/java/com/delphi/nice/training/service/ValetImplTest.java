//package com.delphi.nice.training.service;
//
//import com.delphi.nice.training.ticket.Ticket;
//import org.json.simple.JSONObject;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//import static org.mockito.ArgumentMatchers.anyLong;
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(SpringExtension.class)
//@ContextConfiguration
//@WithMockUser(username = "Zakhar")
//class ValetImplTest {
//    @Mock
//    ExitService exitService;
//    @Mock
//    TicketService ticketService;
//    @Mock
//    Valet valet = new ValetImpl(exitService,ticketService);
//    @Test
//    void shouldParkTheCar() {
//        when(valet.parkTheCar()).thenReturn(new Ticket());
//        assertThat(valet.parkTheCar()).isNotNull();
//    }
//
//    @Test
//    void exitTheCar() {
//        when(exitService.exit(anyLong())).thenReturn(true);
//        when(valet.exitTheCar(anyLong())).thenReturn(anyString());
//        assertThat(valet.exitTheCar(1314)).isNotNull();
//    }
//
//    @Test
//    void getTicketById() {
//        when(valet.getTicketById(anyLong())).thenReturn(new JSONObject());
//        assertThat(valet.getTicketById(1867187)).isNotNull();
//    }
//
//    @Test
//    void shouldReturnTicketByUsername() {
//        when(valet.getTicketByUsername(anyString())).thenReturn(new JSONObject());
//        assertThat(valet.getTicketByUsername("Zakhar")).isNotNull();
//    }
//}