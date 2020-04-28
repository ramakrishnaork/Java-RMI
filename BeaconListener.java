import java.rmi.RemoteException;

public interface BeaconListener extends java.rmi.Remote{
		public int deposit(Beacon B) throws RemoteException;
	}
