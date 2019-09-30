
import io.*;
import java.io.*;
import java.util.*;

import middleware.*;
import modle.*;
import thread.*;
import adapter.*;
import command.*;

public class Main
{
	public static void main(String[] args)
	{
	
		Scanner scanner = new Scanner(System.in);

		System.out.println(">>>>文件验证...");
		MindFileControl mfc = null;
		File f1=new File(CP.PATH_NAME_PC+"\\"+ CP.FILE_NAME);
		File f2=new File(CP.PATH_NAME_PHONE+"/"+ CP.FILE_NAME);
		if(f1.exists()){
			mfc=new MindFileControl(f1.getAbsolutePath());
		}else {
			mfc=new MindFileControl(f2.getAbsolutePath());
		}
		DataHelper dh=new DataHelper();
		ArrayList<WorkData> workDatas=new ArrayList<WorkData>();
		FeatureAdapter adp=new FeatureAdapter();
		DataContainer dc=new DataContainer(workDatas);

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
		DataAdmin dataAdmin=new DataAdmin(dc);
		/**AB式流程 A为阶段，B为操作
		 * 00为初始阶段
		 * 11创建model 12选择一个model
		 * 21删除model 22创建procedure 23修改model 24选择一个procedure
		 * 31添加size 32修改procedure 33删除procedure 34清空size
		 * 41删除size
		 */
		int jd=0;
		int action=0;
		while(true){
			int key=jd*10+action;
			switch (key){
				case 0://
					System.out.println("输入1创建model  输入2选择一个model");
					action=scanner.nextInt();
					jd++;
					break;
				case  11://
					System.out.println("输入model名称");
					dataAdmin.addModel(scanner.next());
					action=0;
					break;
				case 12://
					//显示存在model
					for(int i=0;i<workDatas.size();i++){
						System.out.println(""+i+"."+workDatas.get(i).getModel());
					}
					System.out.println("输入model序号");
					dataAdmin.setSelectAction(workDatas.get(scanner.nextInt()));
					action=0;
					break;
				case 10://
					System.out.println("1删除model 2创建procedure 3修改model 4选择一个procedure");
					action=scanner.nextInt();
					jd++;
					break;
				case 21://
					dataAdmin.deleteModel();
					jd=0;
					action=0;
					break;
				case 22:
					System.out.println("输入procedure名称(例如：接里布,黑色）");
					String s=scanner.next();
					int partition=s.indexOf(",");
					if(partition==-1){
						partition=s.indexOf("，");
					}
					System.out.println(partition);
					dataAdmin.addProcedure(s.substring(0,partition),s.substring(partition+1));
					jd++;
					action=0;
					break;
				case 23:
					System.out.println("输入新的model名称");
					dataAdmin.modifyModel(scanner.next());
					jd=1;
					action=0;
					break;
				case 24:
					List<Procedure> pds=dataAdmin.getSelectAction().getProcedures();
					for(int i=0;i<pds.size();i++){
						System.out.println(""+i+"."+pds.get(i).getName()+"_"+pds.get(i).getColor());
					}
					System.out.println("输入procedure序号");
					dataAdmin.setSelecteProcedure(scanner.nextInt());
					jd++;
					action=0;
					break;
				case 20:
					System.out.println("1添加size 2修改procedure 3删除procedure 4清空size");

					break;
				default:
					jd=action=0;
					break;
			}
		}
	}
}
