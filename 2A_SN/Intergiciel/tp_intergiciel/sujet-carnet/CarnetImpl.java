import java.net.InetAddress;
import java.net.MalformedURLException;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.*;
import java.util.*;

public class CarnetImpl extends UnicastRemoteObject implements Carnet {
    private Map<String, RFiche> agenda;
    private int nCarnet;
    private Carnet other;

    public CarnetImpl(int n) throws RemoteException {
        this.agenda = new HashMap<>();
        this.nCarnet = n;
    }

    @Override
    public void Ajouter(SFiche sf) throws RemoteException {
        try {
            RFiche rf = new RFicheImpl(sf.getNom(), sf.getEmail());
            this.agenda.put(sf.getNom(), rf);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public RFiche Consulter(String n, boolean forward) throws RemoteException {
        RFiche tmp = agenda.get(n);
        if (tmp == null && forward) {
          
                try {
                    other = (Carnet) Naming.lookup("//localhost:4000/Carnet" +String.valueOf((this.nCarnet % 2) + 1));
                    tmp = other.Consulter(n, false);

                } catch (Exception e) {
                    System.out.println(e.toString());
             
            

        } 
    }
    return tmp;

}

public static void main(String[] args) {
    String URL;
    int port=4000;
    Integer i=new Integer(args[0]);
    Integer n=i.intValue();
    if (n!=1 && n!=2){
        System.out.println("erreur");
        
    }
    try{
        Registry registry = LocateRegistry.createRegistry(port);
    }
    catch(Exception ex){

    }
    finally{
        try{
        URL="//"+InetAddress.getLocalHost().getHostName()+":"+port+"/Carnet"+n;
        Naming.rebind(URL,new CarnetImpl(n));

   

}catch (Exception ex){

}
    }
}


}
