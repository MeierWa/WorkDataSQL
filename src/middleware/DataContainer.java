package middleware;
import m_interface.*;
import modle.*;
import java.util.*;
import java.util.regex.*;

/**DataContainer 数据仓库
*系统在线时数据的容器
*系统离线时也可操作(提供命令设计模式接口)
*/

public class DataContainer implements DataSuperviseInterface
{

	@Override
	public WorkData add(String src_modle)
	{
		// TODO: Implement this method
		WorkData data=new WorkData();
		data.setModel(src_modle);
		datas.add(data);
		return data;
	}

	@Override
	public WorkData add(String src_modle, String src_procedure,String src_procedure_color)
	{
		// TODO: Implement this method
		WorkData data=find(src_modle);
		ArrayList<Procedure> procedures=null;
		if(data!=null){
			procedures=data.getProcedures();
			if(procedures!=null){
				procedures.add(new Procedure(src_procedure,src_procedure_color));
			}else{
				procedures=new ArrayList<Procedure>();
				procedures.add(new Procedure(src_procedure,src_procedure_color));
			}
		}else{
			data=new WorkData();
			data.setModel(src_modle);
			procedures=new ArrayList<Procedure>();
			procedures.add(new Procedure(src_procedure,src_procedure_color));
		}
		data.setProcedures(procedures);
		datas.add(data);
		return data;
	}

	@Override
	public WorkData add(String src_modle, String src_procedure,String src_procedure_color, String src_size)
	{
		// TODO: Implement this method
		//尺码用下横线_隔开
		WorkData data=find(src_modle);
		ArrayList<Procedure> procedures=null;
		Procedure pd=null;
		if(data!=null){
			procedures=data.getProcedures();
			pd=find(src_modle,src_procedure,src_procedure_color);
			if(procedures!=null){
				if(pd!=null){
					pd.setSize(pd.getSize()+"_"+src_size);//添加成功(最长距离)
				}else{
					pd=new Procedure(src_procedure,src_procedure_color);
					pd.setSize(src_size);
				}
				procedures.add(pd);
			}else{
				procedures=new ArrayList<Procedure>();
				pd=new Procedure(src_procedure,src_procedure_color);
				pd.setSize(src_size);
				procedures.add(pd);
			}
		}else{
			data=new WorkData();
			data.setModel(src_modle);
			procedures=new ArrayList<Procedure>();
			pd=new Procedure(src_procedure,src_procedure_color);
			pd.setSize(src_size);
			procedures.add(pd);
		}
		data.setProcedures(procedures);
		datas.add(data);
		return data;
	}

        /** @author mewCu
        * @Description
        * @Date 19:15 2019/9/25
        * @Param [dst_wd]
        * @Return modle.WorkData
        **/

    @Override
    public WorkData add(WorkData dst_wd) {
        WorkData wd=null;
        datas.add(dst_wd);

        return datas.get(datas.size()-1);
    }

    @Override
	public WorkData delete(String src_modle)
	{
		// TODO: Implement this method
		WorkData wd=null;
		for(WorkData wdt:datas){
			if(wdt.getModel().equals(src_modle)){
				wd=wdt;
				datas.remove(wdt);
				break;
			}
		}
		return wd;
	}

	@Override
	public Procedure delete(String src_modle, String src_procedure,String src_color)
	{
		// TODO: Implement this method
		WorkData wd=find(src_modle);
		if(wd==null){
			return null;
		}else {
			System.out.println("no null");
		}
		Procedure pd=find(src_modle,src_procedure,src_color);
		if(pd!=null){
			wd.getProcedures().remove(pd);//删除
			System.out.println("yes");
			System.out.println(wd.getProcedures().size());
		}else {
			System.out.println("pd  null");
		}

		
		return pd;
	}

	@Override
	public Procedure delete(String src_modle, String src_procedure,String src_color, String src_size)
	{
		// TODO: Implement this method
		WorkData wd=find(src_modle);
		Pattern pattern=null;
		Matcher matcher=null;
		StringBuilder rex=new StringBuilder();
		if(wd==null){
			return null;
		}
		Procedure pd=find(src_modle,src_procedure,src_color);
		if(pd!=null){
			//匹配size "[1-9][0-9]*([.][5])?[a-zA-Z]?"
			String sc=pd.getColor();
			if(sc.contains(src_color)){
				//生成rex
				for(int i=0;i<src_color.length();i++){
					rex.append("[");
					rex.append(src_color.charAt(i));
					rex.append("]");
				}
				rex.append("[a-zA-Z]?");
				//找一个group
				pattern=Pattern.compile(rex.toString());
				matcher=pattern.matcher(sc);
				//替换原字符
				if(matcher.find()){
					sc.replace(matcher.group(),"");
				}
			}
		}

		return pd;
	}

	@Override
	public WorkData find(String modle)
	{
		// TODO: Implement this method
		WorkData data=null;
		for(WorkData wd:datas){
			if(wd.getModel().equals(modle)){
				data=wd;
				break;
			}
		}
		return data;
	}

	@Override
	public Procedure find(String modle, String procedure,String color)
	{
		// TODO: Implement this method
		WorkData wd=find(modle);
		Procedure pd=null;
		if(wd!=null&&wd.getProcedures()!=null){
			 for(Procedure p:wd.getProcedures()){
				 if(p.getName().equals(procedure)&&p.getColor().equals(color)){
					 pd=p;
					 break;
				 }
			 }
			
			return pd;
		}else{
			return null;
		}
	}
	
	@Override
	public WorkData replace_model(String oldModel, String newModel)
	{
		// TODO: Implement this method
		return null;
	}

	@Override
	public WorkData replace_procedure(String model, String oldProcedure, String newProcedure)
	{
		// TODO: Implement this method
		return null;
	}
	
	
	private ArrayList<WorkData> datas=null;
	

	
	
	
	public DataContainer(ArrayList<WorkData> datas){
		this.datas=datas;
	}
	
	
	
}
