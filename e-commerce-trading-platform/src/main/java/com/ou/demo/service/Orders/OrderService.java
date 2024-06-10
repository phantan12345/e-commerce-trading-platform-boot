/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.service.Orders;

import com.ou.demo.service.OrderDetails.DTO.DateDto;
import com.ou.demo.service.OrderDetails.DTO.StatDto;
import com.ou.demo.pojos.Order1;
import com.ou.demo.pojos.Orderdetail;

import com.ou.demo.pojos.User;
import com.ou.demo.repositories.OrderReponsitory;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ou.demo.service.OrderDetails.IOrderdetailService;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Iterator;
import java.util.stream.Collectors;

/**
 *
 * @author ADMIN
 */
@Service
public class OrderService implements IOrderService {

    @Autowired
    private OrderReponsitory OrderReponsitory;

    @Autowired
    private IOrderdetailService OrderdetailService;

    @Override
    public Order1 create(Order1 o) {

        return OrderReponsitory.save(o);
    }

    @Override
    public Object stat( int month, int year) {
        List<StatDto> result = new ArrayList<>();
        YearMonth yearMonth = YearMonth.of(year, month);
        LocalDate firstDay = yearMonth.atDay(1);
        LocalDate lastDay = yearMonth.atEndOfMonth();

        List<Object[]> dateData = OrderdetailService.findByDate(month, year);
        List<StatDto> data = dateData.stream().map(dto -> new StatDto(
                Integer.valueOf(dto[0].toString()),
                new BigDecimal(dto[1].toString()))).collect(Collectors.toList());

        for (LocalDate date = firstDay; !date.isAfter(lastDay); date = date.plusDays(1)) {
            // Check if date is present in data
            boolean isPresent = false;
            Iterator<StatDto> iterator = data.iterator();
            while (iterator.hasNext()) {
                StatDto stat = iterator.next();
                if (stat.getDay()==(date.getDayOfMonth())) {
                    result.add(stat);  // Add the existing stat to result
                    iterator.remove(); // Remove the existing stat from data
                    isPresent = true;
                    break;
                }
            }
            if (!isPresent) {
                // If not present, add a new StatDto with date and default value
                result.add(new StatDto(date.getDayOfMonth(), BigDecimal.ZERO));
            }
        }

        // Add remaining data (if any) to the result
        result.addAll(data);

        return result;

    }

    @Override
    public Set<Order1> GetByUserId(User user) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
