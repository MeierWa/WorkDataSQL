package middleware;
import mindinterface.*;
import modle.*;
import java.util.*;
import java.util.regex.*;

/**DataContainer 数据仓库
*系统在线时数据的容器
*系统离线时也可操作(提供命令设计模式接口)
*/

public class DataContainer implements DataSuperviseInterface
{

	private static final boolean ALLOW_REPEAT_MODEL =false;
	private static final boolean ALLOW_REPEAT_PROCEDURE_AND_COLOR =false;

	@Override
	public WorkData add(String src_modle)
	{
		// TODO: Implement this method
		WorkData wd;
		if(!ALLOW_REPEAT_MODEL){
			wd=find(src_modle);
			if(wd!=null){
				return wd;
			}
		}
		WorkData data=new WorkData();
		data.setModel(src_modle);
		datas.add(data);
		return data;
	}

	@Override
	public Procedure add(String src_modle, String src_procedure,String src_procedure_color)
	{
		// TODO: Implement this method
		WorkData data=find(src_modle);
		ArrayList<Procedure> procedures=null;
		boolean addRepeat=false;
		if(data!=null){
			procedures=data.getProcedures();
			if(procedures!=null){
				for(Procedure pd:procedures){
					if(pd.getName().equals(src_procedure)&&pd.getColor().equals(src_procedure_color)){
						addRepeat=true;
						break;
					}
				}
				if(!(addRepeat&&!ALLOW_REPEAT_PROCEDURE_AND_COLOR)){
					procedures.add(new Procedure(src_procedure,src_procedure_color));
				}else {
					return null;
				}
			}else{
				procedures=new ArrayList<Procedure>();
				procedures.add(new Procedure(src_procedure,src_procedure_color));
				data.setProcedures(procedures);
			}
		}else{
			data=new WorkData();
			data.setModel(src_modle);
			procedures=new ArrayList<Procedure>();
			procedures.add(new Procedure(src_procedure,src_procedure_color));
			data.setProcedures(procedures);
			datas.add(data);
		}

		System.out.println(datas.size());
		return find(src_modle,src_procedure,src_procedure_color);
	}

	@Override
	public Procedure add(String src_modle, String src_procedure,String src_procedure_color, String src_size)
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
					//添加成功(最长距离)
					if(pd.getSize().isEmpty()||pd.getSize().length()==0){
						pd.setSize(src_size);
					}else {
						pd.setSize(pd.getSize()+"_"+src_size);
					}
				}else{
					pd=new Procedure(src_procedure,src_procedure_color);
					pd.setSize(src_size);
					procedures.add(pd);
				}
			}else{
				procedures=new ArrayList<Procedure>();
				pd=new Procedure(src_procedure,src_procedure_color);
				pd.setSize(src_size);
				procedures.add(pd);
				data.setProcedures(procedures);
			}
		}else{
			data=new WorkData();
			data.setModel(src_modle);
			procedures=new ArrayList<Procedure>();
			pd=new Procedure(src_procedure,src_procedure_color);
			pd.setSize(src_size);
			procedures.add(pd);
			data.setProcedures(procedures);
			datas.add(data);
		}
		return find(src_modle,src_procedure,src_procedure_color);
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
	public Procedure add(String model, Procedure procedure)
	{
		// TODO: Implement this method
		if(procedure==null){
			return null;
		}
		WorkData wd=find(model);
		if(wd!=null){
			wd.getProcedures().add(procedure);
			return wd.getProcedures().get(wd.getProcedures().size()-1);
		}
		return null;
	}

	@Override
	public WorkData add(String src_model, String description)
	{
		// TODO: Implement this method
		WorkData wd=this.find(src_model);
		if(wd!=null){
			wd.setDescription(description);
			return wd;
		}
		return null;
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
		}
		Procedure pd=find(src_modle,src_procedure,src_color);
		if(pd!=null){
			wd.getProcedures().remove(pd);//删除
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
		String result="",result2="";
		if(wd==null){
			return null;
		}
		Procedure pd=find(src_modle,src_procedure,src_color);
		if(pd!=null){
			//匹配size "[1-9][0-9]*([.][5])?[a-zA-Z]?"
			String ss=pd.getSize();
			if(ss.contains(src_size)){
				//生成rex
				for(int i=0;i<src_size.length();i++){
					rex.append("[");
					rex.append(src_size.charAt(i));
					rex.append("]");
				}
				rex.append("[a-zA-Z]?");
				//找一个group
				pattern=Pattern.compile(rex.toString());
				matcher=pattern.matcher(ss);
				//替换原字符
				if(matcher.find()){
					int st=ss.indexOf(matcher.group());
					int se=st+src_size.length();
					if(st>0){
						result=ss.substring(0,st);
					}else {
						se++;
					}
					if(se<ss.length()){
						result=result+ss.substring(se);
					}else {
						result=result.substring(0,result.length()-1);
					}
					//去双下划线
					matcher=p2.matcher(result);
					if(matcher.find()){
						int st2=result.indexOf(matcher.group());
						int se2=st2+2;
						if(st2>0){
							if(se2<result.length()){
								result2=result.substring(0,st2+1);
								result2=result2+result.substring(se2);
							}else {
								result2=result.substring(0,st2);
							}
						}else {
							result2=result.substring(se2);
						}
						pd.setSize(result2);
					}else {
						pd.setSize(result);
					}

				}else{
					return null;
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
					 return pd;
				 }
			 }
		}
		return null;
	}
	
	@Override
	public WorkData replace_model(String oldModel, String newModel)
	{
		// TODO: Implement this method
       WorkData wd=find(oldModel);
       if(wd==null){
           return  null;
       }
       wd.setModel(newModel);

		return find(newModel);
	}

    @Override
    public Procedure replace_procedure(String model, String oldProcedure, String newProcedure, String oldColor, String newColor) {
        Procedure pd=find(model,oldProcedure,oldColor);
        if(pd==null){
            return null;
        }
        pd.setName(newProcedure);
        pd.setColor(newColor);
        return find(model,newProcedure,newColor);
    }

    @Override
    public Procedure replace_szie(String model, String procedure, String color, String oldSize, String newSize) {
        Procedure pd=find(model,procedure,color);
        Pattern pattern2=null;
        Matcher matcher2=null;
        if(pd==null){
            return null;
        }
        if(!pd.getSize().contains(oldSize)){
            return null;
        }else {
            //确定该size的完整形式和具体位置
            StringBuilder rex=new StringBuilder();
            char[] chars=oldSize.toCharArray();
            for(int i=0;i<chars.length;i++){
                rex.append("["+chars[i]+"]");
            }
            rex.append("[a-zA-Z]?");
            pattern2=Pattern.compile(rex.toString());
            matcher2=pattern2.matcher(pd.getSize());
            if(matcher2.find()){
                //替换
                pd.setSize(pd.getSize().replace(matcher2.group(),newSize));
            }
        }
        return find(model,procedure,color);
    }

	@Override
	public Procedure clearSize(String src_modle, String procedure, String color) {
    	Procedure pc=find(src_modle,procedure,color);
    	if(pc!=null){
    		pc.setSize("");
		}
		return pc;
	}

	private ArrayList<WorkData> datas=null;
    private Pattern p2=null;

    public ArrayList<WorkData> getDatas(){return datas;}

	public DataContainer(ArrayList<WorkData> datas){
		this.datas=datas;
		p2=Pattern.compile("([_][_])+");
	}
}
