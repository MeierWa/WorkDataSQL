package modle;
import java.util.*;

/**WorkData 工单
 *型号、(颜色)工序、尺码、描述
 */
 
public class WorkData
{
	
	private String model="";//型号
	private ArrayList<Procedure> procedures=null;//工序
	private String description="";//描述
	
	
	public void setModel(String modle){
		this.model=modle;
	}
	public String getModel(){
		return model;
	}
	
	public void setProcedures(ArrayList<Procedure> pcds){
		procedures=pcds;
	}
	public ArrayList<Procedure> getProcedures(){
		return procedures;
	}
	
	public void setDescription(String des){
		description=des;
	}
	public String getDescription(){
		return description;
	}
	
	public WorkData(){
		
	}

	@Override
	public String toString()
	{
		// TODO: Implement this method
		StringBuilder sb=new StringBuilder();
		sb.append("{");
		sb.append("\"model\":\""+model+"\"");
		if(procedures!=null&&!procedures.isEmpty()){
			sb.append(",\"procedures\""+":");
			//工序
			sb.append("[");
			for(Procedure p:procedures){
				sb.append(p.toString()+",");
			}
			sb.deleteCharAt(sb.length()-1);//结尾多了一个逗号
			sb.append("]");
		}
		sb.append(",\"description\":\""+description+"\"");
		sb.append("}");
		return sb.toString();
	}
	
	
	
	
}
