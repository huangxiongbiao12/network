import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {


    public static void main(String[] args) {
        ServerSocket http = null;
        try { 
            http =  new ServerSocket(8080);
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (true) {
            Socket socket = null;
            try {
                socket = http.accept();
            } catch (IOException e) {
                e.printStackTrace();
            }
            InputStream inputStream = null;
            OutputStream outputStream = null;
            try {
                inputStream = socket.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
//                int size = inputStream.available();
                byte[] bytes = new byte[1024];
                int temp = inputStream.read(bytes);
                String request = new String(bytes);
                System.out.println(request);
                if (request.length()==0) {
                    continue;
                }
                
                outputStream = socket.getOutputStream();
                // ´íÎó·µ»ØÀý×Ó
//                outputStreamWriter.write("hello");


                //get http request first line  
                String firstLineOfRequest=request.substring(0, request.indexOf("\r\n"));
                String[] parts=firstLineOfRequest.split(" ");
                String uri=parts[1];
                //mime  
                String contentType;
                if(uri.indexOf("html")!=-1||uri.indexOf("htm")!=-1)
                    contentType="text/html";
                else {
                    contentType="application/octet-stream";
                }
                //create http response  
                //the first line  
                String responseFirstLine="HTTP/1.1 200 OK\r\n";
                //http response head  
                String responseHeader="Content-Type:"+contentType+"\r\n";
                //send http response result
                //send http response first line  
                outputStream.write(responseFirstLine.getBytes());
                //send http response heade  
                outputStream.write(responseHeader.getBytes());
                //send content  
                outputStream.write("\r\n".getBytes());
//                outputStreamWriter.write("{nihao:ni}\r\n");
                String path = uri;
                InputStream in = HttpServer.class.getClassLoader().getResourceAsStream(path.substring(1));
                int len=0;
                byte[] buffer=new byte[128];
                if (in!=null) {
                    while((len=in.read(buffer))!=-1){
                        System.out.println(new String(buffer));
                        outputStream.write(buffer,0,len);
                    }
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if (socket!=null) {
                    try {
                        socket.close();
                        socket = null;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            

        }
        

    }

}
