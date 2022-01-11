package com.wangqi.netty.io.bio.tomcat;

import com.wangqi.netty.io.bio.tomcat.http.VTRequest;
import com.wangqi.netty.io.bio.tomcat.http.VTResponse;
import com.wangqi.netty.io.bio.tomcat.http.VTServlet;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author wangqi
 * @version 1.0
 * @date 2022/1/11 15:19
 */
public class VTTomcat {
    private int port=8080;
    private ServerSocket server;
    private Map<String, VTServlet> servletMap=new HashMap<>();

    private Properties webxml=new Properties();

    private void init(){
        try {
            String WEB_INF=this.getClass().getResource("/").getPath();
            FileInputStream fis=new FileInputStream((WEB_INF+"web.properties"));

            webxml.load(fis);

            for (Object k : webxml.keySet()) {
                String key=k.toString();
                if(key.endsWith(".url")){
                    String servletName=key.replaceAll("\\.url$","");
                    String url=webxml.getProperty(key);
                    String className= webxml.getProperty(servletName+".className");

                    //单实例多线程
                    VTServlet obj= (VTServlet) Class.forName(className).newInstance();
                    servletMap.put(url,obj);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void start(){
        init();
        try {
            server=new ServerSocket(this.port);
            System.out.println("VTTomcat 已启动，监听的端口是:"+this.port);

            //使用死循环来等待用户请求
            while(true){
                Socket client=server.accept();
                //HTTP请求发送的是有规律的字符串，，，
                System.out.println(client.toString());
                process(client);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void process(Socket client) throws Exception{
        InputStream is=client.getInputStream();
        OutputStream os=client.getOutputStream();

        VTRequest request=new VTRequest(is);
        VTResponse response=new VTResponse(os);

        //从协议中获得URL，把相应的Servlet用反射进行实例化
        String url=request.getUrl();

        if(servletMap.containsKey(url)){
            servletMap.get(url).service(request,response);
        }else{
            response.write("404-Not Found");
        }

        os.flush();
        os.close();

        is.close();
        client.close();

    }

    public static void main(String[] args) {
        new VTTomcat().start();
    }

}
