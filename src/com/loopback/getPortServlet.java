package com.loopback;

import com.liquidms.LiquidMS;
import org.json.JSONObject;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class getPortServlet implements Servlet {

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
        HttpServletResponse httpResponse = (HttpServletResponse)response;
        httpResponse.addHeader("Access-Control-Allow-Origin","*");
        response.getOutputStream().write("".getBytes());
    }

    @Override
    public String getServletInfo() {
        return "V1.0";
    }

    @Override
    public void destroy() {
        System.out.println("destroy");
    }
}
