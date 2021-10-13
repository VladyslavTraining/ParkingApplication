package com.delphi.nice.training.ticket;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


public interface TickerRepository extends JpaRepository<Ticket, Long> {

    Ticket findByUuid(Long uuid);

//    @Modifying(clearAutomatically = true)
//    @Transactional
//    @Query("update Ticket t set t.isValid =:isValid where t.uuid = :uuid")
//    void updateTicket(@Param("isValid") boolean valid,@Param("uuid") long uuid);

}

