package m_interface;
import modle.*;

/**
 * @author  mewCu
 * @description 交互接口
 * @date  2019/9/24 19:53
 * @modified
 */
public interface InterReactive
{
	void findModel(String model);//查找model
	void addModel(String model);//添加model
	
	void addProcedure(String sp,String sc);//添加工序(工序名称,颜色)
	void modifyModel(String newModel);//修改mod名称
	void deleteModel();
	
	void addSize(String s);//添加尺码
	void modifyProcedure(String newProcedure,String newColor);//修改工序
	void deleteProcedure();//删除工序
	
	void deleteSize(String s);//删除尺码
	
	WorkData selectModel(int index);//选择一个存在的model
	Procedure selectProcedure(int index);//选择工序
	
	void clearSize();//清除size
	
	void addDescription(String des);//添加描述
	void deleteDescription();//删除描述
	
}

