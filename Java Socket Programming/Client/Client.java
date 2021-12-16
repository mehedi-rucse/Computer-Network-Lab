import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) throws Exception {
        Socket s = new Socket("localhost", 8080);
        DataOutputStream dout = new DataOutputStream(s.getOutputStream());
        DataInputStream din = new DataInputStream(s.getInputStream());
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str1 = "", str2 = "";
        while (!str1.equalsIgnoreCase("OVER")) {
            System.out.print("Client : ");
            str1 = br.readLine();
            dout.writeUTF(str1);
            if (str1.equalsIgnoreCase("LS")) {
                
                System.out.println("\tList:");
                while (true) {
                    String st = din.readUTF();
                    if (st.equals("End")) {
                        break;
                    }
                    System.out.println("\t" + st);
                }
            }
            str2 = din.readUTF();
            System.out.println("Server : " + str2);
        }
        dout.flush();
        dout.close();
        s.close();
    }
}
