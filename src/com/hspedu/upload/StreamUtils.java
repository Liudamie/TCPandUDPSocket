package com.hspedu.upload;

import java.io.*;

public class StreamUtils {
    public static byte[] streamToByteArray(InputStream is) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte [] b = new byte[1024];
        int len;
        while((len = is.read(b))!=-1){
            bos.write(b,0,len);
        }
        byte [] array = bos.toByteArray();
        bos.close();
        return array;
    }

    public static String streamToString(InputStream is) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));

        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while((line = bufferedReader.readLine())!=null){
            stringBuilder.append(line+"\r\n");
        }
        return stringBuilder.toString();
    }
}
