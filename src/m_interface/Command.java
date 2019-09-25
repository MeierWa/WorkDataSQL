package m_interface;

/**命令接收者
*/
public interface Command
{
	/**
	 * 执行
	 */
	 void execute();
	 /**
	 * 撤销
	 */
	void undo();
}
