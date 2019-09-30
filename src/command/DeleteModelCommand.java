package command;/**
 * @author Administrator
 * @description
 * @date 2019/9/24 20:43
 * @modified
 */

import middleware.DataContainer;
import modle.WorkData;

/**
 * @ClassName DeleteModelCommand
 * @Description 删除命令
 * @Author mewCu
 * Date 2019/9/24 20:43
 */
public class DeleteModelCommand extends BaseMindCommand {

    private String model="";
    private WorkData wdSave =null;

    @Override
    public String toString() {
        return  "";
    }

    private void save() {
        wdSave=getDc().find(getModel());
    }

    @Override
    public void execute() {
        this.save();
        if(getDc().delete(getModel())!=null){
			setCanUndo(true);
		}
    }

    @Override
    public void undo() {
        //Throw a Exception
		if(!isCanUndo()){
			return;
		}
        getDc().add(wdSave);
    }

    public DeleteModelCommand(String model, DataContainer dc) {
        super(dc);
		setModel(model);
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
