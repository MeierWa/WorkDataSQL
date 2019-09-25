package command;/**
 * @author Administrator
 * @description
 * @date 2019/9/24 20:46
 * @modified
 */

import m_interface.Command;
import middleware.DataContainer;
import modle.WorkData;

/**
 *@ClassName MindCommand
 *@Description 更好用的Command
 *@Author mewCu
 *Date 2019/9/24 20:46
 */
public abstract class MindCommand implements Command {

    private DataContainer dc=null;

    public MindCommand(DataContainer dc){
        this.dc=dc;
    }

    public DataContainer getDc() {
        return dc;
    }

    public void setDc(DataContainer dc) {
        this.dc = dc;
    }


}
