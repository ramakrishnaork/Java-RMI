import java.rmi.RemoteException;

public class CmdAgentImpl extends java.rmi.server.UnicastRemoteObject implements CmdAgent {

	static {
    System.loadLibrary("libAgentC");
	}
	
	protected CmdAgentImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}
	public native Object C_GetLocalTime(Object obj);
	public native Object C_GetLocalOS(Object obj);
		
	public Object execute(String CmdId, Object CmbObj) {
		if (CmdId.equals("GetLocalTime")){
			GetLocalTime GLT = new GetLocalTime();
			//System.out.println("Calling c function for getlocaltime");
			return C_GetLocalTime(GLT);
		}
		else if (CmdId.equals("GetLocalOS")){
			GetLocalOS GLO = new GetLocalOS();
			//System.out.println("Calling c function for getlocalos");
			return C_GetLocalOS(GLO);
		}
		return null;
	}
}
