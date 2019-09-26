package command;/**
 * @author mewCu
 * @description
 * @date 2019/9/25 21:24
 * @modified
 */

import middleware.DataContainer;
import modle.*;

/**
 *@ClassName DeleteProcedureCommand
 *@Description 删除
 *@Author mewCu
 *Date 2019/9/25 21:24
 */
public class DeleteProcedureCommand extends MindCommand {

    private String model="";
    private String procedure="";
    private String color="";
	private Procedure saveProcedure=null;

    @Override
    public String toString() {
        return "";
    }

    @Override
    public void execute() {
        if(getDc().find(getModel(),getProcedure(),getColor())!=null) {
            saveProcedure=getDc().delete(getModel(), getProcedure(), getColor());
			
        }
    }

    @Override
    public void undo() {
		if(saveProcedure==null){
			return;
		}
        getDc().add(getModel(),saveProcedure);
    }

    public DeleteProcedureCommand(String model,String procedure,String color,DataContainer dc) {
        super(dc);
        this.model=model;
        this.procedure=procedure;
        this.color=color;
    }


    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getProcedure() {
        return procedure;
    }

    public void setProcedure(String procedure) {
        this.procedure = procedure;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
