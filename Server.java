import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.UUID;
import java.util.concurrent.*;

public class Server {
    public static void main(String[] args) throws IOException, InterruptedException {
        //创建线程池对象
        ThreadPoolExecutor pool = new ThreadPoolExecutor(
                4,//核心线程数量
                16,//最大线程数
                60,//空闲时间
                TimeUnit.SECONDS,//空闲单位
                new ArrayBlockingQueue<>(4),//队列
                Executors.defaultThreadFactory(),//线程工厂
                new ThreadPoolExecutor.AbortPolicy()//阻塞队列
        );
        ServerSocket serverSocket = new ServerSocket(10089);
        while (true) {
            Socket socket = serverSocket.accept();
            pool.submit(new MyThread(socket));
        }
    }
}

