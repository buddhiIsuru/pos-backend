package com.example.springsOfAlmawalehPOS.repositories;

import com.example.springsOfAlmawalehPOS.entity.Invoice;
import com.example.springsOfAlmawalehPOS.entity.Outlet;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface InvoiceRepository extends CrudRepository<Invoice, Long>, PagingAndSortingRepository<Invoice, Long> {

    List<Invoice> findAllByOutletId(Outlet outlet);
    List<Invoice> findAllByOutlet(Long id);

    Invoice findFirstById(Long id);
//    Optional<Invoice> findAllByOutletAndIs_draft(Outlet outlet,boolean value);

    @Query(
            value = "SELECT * FROM invoice WHERE outlet_id = :outletID and is_draft=:isDraft and is_cancel=:isCancel order by created_at desc;",
            nativeQuery = true)
    List<Invoice> findAllByOutletAndIs_draft(@Param("outletID") Long outletID, @Param("isDraft") Boolean isDraft, @Param("isCancel") Boolean isCancel);

    @Query(
            value = "SELECT id FROM invoice WHERE outlet_id=:outletID  order by created_at desc;",
            nativeQuery = true)
    List<Long> findLatestInvoiceId(@Param("outletID") Long outletID);

    @Query(
            value = "select * from invoice where created_at between :start_time and :close_time order by created_at desc;",
            nativeQuery = true)
    List<Invoice> getInvoiceListByShift(@Param("start_time") LocalDateTime start_time,@Param("close_time") LocalDateTime close_time);

    @Query(
            value = "SELECT SUM(grand_total_amount) as 'grand_total', SUM(tax_amount) as 'tax', SUM(sub_total_amount) as 'sub_total' from invoice where created_at between :start_time and :close_time  order by created_at;",
            nativeQuery = true)
    List<Invoice> getDailyReportData(@Param("start_time") LocalDateTime start_time,@Param("close_time") LocalDateTime close_time);

}
