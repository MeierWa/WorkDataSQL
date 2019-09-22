package m_interface;

/**命令接收者
*/
public interface Command
{
	void execute();//执行
	void undo();//撤销
}
