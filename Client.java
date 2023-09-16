import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 10089);
        File file = new File("1.jpg");
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
        OutputStream ops = socket.getOutputStream();
        byte[] bytes = new byte[1024];
        int b;
        while ((b = bis.read(bytes)) != -1) {
            ops.write(bytes, 0, b);
        }

        bis.close();
        socket.shutdownOutput();
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        System.out.println(br.readLine());
        socket.close();


    }
}
