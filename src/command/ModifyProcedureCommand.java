package command;

import middleware.DataContainer;
import modle.Procedure;

public class ModifyProcedureCommand extends BaseMindCommand
{

	private String model="";
	private String oldProcedure="";
	private String oldColor="";
	private String newProcedure="";
	private String newColor="";

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getOldProcedure() {
		return oldProcedure;
	}

	public void setOldProcedure(String oldProcedure) {
		this.oldProcedure = oldProcedure;
	}

	public String getNewProcedure() {
		return newProcedure;
	}

	public void setNewProcedure(String newProcedure) {
		this.newProcedure = newProcedure;
	}

	public String getNewColor() {
		return newColor;
	}

	public void setNewColor(String newColor) {
		this.newColor = newColor;
	}


	public String getOldColor() {
		return oldColor;
	}

	public void setOldColor(String oldColor) {
		this.oldColor = oldColor;
	}
	@Override
	public void execute()
	{
		// TODO: Implement this method
		if(getDc().find(getModel(),getOldProcedure(),getOldColor())==null){
			return;
		}
		getDc().replace_procedure(getModel(),getOldProcedure(),getNewProcedure(),getOldColor(),getNewColor());
		setCanUndo(true);
	}

	@Override
	public void undo()
	{
		// TODO: Implement this method
		if(!isCanUndo()){
			return;
		}
		getDc().replace_procedure(getModel(),getNewProcedure(),getOldProcedure(),getNewColor(),getOldColor());
	}
	
	
	public ModifyProcedureCommand(String model,String oldProcedure,String oldColor,String newPreocedure,String newColor,DataContainer dc){
		super(dc);
		this.model=model;
		this.oldProcedure=oldProcedure;
		this.oldColor=oldColor;
		this.newProcedure=newPreocedure;
		this.newColor=newColor;
	}
	
}
