package http.demo;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class HttpClient {

    public static void main(String[] args) {
        Socket socket = null;
        try {
             socket = new Socket("localhost",8080);

             String uri = "/hello.html";

            StringBuffer sb=new StringBuffer("GET "+uri+" HTTP/1.1\r\n");
            sb.append("Accept: */*\r\n");
            sb.append("Accept-Language: zh-cn\r\n");
            sb.append("User-Agent: HTTPClient\r\n");
            sb.append("Host: localhost:8080\r\n");
            sb.append("Connection: Keep-Alive\r\n");

            OutputStream outputStream = socket.getOutputStream();
            outputStream.write(sb.toString().getBytes());

            InputStream inputStream = socket.getInputStream();
            int size = 0;
            int i = 0;
            Thread.sleep(2000);
            while (size<=0) {
                size = inputStream.available();
                if (size>0) {
                    byte[] bytes = new byte[size];
                    inputStream.read(bytes);
                    String response = new String(bytes);
                    System.out.println(response);
                    i++;
                }
            }



        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
