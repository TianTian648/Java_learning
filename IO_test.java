import java.io.*;

public class IO_test {
    public static void main(String[] args) throws IOException {
        File src = new File("C:\\Downloads\\Anaconda3-2022.10-Windows-x86_64.exe");
        File dest1 = new File("1.exe");
        File dest2 = new File("2.exe");
        File dest3 = new File("3.exe");
        File dest4 = new File("4.exe");

        //fileStream(src, dest1);
        fileStreamArray(src, dest2);
        BufferStream(src, dest3);
        BufferStream_arr(src, dest4);
    }

    public static void fileStream(File src, File dest) throws IOException {
        FileInputStream fis = new FileInputStream(src);
        FileOutputStream fos = new FileOutputStream(dest);
        int len = 0;
        long start = System.currentTimeMillis();
        while ((len = fis.read()) != -1) {
            fos.write(len);
        }
        long end = System.currentTimeMillis();
        System.out.println("字节流的基本流用时:" + (end - start) + "毫秒");
        fos.close();
        fis.close();
    }

    public static void fileStreamArray(File src, File dest) throws IOException {
        FileInputStream fis = new FileInputStream(src);
        FileOutputStream fos = new FileOutputStream(dest);
        byte[] bytes = new byte[1024 * 1024 * 5];
        int len = 0;
        long start = System.currentTimeMillis();
        while ((len = fis.read(bytes)) != -1) {
            fos.write(bytes, 0, len);
        }
        long end = System.currentTimeMillis();
        System.out.println("字节流的基本流+5MB的缓冲数组用时:" + (end - start) + "毫秒");
        fos.close();
        fis.close();
    }

    public static void BufferStream(File src, File dest) throws IOException {
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(src));
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(dest));
        int len = 0;
        long start = System.currentTimeMillis();
        while ((len = bis.read()) != -1) {
            bos.write(len);
        }
        long end = System.currentTimeMillis();
        System.out.println("字节缓冲流的基本流用时:" + (end - start) + "毫秒");
        bos.close();
        bis.close();
    }

    public static void BufferStream_arr(File src, File dest) throws IOException {
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(src));
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(dest));
        byte[] bytes = new byte[1024 * 1024 * 5];
        int len = 0;
        long start = System.currentTimeMillis();
        while ((len = bis.read(bytes)) != -1) {
            bos.write(bytes, 0, len);
        }
        long end = System.currentTimeMillis();
        System.out.println("字节缓冲流 + 5MB内存缓冲区的基本流用时:" + (end - start) + "毫秒");
        bos.close();
        bis.close();
    }
}
