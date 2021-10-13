package com.delphi.nice.training.service;

import com.delphi.nice.training.exception.TicketIsNotValidException;
import com.delphi.nice.training.ticket.TickerRepository;
import com.delphi.nice.training.ticket.Ticket;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;

@Slf4j
@Service
public class ExitServiceImpl implements ExitService {
    @Autowired
    private TickerRepository tickerRepository;

//    @Autowired
//    private DataSource dataSource;
//
//    @PostConstruct
//    private void initialize() {
//        setDataSource(dataSource);
//    }

    private long getTime(LocalDateTime enter, LocalDateTime exit) {
        Duration duration = Duration.between(enter, exit);
        return duration.getSeconds();
    }

    @Override
    @Transactional
    public String exit(long uuid) {
        Ticket ticket = tickerRepository.findByUuid(uuid);
        if (ticket.isValid()) {
//            tickerRepository.updateTicket(false, uuid);
            return getPayMessage(ticket);
        }
        throw new TicketIsNotValidException(uuid);
    }

    private String getPayMessage(Ticket ticket) {
        ticket.setValid(false);
        LocalDateTime enter = ticket.getEntranceDateTime();
        LocalDateTime exit = LocalDateTime.now();
        double cost = getTime(enter, exit) * 0.001;
        return String.format("Need to pay ---> %.2f$", cost);
    }

//    @Override
//    public String exit(long uuid) {
//        Ticket ticket = getTicket(uuid);
//        if (ticket.isValid()) {
//            return getPayMessage(uuid, ticket);
//        }
//        throw new TicketIsNotValidException(uuid);
//    }

//    private String getPayMessage(long uuid, Ticket isTicketValid) {
//        String sql = "UPDATE tickets SET isValid='false' WHERE uuid= ?";
//        Objects.requireNonNull(getJdbcTemplate()).update(sql, uuid);
//        LocalDateTime enter = isTicketValid.getEntranceDateTime();
//        LocalDateTime exit = LocalDateTime.now();
//        double cost = getTime(enter, exit) * 0.001;
//        return String.format("Need to pay ---> %.2f$", cost);
//    }
//
//    private Ticket getTicket(long uuid) {
//        String sql = "SELECT * FROM tickets WHERE uuid = ?";
//        return Objects.requireNonNull(getJdbcTemplate()).queryForObject(sql, new Object[]{uuid}, new TicketMapper());
//    }


}