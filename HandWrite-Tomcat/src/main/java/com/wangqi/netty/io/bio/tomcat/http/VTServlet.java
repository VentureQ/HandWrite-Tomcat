package com.wangqi.netty.io.bio.tomcat.http;

/**
 * @author wangqi
 * @version 1.0
 * @date 2022/1/11 14:36
 */
public abstract class VTServlet {
    public void service(VTRequest request,VTResponse response) throws Exception{

        //service方法判断是调用doGet()还是调用doPost()
        if("GET".equalsIgnoreCase(request.getMethod())){
            doGet(request,response);
        }else{
            doPost(request,response);
        }

    }
    public abstract void doGet(VTRequest request,VTResponse response) throws Exception;

    public abstract void doPost(VTRequest request,VTResponse response) throws Exception;
}
