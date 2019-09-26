package m_interface;
import modle.*;

/**
*数据库的管理
*/
public interface DataSuperviseInterface
{

	WorkData add(String src_modle);//增
	Procedure add(String src_modle,String src_procedure,String src_procedure_color);
	Procedure add(String src_modle,String src_procedure,String src_procedure_color,String src_size);
	WorkData add(WorkData dst_wd);
	Procedure add(String model,Procedure procedure);
	WorkData add(String src_model,String description);
	
	WorkData delete(String src_modle);//删
	Procedure delete(String src_modle,String src_procedure,String src_procedure_color);
	Procedure delete(String src_modle,String src_procedure,String src_color,String src_size);

	WorkData find(String modle);//查
	Procedure find(String modle,String procedure,String color); 
	
	WorkData replace_model(String oldModel,String newModel);//改
	Procedure replace_procedure(String model,String oldProcedure,String newProcedure,String oldColor,String newColor);
	Procedure replace_szie(String model,String procedure,String color,String oldSize,String newSize);
	
	
}
