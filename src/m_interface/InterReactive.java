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
	void addSize(String s);//添加尺码
	void addProcedure(String sp,String sc);//添加工序(工序名称,颜色)
	void addModel(String model);//添加一个新单子
	void addDescription(String des);//添加描述
}
