package command;/**
 * @author mewCu
 * @description
 * @date 2019/10/1 21:04
 * @modified
 */

import middleware.DataContainer;

/**
 *@ClassName ModifySizeCommand
 *@Description 修改szie
 *@Author mewCu
 *Date 2019/10/1 21:04
 */
public class ModifySizeCommand extends BaseMindCommand{

    private String model="";
    private String procedure="";
    private String color="";
    private String oldSize="";
    private String newSize="";

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

    public String getOldSize() {
        return oldSize;
    }

    public void setOldSize(String oldSize) {
        this.oldSize = oldSize;
    }

    public String getNewSize() {
        return newSize;
    }

    public void setNewSize(String newSize) {
        this.newSize = newSize;
    }

    @Override
    public void execute() {
        if(getDc().replace_szie(getModel(),getProcedure(),getColor(),getOldSize(),getNewSize())!=null){
            setCanUndo(true);
        }
    }

    @Override
    public void undo() {
        if(!isCanUndo()){
            return;
        }
        getDc().replace_szie(getModel(),getProcedure(),getColor(),getOldSize(),getNewSize());
        setCanUndo(false);
    }

    public ModifySizeCommand(String model, String procedure, String color, String oldSize, String newSize, DataContainer dc){
        super(dc);
        this.model=model;
        this.procedure=procedure;
        this.color=color;
        this.oldSize=oldSize;
        this.newSize=newSize;
    }

}
