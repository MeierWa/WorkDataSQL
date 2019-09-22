package thread;
import io.*;
import java.util.*;
import modle.*;
import java.io.*;

public class WriteDataThread implements Runnable
{
	
	private MindFileControl mfc=null;
	private ArrayList<WorkData> wds=null;
	
	public WriteDataThread(MindFileControl mfc,ArrayList<WorkData> wds){
		this.mfc=mfc;
		this.wds=wds;
	}

	@Override
	public void run()
	{
		// TODO: Implement this method
		try
		{
			mfc.write(wds);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	
	
}
