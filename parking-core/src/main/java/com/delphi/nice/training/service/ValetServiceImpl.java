package com.delphi.nice.training.service;

import com.delphi.nice.training.exception.HaveNoParkingSlotException;
import com.delphi.nice.training.ticket.TickerRepository;
import com.delphi.nice.training.ticket.Ticket;
import com.delphi.nice.training.ticket.TicketDao;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ValetServiceImpl implements ValetService {
    private final ExitService exitService;
    private final TicketDao ticketDao;
    @Autowired
    private TickerRepository tickerRepository;
    @Value("${car.threshold}")
    private int carThreshold;


//
//    @Autowired
//    private DataSource dataSource;
//
//    @PostConstruct
//    private void initialize() {
//        setDataSource(dataSource);
//    }

    @Override
    public String exitTheCar(long uuid) {
        return exitService.exit(uuid);
    }

    @Override
    public Ticket getTicketById(long uuid) {
        return ticketDao.selectTicketByUuid(uuid);
    }

    @Override
    public List<Ticket> getAllTickets() {
        return ticketDao.getAllTickets();
    }

//    @Override
//    public Ticket parkTheCar() {
//        if (carThreshold > ticketDao.getAllValidTickets().size()) {
//            Ticket ticket = new Ticket();
//            String sql = "INSERT INTO tickets (uuid, entranceDateTime, isValid) VALUES (?, ?, ?)";
//            Objects.requireNonNull(getJdbcTemplate()).update(sql, ticket.getUuid(), ticket.getEntranceDateTime(), "true");
//            return ticket;
//        }
//        throw new HaveNoParkingSlotException();
//    }

    @Override
    public Ticket parkTheCar() {
        if (carThreshold > ticketDao.getAllValidTickets().size()) {
            Ticket ticket = new Ticket();
            tickerRepository.save(ticket);
            return ticket;
        }
        throw new HaveNoParkingSlotException();
    }

    public List<Ticket> getAllValid() {
        return ticketDao.getAllValidTickets();
    }
}
