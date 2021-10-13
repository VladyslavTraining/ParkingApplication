package com.delphi.nice.training.ticket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class TicketDaoService implements TicketDao {

    @Autowired
    private TickerRepository tickerRepository;

//    private DataSource dataSource;
//
//    @Autowired
//    public TicketDaoService(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }
//
//    @PostConstruct
//    private void initialize() {
//        setDataSource(dataSource);
//    }

//    @Override
//    public Ticket selectTicketByUuid(long uuid) {
//        String sql = "SELECT * FROM tickets WHERE uuid = ?";
//        return Objects.requireNonNull(getJdbcTemplate()).queryForObject(sql, new Object[]{uuid}, new TicketMapper());
//    }
//
//    @Override
//    public List<Ticket> getAllTickets() {
//        String sql = "SELECT * FROM tickets";
//        return Objects.requireNonNull(getJdbcTemplate()).query(sql, new TicketMapper());
//    }

    //    @Override
//    public Integer getAllValidTickets() {
//        String sql = "SELECT COUNT (isValid) FROM tickets WHERE isValid='true'";
//        return (Integer) Objects.requireNonNull(getJdbcTemplate()).queryForMap(sql).get("");
//    }
//    @Override
//    public List<Ticket> getAllValidTickets() {
//        String sql = "select * from tickets where isValid='true'";
//        return Objects.requireNonNull(getJdbcTemplate()).query(sql, new TicketMapper());
//    }

    @Override
    public Ticket selectTicketByUuid(long uuid) {
        return tickerRepository.findByUuid(uuid);
    }

    @Override
    public List<Ticket> getAllTickets() {
        return tickerRepository.findAll();
    }

    @Override
    public List<Ticket> getAllValidTickets() {
        return tickerRepository.findAll().stream()
                .filter(Ticket::isValid)
                .collect(Collectors.toList());
    }

}

