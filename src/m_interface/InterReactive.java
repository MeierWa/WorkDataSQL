package m_interface;
import modle.*;

/**
 * @author  mewCu
 * @description 和操作人员交互
 * @date  2019/9/24 19:53
 * @modified
 */
public interface InterReactive
{
	void findModel(String model);//查找model
	void addModel(String model);//添加model
	void selectProcedure(int index);//选择工序
	void addProcedure(String sp,String sc);//添加工序(工序名称,颜色)
	void addSize(String s);//添加尺码
	void deleteSize(String s);//删除尺码
	void deleteProcedure(String sp,)
	void addDescription(String des);//添加描述
}
