package com.loopback;

import com.liquid.bean;
import com.liquid.utility;
import com.liquidms.LiquidMS;
import org.json.JSONObject;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class demoServlet implements Servlet {

    private static String SETUP_STRING = "Liquid MicroService Loopback ver."+ LiquidMS.version;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("init");
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        JSONObject result = new JSONObject();

        try {
            JSONObject body = new JSONObject(com.liquid.utility.get_request_content((HttpServletRequest) request));
            result.put("content", body.getString("content"));
            result.put("filename", body.getString("filename"));
        } catch (Exception e) {
            result.put("error", e.getMessage());
        }

        result.put("header", SETUP_STRING);

        System.out.println("service done");



        //
        // Write long response
        //
        ByteBuffer content = ByteBuffer.wrap(result.toString().getBytes(StandardCharsets.UTF_8));
        AsyncContext async = request.startAsync();
        ServletOutputStream out = response.getOutputStream();
        out.setWriteListener(new WriteListener() {
            @Override
            public void onWritePossible() throws IOException {
                while (out.isReady()) {
                    if (!content.hasRemaining()) {
                        ((HttpServletResponse)response).setStatus(HttpServletResponse.SC_OK);
                        ((HttpServletResponse)response).setContentType("application/json");
                        async.complete();
                        return;
                    }
                    out.write(content.get());
                }
            }

            @Override
            public void onError(Throwable t) {
                System.err.println("Async Error"+t.getMessage());
                async.complete();
            }
        });
    }

    @Override
    public String getServletInfo() {
        return "V1.0";
    }

    @Override
    public void destroy() {
        System.out.println("destroy");
    }

    public void fast_cycle_demo(Object looperObj) {
        System.err.print(".");
    }
    public void slow_cycle_demo(Object looperObj) {
        System.err.print("o");
    }
}
