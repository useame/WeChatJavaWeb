package com.wxtrytry.wechat.servlet;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wxtrytry.wechat.util.SignUtil;

@WebServlet("/coreServlet")
public class CoreServlet extends HttpServlet {

    private static final long serialVersionUID = -3990430699254259022L;// to identify versions

    /**
     * to check whether the request is from WeChat Server or not
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");

        PrintWriter out = response.getWriter();
        // if the signature passes our test, we return echostr as it is

        if(SignUtil.checkSignature(signature,timestamp,nonce)) {
            out.print(echostr);
        }

        out.close();
        out = null;
    }

    /**
     * do with the request we get
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doPost(HttpServletRequest request,HttpServletResponse response)
        throws ServletException,IOException {
        // TODO what we should do later
    }

}
