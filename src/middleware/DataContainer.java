package middleware;
import m_interface.*;
import modle.*;
import java.util.*;

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
		return null;
	}

	@Override
	public WorkData delete(String src_modle)
	{
		// TODO: Implement this method
		return null;
	}

	@Override
	public WorkData delete(String src_modle, String src_procedure)
	{
		// TODO: Implement this method
		return null;
	}

	@Override
	public WorkData delete(String src_modle, String src_procedure, String src_size)
	{
		// TODO: Implement this method
		return null;
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
