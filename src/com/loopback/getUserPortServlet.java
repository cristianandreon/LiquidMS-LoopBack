package com.loopback;

import com.liquidms.LiquidMS;
import org.json.JSONObject;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class getUserPortServlet implements Servlet {

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
        String bodyContent = com.liquid.utility.get_request_content((HttpServletRequest) request);
        JSONObject body = bodyContent != null && !bodyContent.isEmpty() ? new JSONObject(bodyContent) : new JSONObject();
        // body.has("content") ? body.getString("content") : "[EMPTY]");
        HttpServletResponse httpResponse = (HttpServletResponse)response;
        httpResponse.addHeader("Access-Control-Allow-Origin","*");
        httpResponse.addHeader("Access-Control-Allow-Headers","Origin,ContentType,Accept");
        httpResponse.addHeader("Access-Control-Allow-Methods","GET,POST,OPTIONS");
        httpResponse.addHeader("Access-Control-Allow-Credentials","true");
        response.getOutputStream().write("8555".getBytes());
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
