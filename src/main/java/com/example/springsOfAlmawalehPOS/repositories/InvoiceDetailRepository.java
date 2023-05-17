package com.example.springsOfAlmawalehPOS.repositories;


import com.example.springsOfAlmawalehPOS.entity.Invoice;
import com.example.springsOfAlmawalehPOS.entity.InvoiceDetail;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface InvoiceDetailRepository extends CrudRepository<InvoiceDetail,Long>, PagingAndSortingRepository<InvoiceDetail, Long> {
    Set<InvoiceDetail> findAllByInvoiceId(Long id);

    @Query(
            value = "SELECT invoice_detail.*,product.name FROM invoice_detail INNER JOIN product ON invoice_detail.product_id=product.id where invoice_detail.invoice_id= :invoice_id",
            nativeQuery = true)
    Set<InvoiceDetail> findAllByOutletAndIs_draft(@Param("invoice_id") Long invoice_id);
}
