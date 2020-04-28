import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.RemoteRef;
import java.time.LocalTime;
import java.util.Random;

public class Agent implements Serializable{
	private static final long serialVersionUID = -122193075766424611L;
	Random rand = new Random();	
	
	public void GenerateBeacon(Beacon B){
		B.id = rand.nextInt(100000);
		B.StartUpTime = (int) (System.currentTimeMillis()/1000);
		B.CmdAgentId = "GetLocalTime";
		//System.out.println(B.id + " " + B.StartUpTime + " " + B.CmdAgentId);
	}
	public static void main(String[] args){
		Beacon B = new Beacon(0, 0, null, 0);
		Agent A = new Agent();
		CheckAgent CA = new CheckAgent();		
		
		try {
			BeaconListenerImpl BLI = new BeaconListenerImpl();
			RemoteRef location = BLI.getRef();
			System.out.println (location.remoteToString());	
			
			String registry = "localhost"; // the registry servers
			if (args.length >=1) { registry = args[0]; }
			
			CmdAgentImpl CMA = new CmdAgentImpl();
			RemoteRef location1 = CMA.getRef();
			System.out.println (location1.remoteToString());
			String registration1 = "rmi://" + registry + "/CmdAgent";
			System.out.println(registration1);
			Naming.rebind(registration1,CMA);
						
			String registration = "rmi://" + registry + "/BeaconListener";
			Remote remoteService = Naming.lookup(registration);
			BeaconListener BLI1 = (BeaconListener) remoteService;
			
			while (true){
				A.GenerateBeacon(B);
				BLI1.deposit(B);
				Thread.sleep(8000);
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
class Beacon implements Serializable{
	
	private static final long serialVersionUID = 115164803490788135L;
	
	int id;
	int StartUpTime;
	String CmdAgentId;
	int LastTime;
	public Beacon(int id, int StartUpTime, String CmdAgentId,int LastTime){
		this.id=id;
		this.StartUpTime = StartUpTime;
		this.CmdAgentId = CmdAgentId;
		this.LastTime = LastTime;
	}
}
