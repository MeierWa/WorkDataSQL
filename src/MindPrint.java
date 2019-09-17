
import java.util.*;

import modle.Procedure;
import modle.WorkData;

public class MindPrint
{
	
	private List<WorkData> works=null;
	
	public MindPrint(List works){
		this.works=works;
	}
	
	public void print(){
		System.out.println("====工单数据库====");
		System.out.println("单子数量："+works.size());
		for(WorkData wd:works){
			System.out.println(wd.getModel()+"_"+wd.getDescription());
			List<Procedure> ll=wd.getProcedures();
			for(Procedure p:ll){
				System.out.println("___颜色:"+p.getColor()+"_工序:"+p.getName()+"_尺码:"+p.getSize());
			}
		}
	}
	
	
	
}
