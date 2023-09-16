import java.io.*;
import java.net.Socket;
import java.util.UUID;

public class MyThread implements Runnable{
    Socket socket;
    public  MyThread(Socket socket) {
        this.socket = socket;
    }
    @Override
    public void run() {
        try {
            InputStream inputStream = socket.getInputStream();
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File(UUID.randomUUID().toString().replaceAll("-", "")+".jpg")));
            byte[] bytes = new byte[1024];
            int b;
            while ((b = inputStream.read(bytes)) != -1) {
                bos.write(bytes, 0 ,b);
            }
            bos.close();
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write("上传成功".getBytes());

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
