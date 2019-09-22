package m_interface;
import modle.*;

/**
*数据库的管理
*/
public interface DataSuperviseInterface
{
	
	WorkData add(String src_modle);//增
	WorkData add(String src_modle,String src_procedure,String src_procedure_color);
	WorkData add(String src_modle,String src_procedure,String src_procedure_color,String src_size);
	
	WorkData delete(String src_modle);//删
	WorkData delete(String src_modle,String src_procedure);
	WorkData delete(String src_modle,String src_procedure,String src_size);
	
	WorkData find(String modle);//查
	
	WorkData replace_model(String oldModel,String newModel);//改
	WorkData replace_procedure(String model,String oldProcedure,String newProcedure);
	
	
}
