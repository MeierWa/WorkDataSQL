package command;/**
 * @author Administrator
 * @description
 * @date 2019/9/24 20:46
 * @modified
 */

import m_interface.Command;
import middleware.DataContainer;

/**
 *@ClassName MindCommand
 *@Description 更好用的Command
 *@Author mewCu
 *Date 2019/9/24 20:46
 */
public abstract class MindCommand implements Command {

    private DataContainer dc=null;
    private String model="";

    public MindCommand(String model, DataContainer dc){
        this.dc=dc;
        this.model=model;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public DataContainer getDc() {
        return dc;
    }

    public void setDc(DataContainer dc) {
        this.dc = dc;
    }
}
