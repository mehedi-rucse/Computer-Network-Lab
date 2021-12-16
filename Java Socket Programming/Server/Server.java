import java.io.*;
import java.net.*;


public class Server {
    public static void main(String[] args) throws Exception {
        ServerSocket ss = new ServerSocket(8080);
        Socket s = ss.accept();
        DataOutputStream dout = new DataOutputStream(s.getOutputStream());
        DataInputStream din = new DataInputStream(s.getInputStream());
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str1 = "", str2 = "";
        while (!str1.equalsIgnoreCase("OVER")) {
            str1 = din.readUTF();
            System.out.println("Client : " + str1);
            if (str1.equalsIgnoreCase("LS")) {
                File fl = new File("../Server");
                File[] files = fl.listFiles();
                for (int i = 0; i < files.length; i++) {
                    dout.writeUTF(files[i].getName());
                }
                dout.writeUTF("End");
            }
            System.out.print("Server : ");
            str2 = br.readLine();
            dout.writeUTF(str2);
        }
        ss.close();
        dout.flush();
        dout.close();
        din.close();
    }
}