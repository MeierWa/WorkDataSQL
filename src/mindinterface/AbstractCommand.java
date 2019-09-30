package mindinterface;

/**
 * @author  mewCu
 * @description 命令
 * @date  2019/9/30 19:56
 * @modified
 */
public interface AbstractCommand
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
