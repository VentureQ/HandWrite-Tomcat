package com.wangqi.netty.io.bio.tomcat.http;

import jdk.internal.util.xml.impl.Input;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author wangqi
 * @version 1.0
 * @date 2022/1/11 14:37
 */
public class VTRequest {
    private String method;
    private String url;

    public VTRequest(InputStream in){
        try {
            //获取HTTP内容
            String content="";
            byte[] buff=new byte[1024];
            int len=0;
            if((len=in.read(buff))>0){
                content=new String(buff,0,len);
            }

            String line=content.split("\\n")[0];
            String [] arr=line.split("\\s");

            this.method=arr[0];
            this.url=arr[1].split("\\?")[0];

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getUrl(){
        return url;
    }

    public String getMethod() {
        return method;
    }
}
