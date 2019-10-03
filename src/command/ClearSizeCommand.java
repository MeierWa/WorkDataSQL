package command;/**
 * @author mewCu
 * @description
 * @date 2019/10/1 21:16
 * @modified
 */

import middleware.DataContainer;
import modle.Procedure;

/**
 *@ClassName ClearSizeCommand
 *@Description 清空size
 *@Author mewCu
 *Date 2019/10/1 21:16
 */
public class ClearSizeCommand extends BaseMindCommand {

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

    private String model="";
    private String procedure="";
    private String color="";

    public String getSaveSize() {
        return saveSize;
    }

    public void setSaveSize(String saveSize) {
        this.saveSize = saveSize;
    }

    private String saveSize="";

    @Override
    public void execute() {
        Procedure pc=getDc().find(getModel(),getProcedure(),getColor());
        if(pc!=null){
            setSaveSize(pc.getSize());
        }
        if(getDc().clearSize(getModel(),getProcedure(),getColor())!=null){
            setCanUndo(true);
        }
    }

    @Override
    public void undo() {
        if(!isCanUndo()){
            return;
        }
        Procedure pc=getDc().find(getModel(),getProcedure(),getColor());
        pc.setSize(getSaveSize());
        setCanUndo(false);
    }

    public  ClearSizeCommand(String model, String procedure, String color, DataContainer dc){
        super(dc);
        this.model=model;
        this.procedure=procedure;
        this.color=color;
    }
}
