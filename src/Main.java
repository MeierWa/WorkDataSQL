import java.util.*;
import io.*;
import java.io.*;
import middleware.*;
import modle.*;
import thread.*;
import adapter.*;

public class Main
{
	public static void main(String[] args)
	{
	
		Scanner input = new Scanner(System.in);

		System.out.println(">>>>文件验证...");
		MindFileControl mfc = null;
		File f1=new File(CP.pathName_pc+"\\"+ CP.fileName);
		File f2=new File(CP.pathName_phone+"/"+ CP.fileName);
		if(f1.exists()){
			mfc=new MindFileControl(f1.getAbsolutePath());
		}else {
			mfc=new MindFileControl(f2.getAbsolutePath());
		}
		DataHelper dh=new DataHelper();
		ArrayList<WorkData> workDatas=new ArrayList<WorkData>();
		FeatureAdapter adp=new FeatureAdapter();
		//读取数据
		new Thread(new ReadDataThread(mfc,workDatas,dh,adp)).start();
		
		try
		{
			Thread.sleep(1000);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		
		System.out.println("mind____"+adp.toJSONObject(workDatas.get(0)));
		System.out.println("文件标识符："+Arrays.toString(dh.getIdentifier()));
		System.out.println();
		MindPrint mp=new MindPrint(workDatas);
		mp.print();
		
		while(true){
			
		System.out.println("输入1保存");
		int ac=0;
		ac=input.nextInt();
		if(ac==1){
			System.out.println("正在保存");
			new Thread(new WriteDataThread(mfc,workDatas)).start();
		}
		
		}
	}
}
