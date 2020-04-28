import java.rmi.RemoteException;
import java.util.ArrayList;

public class BeaconListenerImpl extends java.rmi.server.UnicastRemoteObject implements BeaconListener  {

	protected BeaconListenerImpl() throws RemoteException {
		super();
	}
	CheckAgent CA = new CheckAgent();
	public ArrayList<Beacon> listagent = new ArrayList<>();
	
	public int deposit (Beacon B) throws RemoteException {
		
		CA.add(B);
		
		return 0;
		
	}
}
