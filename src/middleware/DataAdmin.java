package middleware;
import m_interface.*;
import java.util.*;
import modle.*;
import command.*;

/**
 * @author  mewCu
 * @description
 * @date  2019/9/24 19:52
 * @modified 
 */

public class DataAdmin implements InterReactive
{

	private ArrayList<Command> commandQueue=null;
	private WorkData selectAction=null;
	private DataContainer dc=null;

	public void setDataContainer(DataContainer dc)
	{
		this.dc = dc;
	}

	public DataContainer getDataContainer()
	{
		return dc;
	}

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
		if(wd!=null){
			Command c=new AddProcedureCommand(wd.getModel(),sp,sc,getDataContainer());
			c.execute();
			commandQueue.add(c);
		}
    }

	@Override
	public void addModel(String model)
	{
		// TODO: Implement this method
		Command c=new AddModelCommand(model,getDataContainer());
		c.execute();
		setSelectAction(getDataContainer().find(model));
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
		initData();
	}
	
	
	private void initData(){
		
	}
	
}
