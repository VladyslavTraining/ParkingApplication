package com.delphi.nice.training.ticket;

import org.springframework.jdbc.core.RowMapper;

import javax.annotation.Nonnull;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class TicketMapper implements RowMapper<Ticket> {
    @Override
    public Ticket mapRow(@Nonnull ResultSet resultSet, int i) throws SQLException {
        Ticket ticket = new Ticket();
        ticket.setUuid(resultSet.getLong("uuid"));
        ticket.setEntranceDateTime(resultSet.getObject("entranceDateTime", LocalDateTime.class));
        ticket.setValid(resultSet.getBoolean("isValid"));
        return ticket;
    }
}
