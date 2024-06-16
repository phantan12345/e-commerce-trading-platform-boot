/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.service.Shipment;

import com.ou.demo.configs.VNPayConfig;
import com.ou.demo.exceptions.ShipmentException;
import com.ou.demo.pojos.Order1;
import com.ou.demo.pojos.Product;
import com.ou.demo.pojos.Shipment;
import com.ou.demo.pojos.User;
import com.ou.demo.pojos.Voucher;
import com.ou.demo.repositories.ProductReponsitory;
import com.ou.demo.repositories.ShipmentReponsitory;
import com.ou.demo.repositories.VoucherRepository;
import com.ou.demo.service.Mails.DTO.Mail;
import com.ou.demo.service.Mails.MailService;
import com.ou.demo.service.Messsages.DTO.MessageSummaryDto;
import com.ou.demo.service.Shipment.DTO.ShipmentDto;
import java.io.Serial;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ADMIN
 */
@Service
public class ShipmentService implements IShipmentService {

    @Autowired
    private ShipmentReponsitory shipmentReponsitory;

    @Autowired
    private VoucherRepository VoucherRepository;

    @Autowired
    private ProductReponsitory ProductReponsitory;

    @Autowired
    private MailService MailService;

    @Override
    public List<ShipmentDto> getListShipmentByCurrenUser(int id) {
        List<Object[]> dto = shipmentReponsitory.findShipmentByCurrntUser(id);

        return dto.stream()
                .map(shipmentDtoObject -> new ShipmentDto(
                Integer.valueOf(shipmentDtoObject[0].toString()),
                shipmentDtoObject[1].toString(),
                shipmentDtoObject[2].toString(),
                shipmentDtoObject[2].toString(),
                shipmentDtoObject[4].toString(),
                Integer.valueOf(shipmentDtoObject[5].toString()),
                Integer.valueOf(shipmentDtoObject[6].toString()),
                shipmentDtoObject[7].toString(),
                new BigDecimal(shipmentDtoObject[8].toString())))
                .toList();
    }

    @Override
    public Shipment doAction(ShipmentDto dto) {
        Shipment shipment = shipmentReponsitory.findById(dto.getId()).get();

        switch (dto.getProvider()) {
            case "Accepted":
                if (shipment.getActive().equals("Canceled")) {
                    throw new ShipmentException("Cannot accept a canceled shipment");
                }
                shipment.setActive("Accepted");
                break;

            case "Canceled":
                User user = (shipmentReponsitory.findById(dto.getId()).get()).getOrderdetailId().getOrderId().getUserID();

                Mail mail = new Mail();
                mail.setMailFrom("2051050435tan@ou.edu.vn");
                mail.setMailTo(user.getEmail());
                mail.setMailSubject("VOUCHER HOÀN TIỀN");
                mail.setMailContent("VOUCHER HOÀN TIỀN");
                Voucher voucher = addVouver(dto.getProductId());
                MailService.sendEmai(voucher, mail);

                shipment.setActive("Canceled");
                break;
            case "Completed":
                shipment.setActive("Completed");
                break;
            default:
                throw new AssertionError();
        }
        return shipmentReponsitory.save(shipment);
    }

    @Override
    public List<ShipmentDto> getListShipmentByCurrenStore() {
        List<Object[]> dto = shipmentReponsitory.findShipmentByAdmin();

        return dto.stream()
                .map(shipmentDtoObject -> new ShipmentDto(
                Integer.valueOf(shipmentDtoObject[0].toString()),
                shipmentDtoObject[1].toString(),
                shipmentDtoObject[2].toString(),
                shipmentDtoObject[2].toString(),
                shipmentDtoObject[4].toString(),
                Integer.valueOf(shipmentDtoObject[5].toString()),
                Integer.valueOf(shipmentDtoObject[6].toString()),
                shipmentDtoObject[7].toString(),
                new BigDecimal(shipmentDtoObject[8].toString())))
                .toList();
    }

    private Voucher addVouver(int id) {
        Product product = ProductReponsitory.findById(id).get();
        Voucher voucher = new Voucher(product.getPrice(), VNPayConfig.getRandomNumber(6));
        return VoucherRepository.save(voucher);
    }
}
