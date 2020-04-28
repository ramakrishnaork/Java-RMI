import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.RemoteRef;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CheckAgent extends Thread {
	Beacon B = new Beacon(0, 0, null, 0);
	static List<Beacon> agList = new ArrayList<>();
	
	public void run(){
		System.out.println("Agent check has started");
		while (true){
			try {
				agentCheck();
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void add(Beacon B){
		B.LastTime = (int) (System.currentTimeMillis()/1000);
		agList.add(B);
		System.out.println("New agent added! Agent id: " + B.id);
		System.out.println("Number of agents: " + agList.size());
		CallLocalOSTime(B);
	}
	public void CallLocalOSTime(Beacon B){
		try {
			CmdAgentImpl CMA = new CmdAgentImpl();
			
			GetLocalTime GLT = new GetLocalTime();	
			//System.out.println("Calling c local time");
			GLT = (GetLocalTime)CMA.execute("GetLocalTime", GLT);
			System.out.println("Local Time received: " + GLT.tyme);
			//System.out.println(GLT.valid);
			
			GetLocalOS GLO = new GetLocalOS();
			//System.out.println("Calling c local os");
			GLO = (GetLocalOS)CMA.execute("GetLocalOS", GLO);
			if (GLO.version == 1)
				System.out.println("OS Name received: Windows");
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void agentCheck(){
		int curtime = (int) (System.currentTimeMillis()/1000);
		Iterator<Beacon> it = agList.iterator();
		//System.out.println("agentCheck println" + " " + agList.size());
		while (it.hasNext()){
			B = (Beacon) it.next();
			//System.out.println("curtime: " + curtime + "lasttime: " + B.LastTime);
			if(curtime - B.LastTime > 60){
				System.out.println("Agent " + B.id + " is dead ");
				it.remove();
				System.out.println("New list size: " + agList.size());
			}
		}
		//System.out.print("Size"+agList.size());
		/*for(Beacon B:agList){
			System.out.println("curtime: " + curtime + "lasttime: " + B.LastTime);
		}*/
	}

}
