package com.trainticket.controller;

import com.trainticket.entity.Payment;
import com.trainticket.service.PaymentService;
import edu.fudan.common.util.Response;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.ok;

/**
 * @author Chenjie
 * @date 2017/4/7
 */
@RestController
@RequestMapping("/api/v1/paymentservice")
public class PaymentController {

    @Autowired
    PaymentService service;

    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentController.class);

    @GetMapping(path = "/welcome")
    public String home() {
        return "Welcome to [ Payment Service ] !";
    }

    @PostMapping(path = "/payment")
    public HttpEntity pay(@RequestBody Payment info, @RequestHeader HttpHeaders headers) {
        PaymentController.LOGGER.info("[pay][Pay][PaymentId: {}]", info.getId());
        return ok(service.pay(info, headers));
    }

    @PostMapping(path = "/payment/money")
    public HttpEntity addMoney(@RequestBody Payment info, @RequestHeader HttpHeaders headers) {
        PaymentController.LOGGER.info("[addMoney][Add money][PaymentId: {}]", info.getId());
        return ok(service.addMoney(info, headers));
    }

    @GetMapping(path = "/payment")
    public HttpEntity query(@RequestHeader HttpHeaders headers) {
        PaymentController.LOGGER.info("[query][Query payment]");
        return ok(service.query(headers));
    }

    @GetMapping(path = "/payment/search", produces = "application/json")
    public HttpEntity search(
            @ApiParam(value = "User ID", required = true) @RequestParam("userId") String userId,
            @ApiParam(value = "Start date/time (ISO_LOCAL_DATE format, e.g yyyy-MM-dd)", required = true) @RequestParam("startDate") String startDate,
            @ApiParam(value = "End date/time (yyyy-MM-dd)", required = true) @RequestParam("endDate") String endDate,
            @ApiParam(value = "Page number (0-based)", example = "0") @RequestParam(value = "page", defaultValue = "0") int page,
            @ApiParam(value = "Page size", example = "10") @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestHeader HttpHeaders headers
    ) {
        LOGGER.info("[search][Query payments by user and date range][userId: {}, startDate: {}, endDate: {}, page: {}, size: {}]", userId, startDate, endDate, page, size);
        Response<?> resp = service.searchByUserAndDateRange(userId, startDate, endDate, page, size, headers);
        return ok(resp);
    }

}
