package com.smart.om.rest.pay;

import com.smart.om.biz.sale.SaleHandler;
import com.smart.om.rest.base.BaseResource;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by hxt on 2015/11/30.
 */
@Component("AliPayResource")
public class AliPayResource extends BaseResource {
    private static final Logger logger = Logger.getLogger(PayResource.class);
    @Autowired
    public SaleHandler saleHandler;

    public String addPayOrder(Map dataMap) {

        return "0";
    }

}
