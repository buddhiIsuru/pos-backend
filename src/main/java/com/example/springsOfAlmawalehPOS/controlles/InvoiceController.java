package com.example.springsOfAlmawalehPOS.controlles;

import com.example.springsOfAlmawalehPOS.entity.Invoice;
import com.example.springsOfAlmawalehPOS.modal.InvoiceModal;
import com.example.springsOfAlmawalehPOS.services.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.LongFunction;

@RestController
@RequestMapping(value = "/invoice")
@CrossOrigin
public class InvoiceController {
    @Autowired
    private InvoiceService invoiceService;

    @PostMapping("/save-invoice")
    public InvoiceModal saveInvoice(@RequestBody InvoiceModal invoiceModal){
        return invoiceService.creatInvoice(invoiceModal);
    }

    @GetMapping("/get-invoice-outlet/{id}")
    public List<InvoiceModal> getOutletInvoiceList(@PathVariable("id") Long id){
        return invoiceService.getOutletInvoices(id);
    }

    @GetMapping("/cancel-invoice/{id}")
    public Long cancelInvoice(@PathVariable("id") Long id){
        return invoiceService.invoiceCancel(id);
    }

    @GetMapping("/get-outlet-invoice-data/{id}")
    public InvoiceModal getOutletInvoiceData(@PathVariable("id") Long id){
        return invoiceService.getOutletInvoiceData(id);
    }

    @GetMapping("/get-outlet-draft-invoice-set/{id}")
    public List<InvoiceModal> getOutletDraftInvoiceList(@PathVariable("id") Long id){
        return invoiceService.getOutletDraftInvoicesSet(id);
    }

    @GetMapping("/get-outlet-latest-invoice-id/{id}")
    public Long getOutletLatestInvoiceId(@PathVariable("id") Long id){
        return invoiceService.getLatestInvoiceId(id);
    }

    @GetMapping("/get-draft-invoices/{id}")
    public InvoiceModal getOutletDraftInvoice(@PathVariable("id") Long id){
        return invoiceService.getOutletDraftInvoice(id);
    }

    @GetMapping("/get-daily-report/{id}")
    public List<InvoiceModal>  getShiftInvoiceReport(@PathVariable("id") Long id){
        return invoiceService.getShiftInvoiceReport(id);
    }
}
