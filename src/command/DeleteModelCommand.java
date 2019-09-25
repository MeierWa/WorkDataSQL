package command;/**
 * @author Administrator
 * @description
 * @date 2019/9/24 20:43
 * @modified
 */

import m_interface.Command;
import middleware.DataContainer;
import modle.WorkData;

/**
 * @ClassName DeleteModelCommand
 * @Description 删除命令
 * @Author mewCu
 * Date 2019/9/24 20:43
 */
public class DeleteModelCommand extends MindCommand{

    private WorkData wd_save=null;

    @Override
    public String toString() {
        return  "";
    }

    private void save() {
        wd_save=getDc().find(getModel());
    }

    @Override
    public void execute() {
        this.save();
        getDc().delete(getModel());
    }

    @Override
    public void undo() {
        //Throw a Exception
        getDc().add(wd_save);
    }

    public DeleteModelCommand(String model, DataContainer dc) {
        super(model, dc);
    }

}
