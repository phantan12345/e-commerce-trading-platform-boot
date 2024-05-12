/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.demo.service.GHN;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ou.demo.pojos.Store;
import com.ou.demo.service.Receipts.DTO.CartInput;
import com.ou.demo.util.Utils;

import io.swagger.models.auth.In;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;

import org.apache.http.entity.ContentType;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.apache.http.client.fluent.Request;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ADMIN
 */
@Service
@Component
public class GHNservice {

    @Autowired
    private Environment env;
    @Autowired
    private Utils utils;
    private static final String GHN_EXPRESS_LINK_GET_PROVINCES = "https://online-gateway.ghn.vn/shiip/public-api/master-data/province";
    private static final String GHN_EXPRESS_LINK_GET_DISTRICTS = "https://online-gateway.ghn.vn/shiip/public-api/master-data/district";
    private static final String GHN_EXPRESS_LINK_GET_WARDS = "https://online-gateway.ghn.vn/shiip/public-api/master-data/ward";
    private static final String GHN_EXPRESS_LINK_GET_AVAILABLE_SERVICES = "https://dev-online-gateway.ghn.vn/shiip/public-api/v2/shipping-order/available-services";
    private static final String GHN_EXPRESS_LINK_CALCULATE_THE_SHIP_FEE = "https://dev-online-gateway.ghn.vn/shiip/public-api/v2/shipping-order/fee";
    private static final String GHN_EXPRESS_LINK_CALCULATE_THE_EXPECTED_DELIVERY_TIME = "https://dev-online-gateway.ghn.vn/shiip/public-api/v2/shipping-order/leadtime";
    private static final String GHN_EXPRESS_LINK_CREATE_ORDER = "https://dev-online-gateway.ghn.vn/shiip/public-api/v2/shipping-order/create";
    private static final String GHN_EXPRESS_LINK_PREVIEW_ORDER = "https://dev-online-gateway.ghn.vn/shiip/public-api/v2/shipping-order/preview";
    private static final String GHN_EXPRESS_LINK_GENERATE_TOKEN_PRINT_ORDER = "https://dev-online-gateway.ghn.vn/shiip/public-api/v2/a5/gen-token";
    private static final String GHN_EXPRESS_LINK_CANCEL_ORDER = "https://dev-online-gateway.ghn.vn/shiip/public-api/v2/switch-status/cancel";
    private static final String GHN_EXPRESS_LINK_REVIEW_ORDER = "https://dev-online-gateway.ghn.vn/shiip/public-api/v2/shipping-order/detail";
    private static final String GHN_EXPRESS_LINK_GET_PICK_SHIFT = "https://dev-online-gateway.ghn.vn/shiip/public-api/v2/shift/date";
    private static final String GHN_EXPRESS_LINK_UPDATE_ORDER = "https://dev-online-gateway.ghn.vn/shiip/public-api/v2/shipping-order/update";
    private static final String GHN_EXPRESS_LINK_GET_STORE = "https://dev-online-gateway.ghn.vn/shiip/public-api/v2/shop/all";
    private static final String GHN_EXPRESS_LINK_CREATE_STORE = "https://dev-online-gateway.ghn.vn/shiip/public-api/v2/shop/register";

    @Value("${ghn.token.sandbox}")
    private String token;

    public Map<Object, Object> getLocationProvinceOfGHNExpress() throws ClientProtocolException, IOException {
        String token = env.getProperty("ghn.token.production");

        Map<Object, Object> mapResult = new HashMap<>();
        try {
            HttpResponse response = Request.Get(GHN_EXPRESS_LINK_GET_PROVINCES)
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Token", token)
                    .execute().returnResponse();
            String temp = new String(EntityUtils.toByteArray(response.getEntity()));
//            System.out.println(temp);
            ObjectMapper mapper = new ObjectMapper();
            mapResult = mapper.readValue(temp, Map.class);
            return mapResult;
        } catch (Exception exception) {
            System.out.printf("%s", exception.toString());
        }
        mapResult.put("message_err", "Có lỗi trong quá khi call api của GHN !!!");
        return mapResult;
    }

    public Map<Object, Object> getLocationDistrictsOfGHNExpress(int provinceID) throws ClientProtocolException, IOException {
        String token = env.getProperty("ghn.token.production");

        Map<Object, Object> mapResult = new HashMap<>();
        try {

            HttpResponse response = Request.Post(GHN_EXPRESS_LINK_GET_DISTRICTS)
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Token", token)
                    .bodyString("{\n"
                            + String.format("    \"province_id\": %d", provinceID)
                            + "\n}", ContentType.APPLICATION_JSON)
                    .execute().returnResponse();
            String temp = new String(EntityUtils.toByteArray(response.getEntity()));
//            System.out.println(temp);
            ObjectMapper mapper = new ObjectMapper();
            mapResult = mapper.readValue(temp, Map.class);
            return mapResult;
        } catch (Exception exception) {
            System.out.printf("%s", exception.toString());
        }
        mapResult.put("message_err", "Có lỗi trong quá khi call api của GHN !!!");
        return mapResult;
    }

    public Map<Object, Object> getLocationWardsOfGHNExpress(int districtID) throws ClientProtocolException, IOException {
        String token = env.getProperty("ghn.token.production");

        Map<Object, Object> mapResult = new HashMap<>();
        try {

            HttpResponse response = Request.Post(GHN_EXPRESS_LINK_GET_WARDS)
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Token", token)
                    .bodyString("{\n"
                            + String.format("    \"district_id\": %d", districtID)
                            + "\n}", ContentType.APPLICATION_JSON)
                    .execute().returnResponse();
            String temp = new String(EntityUtils.toByteArray(response.getEntity()));
//            System.out.println(temp);
            ObjectMapper mapper = new ObjectMapper();
            mapResult = mapper.readValue(temp, Map.class);
            return mapResult;
        } catch (Exception exception) {
            System.out.printf("%s", exception.toString());
        }
        mapResult.put("message_err", "Có lỗi trong quá khi call api của GHN !!!");
        return mapResult;
    }

    public Map<Object, Object> generateTokenToPrintOrderOfGHNExpress(String orderCode) throws ClientProtocolException, IOException {
        String token = env.getProperty("ghn.token.sandbox");
        Map<Object, Object> mapResult = new HashMap<>();
        String orderCodeInput = String.format("[\"%s\"]", orderCode);
        try {

            HttpResponse response = Request.Post(GHN_EXPRESS_LINK_GENERATE_TOKEN_PRINT_ORDER)
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Token", token)
                    .bodyString("{\n"
                            + String.format("    \"order_codes\": %s", orderCodeInput)
                            + "\n}", ContentType.APPLICATION_JSON)
                    .execute().returnResponse();
            String temp = new String(EntityUtils.toByteArray(response.getEntity()));
//            System.out.println(temp);
            ObjectMapper mapper = new ObjectMapper();
            mapResult = mapper.readValue(temp, Map.class);
            return mapResult;
        } catch (Exception exception) {
            System.out.printf("%s", exception.toString());
        }
        mapResult.put("message_err", "Có lỗi trong quá khi call api của GHN !!!");
        return mapResult;
    }

    public Map<Object, Object> cancelOrderOfGHNExpress(String orderCode) throws ClientProtocolException, IOException {
        String token = env.getProperty("ghn.token.sandbox");
        String shopID = env.getProperty("ghn.shopManager.shopID");
        Map<Object, Object> mapResult = new HashMap<>();
        String orderCodeInput = String.format("[\"%s\"]", orderCode);
        try {

            HttpResponse response = Request.Post(GHN_EXPRESS_LINK_CANCEL_ORDER)
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Token", token)
                    .addHeader("ShopId", shopID)
                    .bodyString("{\n"
                            + String.format("    \"order_codes\": %s", orderCodeInput)
                            + "\n}", ContentType.APPLICATION_JSON)
                    .execute().returnResponse();
            String temp = new String(EntityUtils.toByteArray(response.getEntity()));
            System.out.println(temp);
            ObjectMapper mapper = new ObjectMapper();
            mapResult = mapper.readValue(temp, Map.class);
            return mapResult;
        } catch (Exception exception) {
            System.out.printf("%s", exception.toString());
        }
        mapResult.put("message_err", "Có lỗi trong quá khi call api của GHN !!!");
        return mapResult;
    }

    public Map<Object, Object> reviewOrderOfGHNExpress(String orderCode) throws ClientProtocolException, IOException {
        String token = env.getProperty("ghn.token.sandbox");
        Map<Object, Object> mapResult = new HashMap<>();
        try {

            HttpResponse response = Request.Post(GHN_EXPRESS_LINK_REVIEW_ORDER)
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Token", token)
                    .bodyString("{\n"
                            + String.format("    \"order_code\": \"%s\"", orderCode)
                            + "\n}", ContentType.APPLICATION_JSON)
                    .execute().returnResponse();
            String temp = new String(EntityUtils.toByteArray(response.getEntity()));
            System.out.println(temp);
            ObjectMapper mapper = new ObjectMapper();
            mapResult = mapper.readValue(temp, Map.class);
            return mapResult;
        } catch (Exception exception) {
            System.out.printf("%s", exception.toString());
        }
        mapResult.put("message_err", "Có lỗi trong quá khi call api của GHN !!!");
        return mapResult;
    }

    public Map<Object, Object> createStore(Store store, int ProvinceID, String WardCode) throws ClientProtocolException, IOException {
        int shopID = store.getUserId();
        System.out.println(token);
        Map<Object, Object> mapResult = new HashMap<>();
        try {

            HttpResponse response = Request.Post(GHN_EXPRESS_LINK_CREATE_STORE)
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Content-Type", "text/plain")
                    .addHeader("Token", token)
                    .bodyString(String.format("{\n"
                            + "    \"district_id\": %d,\n"
                            + "    \"ward_code\": \"%s\",\n"
                            + "    \"name\": \"%s\",\n"
                            + "    \"phone\": \"%s\",\n"
                            + "    \"address\": \"%s\"\n"
                            + "}", ProvinceID, WardCode, store.getUser().getName(), store.getUser().getPhone(), store.getAddress()),
                            ContentType.APPLICATION_JSON)
                    .execute().returnResponse();
            String temp = new String(EntityUtils.toByteArray(response.getEntity()));
            System.out.println(temp);
            ObjectMapper mapper = new ObjectMapper();
            mapResult = mapper.readValue(temp, Map.class);
            return mapResult;
        } catch (Exception exception) {
            System.out.printf("%s", exception.toString());
        }
        mapResult.put("message_err", "Có lỗi trong quá khi call api của GHN !!!");
        return mapResult;
    }

    public Map<Object, Object> getStore(String clientphone) throws ClientProtocolException, IOException {
        String token = env.getProperty("ghn.token.sandbox");
        Map<Object, Object> mapResult = new HashMap<>();
        try {

            HttpResponse response = Request.Post(GHN_EXPRESS_LINK_GET_STORE)
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Content-Type", "text/plain")
                    .addHeader("Token", token)
                    .bodyString("{\n"
                            + String.format("    \"offset \": \"%s\",\n", 0)
                            + String.format("    \"limit \": \"%s\",\n", 10)
                            + String.format("    \"client_phone \": \"%s\",\n", clientphone)
                            + "\n}", ContentType.APPLICATION_JSON)
                    .execute().returnResponse();
            String temp = new String(EntityUtils.toByteArray(response.getEntity()));
            System.out.println(temp);
            ObjectMapper mapper = new ObjectMapper();
            mapResult = mapper.readValue(temp, Map.class);
            return mapResult;
        } catch (Exception exception) {
            System.out.printf("%s", exception.toString());
        }
        mapResult.put("message_err", "Có lỗi trong quá khi call api của GHN !!!");
        return mapResult;
    }

    public Map<Object, Object> createOrder(Store store, int ProvinceID, String WardCode) throws ClientProtocolException, IOException {
        String token = env.getProperty("ghn.token.sandbox");
        String shopID = store.getUserId().toString();
        Map<Object, Object> mapResult = new HashMap<>();
        try {

            HttpResponse response = Request.Post(GHN_EXPRESS_LINK_UPDATE_ORDER)
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Content-Type", "text/plain")
                    .addHeader("Token", token)
                    .addHeader("ShopId", shopID)
                    .bodyString("{\n"
                            + String.format("    \"to_name \": \"%s\",\n", ProvinceID)
                            + String.format("    \"to_phone \": \"%s\",\n", WardCode)
                            + String.format("    \"to_address \": \"%s\",\n", store.getUser().getName())
                            + String.format("    \"to_ward_name \": \"%s\",\n", store.getUser().getPhone())
                            + String.format("    \"to_district_name \": %d", store.getAddress())
                            + String.format("    \"weight \": %d", 300)
                            + String.format("    \"length \": %d", 15)
                            + String.format("    \"width \": %d", 15)
                            + String.format("    \"height \": %d", 15)
                            + String.format("    \"return_address \": [%d]", store.getAddress())
                            + String.format("    \"return_address \": [%d]", store.getAddress())
                            + String.format("    \"return_address \": [%d]", store.getAddress())
                            + String.format("    \"return_address \": [%d]", store.getAddress())
                            + String.format("    \"return_address \": [%d]", store.getAddress())
                            + "\n}", ContentType.APPLICATION_JSON)
                    .execute().returnResponse();
            String temp = new String(EntityUtils.toByteArray(response.getEntity()));
            System.out.println(temp);
            ObjectMapper mapper = new ObjectMapper();
            mapResult = mapper.readValue(temp, Map.class);
            return mapResult;
        } catch (Exception exception) {
            System.out.printf("%s", exception.toString());
        }
        mapResult.put("message_err", "Có lỗi trong quá khi call api của GHN !!!");
        return mapResult;
    }
}
