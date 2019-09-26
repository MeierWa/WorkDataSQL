package middleware;
import m_interface.*;
import java.util.*;
import modle.*;
import command.*;

/**
 * @author  mewCu
 * @description 让用户操作与数据交互相契
 * @date  2019/9/26	21:00
 * @modified 
 */

public class DataAdmin implements InterReactive
{

	private ArrayList<Command> commandQueue=null;
	private WorkData selectAction=null;
	private DataContainer dc=null;

	public void setSelectAction(WorkData selectAction)
	{
		this.selectAction = selectAction;
	}

	public WorkData getSelectAction()
	{
		return selectAction;
	}

	@Override
	public void addSize(String s)
	{
		// TODO: Implement this method

	}

    @Override
    public void addProcedure(String sp, String sc)
    {
        // TODO: Implement this method
		WorkData wd=getSelectAction();
		Command c=new AddProcedureCommand(wd.getModel(),sp,sc,dc);
		c.execute();
		commandQueue.add(c);
}

	@Override
	public void addModel(String model)
	{
		// TODO: Implement this method
		Command c=new AddModelCommand(model,dc);
		c.execute();
		setSelectAction(dc.find(model));
		commandQueue.add(c);
	}

	@Override
	public void addDescription(String des)
	{
		// TODO: Implement this method
	}

	public DataAdmin(DataContainer dc){
		commandQueue=new ArrayList<Command>();
		this.dc=dc;
	}
	
}
