package middleware;
import io.MindFileControl;
import mindinterface.*;
import java.util.*;
import modle.*;
import command.*;
import thread.WriteDataThread;

/**
 * @author  mewCu
 * @description 让用户操作与数据交互相契
 * @date  2019/9/26	21:00
 * @modified 
 */

public class DataAdmin implements InterReactive
{
	
	private ArrayList<AbstractCommand> commandQueue;
	private WorkData selectAction;
	private Procedure selectProcedure;
	private DataContainer dc;

	public void setSelectAction(WorkData selectAction)
	{
		this.selectAction = selectAction;
	}

	public void setSelectProcedure(Procedure pc){
	    this.selectProcedure=pc;
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
		AbstractCommand cd=new ModifyModelCommand(getSelectAction().getModel(),newModel,dc);
		cd.execute();
		commandQueue.add(cd);
	}

	@Override
	public void deleteModel()
	{
		// TODO: Implement this method
        AbstractCommand cd=new DeleteModelCommand(getSelectAction().getModel(),dc);
        setSelectProcedure(null);
        setSelectAction(null);
        cd.execute();
        commandQueue.add(cd);
	}

	@Override
	public void modifyProcedure(String newProcedure, String newColor)
	{
		// TODO: Implement this method
        AbstractCommand cd=new ModifyProcedureCommand(getSelectAction().getModel(),getSelectProcedure().getName(),getSelectProcedure().getColor(),newProcedure,newColor,dc);
        cd.execute();
        commandQueue.add(cd);
	}

	@Override
	public void deleteProcedure()
	{
		// TODO: Implement this method
        AbstractCommand cd=new DeleteProcedureCommand(getSelectAction().getModel(),getSelectProcedure().getName(),getSelectProcedure().getColor(),dc);
        setSelectProcedure(null);
        cd.execute();
        commandQueue.add(cd);
	}

	@Override
	public void deleteSize(String s)
	{
		// TODO: Implement this method
        AbstractCommand cd=new DeleteSizeCommand(getSelectAction().getModel(),getSelectProcedure().getName(),getSelectProcedure().getColor(),s,dc);
        cd.execute();
        commandQueue.add(cd);
	}

    @Override
    public void modifySize(String oldSize, String newSize) {
        AbstractCommand cd=new ModifySizeCommand(getSelectAction().getModel(),getSelectProcedure().getName(),getSelectProcedure().getColor(),oldSize,newSize,dc);
        cd.execute();
        commandQueue.add(cd);
    }

    @Override
	public WorkData selectModel(int index)
	{
		// TODO: Implement this method
        ArrayList<WorkData> datas=dc.getDatas();
        setSelectAction(datas.get(index));
		return datas.get(index);
	}

	@Override
	public Procedure selectProcedure(int index)
	{
		// TODO: Implement this method
        WorkData wd=getSelectAction();
        List<Procedure> procedures=wd.getProcedures();
        setSelectProcedure(procedures.get(index));
		return procedures.get(index);
	}

	@Override
	public void clearSize()
	{
		// TODO: Implement this method
        AbstractCommand cd=new ClearSizeCommand(getSelectAction().getModel(),getSelectProcedure().getName(),getSelectProcedure().getColor(),dc);
        cd.execute();
        commandQueue.add(cd);
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
        AbstractCommand cd=new AddSizeCommand(getSelectAction().getModel(),getSelectProcedure().getName(),getSelectProcedure().getColor(),s,dc);
        cd.execute();
        commandQueue.add(cd);
	}

    @Override
    public void addProcedure(String sp, String sc)
    {
        // TODO: Implement this method
		WorkData wd=getSelectAction();
		AbstractCommand c=new AddProcedureCommand(wd.getModel(),sp,sc,dc);
		c.execute();
		commandQueue.add(c);
		setSelectProcedure(dc.find(getSelectAction().getModel(),sp,sc));
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
		commandQueue=new ArrayList<>();
		this.dc=dc;
	}

	public Procedure getSelectProcedure() {
		return selectProcedure;
	}

	public void setSelectProcedure(int index) {
		this.selectProcedure = getSelectAction().getProcedures().get(index);
	}

	public void save(MindFileControl mfc){
		new Thread(new WriteDataThread(mfc,dc.getDatas())).start();
	}
}
