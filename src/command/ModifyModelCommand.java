package command;
import middleware.*;
import modle.*;

public class ModifyModelCommand extends BaseMindCommand
{
	
	private String oldModel="";
	private String newModel="";

	public void setOldModel(String oldModel)
	{
		this.oldModel = oldModel;
	}

	public String getOldModel()
	{
		return oldModel;
	}

	public void setNewModel(String newModel)
	{
		this.newModel = newModel;
	}

	public String getNewModel()
	{
		return newModel;
	}

	@Override
	public void execute()
	{
		// TODO: Implement this method
		WorkData wd=getDc().find(getOldModel());
		if(wd!=null){
			wd.setModel(getNewModel());
			setCanUndo(true);
		}
	}

	@Override
	public void undo()
	{
		// TODO: Implement this method
		WorkData wd=getDc().find(getNewModel());
		if(isCanUndo()&&wd!=null){
			wd.setModel(getOldModel());
		}
	}
	
	
	public ModifyModelCommand(String oldModel,String newModel,DataContainer dc){
		super(dc);
		this.oldModel=oldModel;
		this.newModel=newModel;
	}
	
}
