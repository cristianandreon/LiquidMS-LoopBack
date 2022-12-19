package com.loopback;

import com.liquidms.LiquidMS;
import org.json.JSONObject;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class loopbackServlet implements Servlet {

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
            String bodyContent = com.liquid.utility.get_request_content((HttpServletRequest) request);
            JSONObject body = bodyContent != null && !bodyContent.isEmpty() ? new JSONObject(bodyContent) : new JSONObject();
            result.put("content", body.has("content") ? body.getString("content") : "[EMPTY]");
            result.put("filename", body.has("filename") ? body.getString("filename") : "[EMPTY]");
        } catch (Exception e) {
            result.put("error", e.getMessage());
        }

        result.put("header", SETUP_STRING);
        HttpServletResponse httpResponse = (HttpServletResponse)response;
        httpResponse.addHeader("Access-Control-Allow-Origin","*");
        httpResponse.addHeader("Access-Control-Allow-Headers","*");
        httpResponse.addHeader("Access-Control-Allow-Methods","*");
        httpResponse.addHeader("Access-Control-Allow-Credentials","true");

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

}
