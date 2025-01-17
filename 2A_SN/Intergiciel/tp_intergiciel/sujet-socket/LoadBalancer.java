import java.net.*;
import java.io.*;
import java.util.Random;

public class LoadBalancer implements Runnable {
    static String hosts[] = {"localhost", "localhost"};
    static int ports[] = {8081,8082};
    static int nbHosts = 2;
    static Random rand = new Random();
    private Socket navSock;
	private static int loadBalancerPort = 8080;
	private static ServerSocket loadbalancerSock;

    private LoadBalancer(Socket s) {
        this.navSock = s;
    }

    public void run(){
        int inputnavLu=0;
        int inputcomLu=0;
        byte[] buffer = new byte[2048];
        try{
            int randomHost = rand.nextInt(nbHosts);
	        Socket commancheSock = new Socket(hosts[randomHost], ports[randomHost]);

            InputStream inputNav=this.navSock.getInputStream();
            OutputStream outputNav=this.navSock.getOutputStream();

            InputStream inputCom=commancheSock.getInputStream();
            OutputStream outputCom=commancheSock.getOutputStream();

            inputnavLu=inputNav.read(buffer);
            outputCom.write(buffer,0,inputnavLu);

            inputcomLu=inputCom.read(buffer);
            outputNav.write(buffer,0,inputcomLu);

            commancheSock.close();
            this.navSock.close();
            

    }catch(IOException e){
        e.printStackTrace();
    }
    }
    public static void main(String[] args) throws IOException {
        try {
			loadbalancerSock = new ServerSocket(loadBalancerPort);
			
			
			while (true) {
				//socket d'écoute
				Socket lBSocket = loadbalancerSock.accept();
                
			
				LoadBalancer a = new LoadBalancer(lBSocket);
                new Thread(a).start();
                
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
}
}
