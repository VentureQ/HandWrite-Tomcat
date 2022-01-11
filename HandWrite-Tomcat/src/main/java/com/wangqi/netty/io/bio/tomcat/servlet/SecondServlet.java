package com.wangqi.netty.io.bio.tomcat.servlet;

import com.wangqi.netty.io.bio.tomcat.http.VTRequest;
import com.wangqi.netty.io.bio.tomcat.http.VTResponse;
import com.wangqi.netty.io.bio.tomcat.http.VTServlet;

/**
 * @author wangqi
 * @version 1.0
 * @date 2022/1/11 15:16
 */
public class SecondServlet extends VTServlet {
    @Override
    public void doGet(VTRequest request, VTResponse response) throws Exception {
        this.doGet(request,response);
    }

    @Override
    public void doPost(VTRequest request, VTResponse response) throws Exception {
        response.write("This is second servlet");
    }
}
