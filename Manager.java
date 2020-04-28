import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.RemoteRef;
import java.util.ArrayList;

public class Manager{
	
	public static void main(String[] args) {	
		Beacon B = new Beacon(0, 0, null, 0);
		CheckAgent CA = new CheckAgent();
		try {
			
			BeaconListenerImpl BLI = new BeaconListenerImpl();
			RemoteRef location = BLI.getRef();
			System.out.println (location.remoteToString());
			String registry = "localhost"; // where the registry server locates
			if (args.length >=1) {
				registry = args[0];
			   	}
			String registration = "rmi://" + registry + "/BeaconListener";
			Naming.rebind(registration,BLI);
			
			CA.start();
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}	
}