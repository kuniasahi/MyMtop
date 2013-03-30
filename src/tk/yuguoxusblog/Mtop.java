package tk.yuguoxusblog;

import com.android.ddmlib.AndroidDebugBridge;
import com.android.ddmlib.Client;
import com.android.ddmlib.ClientData;
import com.android.ddmlib.IDevice;
import com.android.ddmlib.Log;

import java.util.List;
import java.lang.Thread;
import java.util.ArrayList;

public class Mtop{
	
	public AndroidDebugBridge adb;
	private boolean mExit;

    boolean wait_for_connection() {
		int i;
		for (i = 0; i < 5; i++) {
			if (adb.isConnected()) {
				return true;
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public Mtop(){
		AndroidDebugBridge.init(true);
	}
	
	public void connect() {
		int i, client_number;
		IDevice device_array[];
		Client cll[];
		ClientData cd;
		System.out.println("AndroidDebugBridge initialized\n");
		adb = AndroidDebugBridge.createBridge();
		if (adb == null) {
			System.out.println("createBridge() failed\n");
			return;
		} 
		if (wait_for_connection()) {
			client_number = -1;
			System.out.println("adb is connected\n");
			device_array = adb.getDevices();
			for (IDevice d : device_array) {
				System.out.println(d.toString());
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				cll = d.getClients();
				for (Client c : cll) {
					cd = c.getClientData();
					System.out.println("client:" + c.toString());
					System.out.println("client:"
							+ cd.getClientDescription());
				}
			}
		} else {
			System.out.println("adb is not connected\n");
		}
		while(!mExit){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		AndroidDebugBridge.disconnectBridge();
		//AndroidDebugBridge.terminate();
		System.out.println("AndroidDebugBridge terminated\n");
	}
	
	public void disconnect(){
		mExit = true;
	}
	public ArrayList<String> getTotalProcess(){
		IDevice device_array[] = adb.getDevices();
		Client cll[];
		ClientData cd;
		ArrayList ls = new ArrayList();
		if(adb!=null){
			device_array = adb.getDevices();
			for (IDevice d : device_array) {
				System.out.println(d.toString());
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				cll = d.getClients();
				for (Client c : cll) {
					cd = c.getClientData();
					System.out.println(cd.getClientDescription());
					ls.add(cd.getClientDescription());
				}
			}
			return ls;
		}
		return null; 
	}
}	
