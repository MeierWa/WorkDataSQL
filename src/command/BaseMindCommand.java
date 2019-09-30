package command;/**
 * @author Administrator
 * @description
 * @date 2019/9/24 20:46
 * @modified
 */

import mindinterface.AbstractCommand;
import middleware.DataContainer;

/**
 *@ClassName MindCommand
 *@Description 更好用的Command
 *@Author mewCu
 *Date 2019/9/24 20:46
 */
public abstract class BaseMindCommand implements AbstractCommand {

    private DataContainer dc=null;
	private boolean canUndo;

    public BaseMindCommand(DataContainer dc){
        this.dc=dc;
    }

	public void setCanUndo(boolean canUndo)
	{
		this.canUndo = canUndo;
	}

	public boolean isCanUndo()
	{
		return canUndo;
	}

    public DataContainer getDc() {
        return dc;
    }

    public void setDc(DataContainer dc) {
        this.dc = dc;
		canUndo=false;
    }


}
