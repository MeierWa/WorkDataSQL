package adapter;
import java.util.*;
import modle.*;
import org.json.*;


/**
*用于json和workdata的标志适配
*/
public class FeatureAdapter
{
	
	
	
	public FeatureAdapter(){
		
	}
	
	/*
	**json对象转为workData
	*/
	public WorkData toWorkData(JSONObject jio) throws JSONException{
		WorkData result=new WorkData();
		result.setModel(jio.getString("model"));
		ArrayList<Procedure> pcds=new ArrayList<Procedure>();
		Procedure pcd_temp=null;
		JSONArray temp=jio.getJSONArray("procedures");
		for(int i=0;i<temp.length();i++){
			JSONObject jo_temp=temp.getJSONObject(i);
			pcd_temp=new Procedure(jo_temp.getString("color"));
			pcd_temp.setName(jo_temp.getString("name"));
			pcd_temp.setSize(jo_temp.getString("size"));
			//add
			pcds.add(pcd_temp);
		}
		result.setProcedures(pcds);
		result.setDescription(jio.getString("description"));
		
		return result;
	}
	
	public JSONObject toJSONObject(WorkData work){
		try
		{
			return new JSONObject(work.toString());
		}
		catch (JSONException e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	
}
