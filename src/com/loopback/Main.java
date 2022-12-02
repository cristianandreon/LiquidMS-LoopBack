package com.loopback;

import com.liquidms.LiquidMS;

public class Main {

    public static void main(String[] args) throws Exception {

        try {

            // Setting service properties
            LiquidMS.port = 8555;
            LiquidMS.https = false;

            // Creating pooled connection
            // com.liquid.connection.addLiquidDBConnection("postgres", null, null, "LiquidX", "liquid", "liquid", true);

            // Registering servlet
            LiquidMS.addServlet(getPortServlet.class, "/api/v1/dss/sign/port");
            LiquidMS.addServlet(getUserPortServlet.class, "/api/v1/dss/sign/gedi/port");
            LiquidMS.addServlet(loopbackServlet.class, "/api/v1/dss/sign/base64");

            // Registering periodic event forever with no start delay
            // LiquidMS.addEvent("slowCycleDemo", "com.customer.app.demoServlet", "slow_cycle_demo", 0, 5000, 0);

            // Registering periodic event forever with no start delay
            // LiquidMS.addEvent("fastCycleDemo", "com.customer.app.demoServlet", "fast_cycle_demo", 0, 1000, 0);

            // Runing services
            LiquidMS.run(args);

        } catch (Throwable e) {
            System.err.println("Main.java Error:"+e.getMessage() + "(port:"+LiquidMS.port+")");
            e.printStackTrace();
        }
    }
}
