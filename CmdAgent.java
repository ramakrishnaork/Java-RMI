import java.rmi.*;

public interface CmdAgent extends java.rmi.Remote
{
	public Object execute(String CmdID, Object CmdObj) throws RemoteException;
}
