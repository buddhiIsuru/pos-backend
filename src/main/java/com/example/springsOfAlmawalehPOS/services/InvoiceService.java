package com.example.springsOfAlmawalehPOS.services;

import com.example.springsOfAlmawalehPOS.entity.*;
import com.example.springsOfAlmawalehPOS.modal.CategoryModal;
import com.example.springsOfAlmawalehPOS.modal.InvoiceDetailModal;
import com.example.springsOfAlmawalehPOS.modal.InvoiceModal;
import com.example.springsOfAlmawalehPOS.repositories.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class InvoiceService {
    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private InvoiceDetailRepository invoiceDetailRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private ChargesRepository chargesRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OutletRepository outletRepository;

    @Autowired
    private ShiftRepository shiftRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public InvoiceModal creatInvoice(InvoiceModal invoiceModal) {
        Invoice invoice = new Invoice();
        if (invoiceModal.getId() != null) {
            invoice.setId(invoiceModal.getId());
        }
        if (invoiceModal.getCustomerId() != null) {
            Customer customer=customerRepository.findByVehicleNo(invoiceModal.getCustomerVehicleNo());
            if(customer==null){
                Customer customer1=new Customer();
                customer1.setVehicleNo(invoiceModal.getCustomerVehicleNo());
                customer=customerRepository.save(customer1);
            }
            invoice.setCustomer(customer);
        }
        invoice.setTotal_qty(invoiceModal.getTotal_qty());
        invoice.setGrandTotalAmount(invoiceModal.getGrandTotalAmount());
        invoice.setSubTotalAmount(invoiceModal.getSubTotalAmount());
        invoice.setTotal_discount(invoiceModal.getTotal_discount());
        invoice.setTax_amount(invoiceModal.getTax_amount());
        invoice.setPayment_method(invoiceModal.getPayment_method());
        invoice.setIs_draft(invoiceModal.getIs_draft());
        invoice.setIs_cancel(false);
        invoice.setRemark(invoiceModal.getRemark());
        invoice.setInvoiceType(invoiceModal.getInvoiceType());
        invoice.setOutlet(outletRepository.findFirstById(invoiceModal.getOutletId()));
        Invoice invoice2 = invoiceRepository.save(invoice);

        Set<InvoiceDetail> invoiceDetailList = new HashSet<>();
        Set<Charges> chargesList = new HashSet<>();

        if (invoice2 != null) {
            for (InvoiceDetail invoiceDetail : invoiceModal.getInvoiceDetails()) {
                invoiceDetail.setInvoice(invoice2);
                InvoiceDetail invoiceDetailSuccess = invoiceDetailRepository.save(invoiceDetail);
                invoiceDetailList.add(invoiceDetailSuccess);
            }
            if (invoiceModal.getExpensesList() != null) {
                for (Charges charges : invoiceModal.getExpensesList()) {
                    charges.setInvoice(invoice2);
                    Charges chargesSuccess = chargesRepository.save(charges);
                    chargesList.add(chargesSuccess);
                }
            }
        }
        InvoiceModal invoiceModalReturn = new InvoiceModal();
        invoiceModalReturn.setId(invoice2.getId());
        return invoiceModalReturn;
    }

    public List<InvoiceModal> getOutletInvoices(Long id) {
        List<Invoice> invoiceList = invoiceRepository.findAllByOutletAndIs_draft(id, false,false);
        List<InvoiceModal> invoiceModalSet = new ArrayList<>();

        for (Invoice invoice : invoiceList) {
            InvoiceModal invoiceModal = new InvoiceModal();
            invoiceModal.setId(invoice.getId());
            invoiceModal.setInvoiceType(invoice.getInvoiceType());
            invoiceModal.setPayment_method(invoice.getPayment_method());
            invoiceModal.setSubTotalAmount(invoice.getSubTotalAmount());
            invoiceModal.setGrandTotalAmount(invoice.getGrandTotalAmount());
            invoiceModal.setTotal_discount(invoice.getTotal_discount());
            invoiceModal.setTotal_qty(invoice.getTotal_qty());
            //invoiceModal.setCustomer(invoice.getCustomer());
            invoiceModal.setTax_amount(invoice.getTax_amount());
            invoiceModal.setRemark(invoice.getRemark());
            invoiceModal.setCreatedAt(invoice.getCreatedAt());
            invoiceModal.setInvoiceId(invoice.getInvoiceId());
            invoiceModalSet.add(invoiceModal);
        }
        return invoiceModalSet;
    }

    public List<InvoiceModal> getOutletDraftInvoicesSet(Long id) {
        List<Invoice> invoiceList = invoiceRepository.findAllByOutletAndIs_draft(id, true,false);
        List<InvoiceModal> invoiceModalSet = new ArrayList<>();

        for (Invoice invoice : invoiceList) {
            InvoiceModal invoiceModal = new InvoiceModal();
            invoiceModal.setId(invoice.getId());
            invoiceModal.setCreatedAt(invoice.getCreatedAt());
            invoiceModal.setGrandTotalAmount(invoice.getGrandTotalAmount());
            invoiceModal.setInvoiceType(invoice.getInvoiceType());
            invoiceModalSet.add(invoiceModal);
        }
        return invoiceModalSet;
    }

    public InvoiceModal getOutletDraftInvoice(Long id) {
        return null;
    }

    public Long getLatestInvoiceId(Long id) {
        List<Long> invoiceIdList = invoiceRepository.findLatestInvoiceId(id);
        Long invoiceId =0L;
        if (invoiceIdList.isEmpty()) {
            invoiceId = 1L;
//            invoiceId = (long) Math.floor(Math.random() * (99) + 9);
        }else {
            invoiceId=invoiceIdList.get(0);
        }
        return invoiceId;
    }

    public InvoiceModal getOutletInvoiceData(Long id) {
        Invoice invoice = invoiceRepository.findFirstById(id);
        Set<InvoiceDetail> invoiceDetailList = invoiceDetailRepository.findAllByOutletAndIs_draft(id);
        Set<InvoiceDetailModal> invoiceDetailModalSet = new HashSet<>();
        InvoiceModal invoiceModal = new InvoiceModal();

        invoiceModal.setId(invoice.getId());
        invoiceModal.setInvoiceType(invoice.getInvoiceType());
        invoiceModal.setPayment_method(invoice.getPayment_method());
        invoiceModal.setSubTotalAmount(invoice.getSubTotalAmount());
        invoiceModal.setGrandTotalAmount(invoice.getGrandTotalAmount());
        invoiceModal.setTotal_discount(invoice.getTotal_discount());
        invoiceModal.setTotal_qty(invoice.getTotal_qty());
        invoiceModal.setCustomer(invoice.getCustomer());
        invoiceModal.setTax_amount(invoice.getTax_amount());
        invoiceModal.setRemark(invoice.getRemark());
        invoiceModal.setCreatedAt(invoice.getCreatedAt());
        invoiceModal.setInvoiceId(invoice.getInvoiceId());

        for (InvoiceDetail invoiceDetail : invoiceDetailList) {
            InvoiceDetailModal invoiceDetailModal = new InvoiceDetailModal();
            invoiceDetailModal.setId(invoiceDetail.getId());
            invoiceDetailModal.setProductId(invoiceDetail.getProductId());
            invoiceDetailModal.setProduct_amount(invoiceDetail.getProduct_amount());
            invoiceDetailModal.setProduct_discount(invoiceDetail.getProduct_discount());
            invoiceDetailModal.setProduct_qty(invoiceDetail.getProduct_qty());
            invoiceDetailModal.setProductName(productRepository.findFirstById(invoiceDetail.getProductId()).getName());
            invoiceDetailModal.setImg(productRepository.findFirstById(invoiceDetail.getProductId()).getImageId());
            invoiceDetailModalSet.add(invoiceDetailModal);
        }
        invoiceModal.setInvoiceDetailsDetailModals(invoiceDetailModalSet);
        return invoiceModal;
    }

    public List<InvoiceModal>  getShiftInvoiceReport(Long id) {
        ShiftManagement shiftManagement = shiftRepository.findFirstById(id);
        if (shiftManagement != null) {
            System.out.println(shiftManagement.getId());
            System.out.println(shiftManagement.getStart_at());
            System.out.println(shiftManagement.getClose_at());
            List<Invoice> invoiceList = invoiceRepository.getInvoiceListByShift(shiftManagement.getStart_at(), shiftManagement.getClose_at());
//            Object object = invoiceRepository.getDailyReportData(shiftManagement.getStart_at(), shiftManagement.getClose_at());
            return buildInvoices(invoiceList);
        }
//        Set<InvoiceDetail> invoiceDetailList = invoiceDetailRepository.findAllByOutletAndIs_draft(id);
//        Set<InvoiceDetailModal> invoiceDetailModalSet = new HashSet<>();
//        InvoiceModal invoiceModal = new InvoiceModal();
//
//        invoiceModal.setId(invoice.getId());
//        invoiceModal.setInvoiceType(invoice.getInvoiceType());
//        invoiceModal.setPayment_method(invoice.getPayment_method());
//        invoiceModal.setSubTotalAmount(invoice.getSubTotalAmount());
//        invoiceModal.setGrandTotalAmount(invoice.getGrandTotalAmount());
//        invoiceModal.setTotal_discount(invoice.getTotal_discount());
//        invoiceModal.setTotal_qty(invoice.getTotal_qty());
//        invoiceModal.setCustomer(invoice.getCustomer());
//        invoiceModal.setTax_amount(invoice.getTax_amount());
//        invoiceModal.setRemark(invoice.getRemark());
//        invoiceModal.setCreatedAt(invoice.getCreatedAt());
//        invoiceModal.setInvoiceId(invoice.getInvoiceId());
//
//        for (InvoiceDetail invoiceDetail : invoiceDetailList) {
//            InvoiceDetailModal invoiceDetailModal = new InvoiceDetailModal();
//            invoiceDetailModal.setId(invoiceDetail.getId());
//            invoiceDetailModal.setProductId(invoiceDetail.getProductId());
//            invoiceDetailModal.setProduct_amount(invoiceDetail.getProduct_amount());
//            invoiceDetailModal.setProduct_discount(invoiceDetail.getProduct_discount());
//            invoiceDetailModal.setProduct_qty(invoiceDetail.getProduct_qty());
//            invoiceDetailModal.setProductName(productRepository.findFirstById(invoiceDetail.getProductId()).getName());
//            invoiceDetailModalSet.add(invoiceDetailModal);
//        }
//        invoiceModal.setInvoiceDetailsDetailModals(invoiceDetailModalSet);
//        return invoiceModal;
        return null;
    }

    public List<InvoiceModal> buildInvoices(List<Invoice> invoiceList) {
//        List<Invoice> invoiceList = invoiceRepository.findAllByOutletAndIs_draft(id, false);
        List<InvoiceModal> invoiceModalSet = new ArrayList<>();

        for (Invoice invoice : invoiceList) {
            InvoiceModal invoiceModal = new InvoiceModal();
            invoiceModal.setId(invoice.getId());
            invoiceModal.setInvoiceType(invoice.getInvoiceType());
            invoiceModal.setPayment_method(invoice.getPayment_method());
            invoiceModal.setSubTotalAmount(invoice.getSubTotalAmount());
            invoiceModal.setGrandTotalAmount(invoice.getGrandTotalAmount());
            invoiceModal.setTotal_discount(invoice.getTotal_discount());
            invoiceModal.setTotal_qty(invoice.getTotal_qty());
            invoiceModal.setCustomer(invoice.getCustomer());
            invoiceModal.setTax_amount(invoice.getTax_amount());
            invoiceModal.setRemark(invoice.getRemark());
            invoiceModal.setCreatedAt(invoice.getCreatedAt());
            invoiceModal.setInvoiceId(invoice.getInvoiceId());
            invoiceModalSet.add(invoiceModal);
        }
        return invoiceModalSet;
    }

    public Long invoiceCancel(Long id) {
        Invoice invoice=invoiceRepository.findFirstById(id);
        invoice.setIs_cancel(true);
        return invoiceRepository.save(invoice).getId();
    }
}
