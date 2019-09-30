package middleware;
import mindinterface.*;
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
	
	private ArrayList<AbstractCommand> commandQueue=null;
	private WorkData selectAction=null;
	private Procedure selecteProcedure=null;
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
	public void findModel(String model)
	{
		// TODO: Implement this method
		WorkData wd=dc.find(model);
		setSelectAction(wd);
	}

	@Override
	public void modifyModel(String newModel)
	{
		// TODO: Implement this method
		//Command cd=new
	}

	@Override
	public void deleteModel()
	{
		// TODO: Implement this method
	}

	@Override
	public void modifyProcedure(String newProcedure, String newColor)
	{
		// TODO: Implement this method
	}

	@Override
	public void deleteProcedure()
	{
		// TODO: Implement this method
	}

	@Override
	public void deleteSize(String s)
	{
		// TODO: Implement this method
	}

	@Override
	public WorkData selectModel(int index)
	{
		// TODO: Implement this method
		return null;
	}

	@Override
	public Procedure selectProcedure(int index)
	{
		// TODO: Implement this method
		return null;
	}

	@Override
	public void clearSize()
	{
		// TODO: Implement this method
	}

	@Override
	public void deleteDescription()
	{
		// TODO: Implement this method
		AbstractCommand c=new DeleteDescriptionCommand(getSelectAction().getModel(),dc);
		c.execute();
		commandQueue.add(c);
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
		AbstractCommand c=new AddProcedureCommand(wd.getModel(),sp,sc,dc);
		c.execute();
		commandQueue.add(c);
}

	@Override
	public void addModel(String model)
	{
		// TODO: Implement this method
		AbstractCommand c=new AddModelCommand(model,dc);
		c.execute();
		setSelectAction(dc.find(model));
		commandQueue.add(c);
	}

	@Override
	public void addDescription(String des)
	{
		// TODO: Implement this method
		AbstractCommand c=new AddDescriptionCommand(getSelectAction().getModel(),des,dc);
		c.execute();
		commandQueue.add(c);
	}

	public DataAdmin(DataContainer dc){
		commandQueue=new ArrayList<AbstractCommand>();
		this.dc=dc;
	}

	public Procedure getSelecteProcedure() {
		return selecteProcedure;
	}

	public void setSelecteProcedure(int index) {
		this.selecteProcedure = getSelectAction().getProcedures().get(index);
	}
}
