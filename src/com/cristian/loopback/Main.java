package com.cristian.loopback;

import com.liquidms.LiquidMS;

public class Main {

    public static void main(String[] args) throws Exception {

        try {

            // Create pooled connection
            // com.liquid.connection.addLiquidDBConnection("postgres", null, null, "LiquidX", "liquid", "liquid", true);

            // Register servlet
            LiquidMS.addServlet(demoServlet.class, "api/v1/dss/sign/base64");

            // Register periodic event forever with no start delay
            // LiquidMS.addEvent("slowCycleDemo", "com.customer.app.demoServlet", "slow_cycle_demo", 0, 5000, 0);

            // Register periodic event forever with no start delay
            // LiquidMS.addEvent("fastCycleDemo", "com.customer.app.demoServlet", "fast_cycle_demo", 0, 1000, 0);

            // Run services
            LiquidMS.run(args);

        } catch (Throwable e) {
            System.err.println("Error:"+e.getMessage());
        }
    }
}
