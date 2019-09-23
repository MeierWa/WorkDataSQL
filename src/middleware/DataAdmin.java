package middleware;
import m_interface.*;
import java.util.*;

/**
*仓库管理员
*/

public class DataAdmin implements CommandExecutor
{

	private ArrayList<Command> commandQueue=null;//命令队列
	
	@Override
	public void addSize(String s)
	{
		// TODO: Implement this method
	}

	@Override
	public void addProcedure(String sp, String sc)
	{
		// TODO: Implement this method
	}

	@Override
	public void addModel(String model)
	{
		// TODO: Implement this method
	}

	@Override
	public void addDescription(String des)
	{
		// TODO: Implement this method
	}
	
	
	public DataAdmin(){
		commandQueue=new ArrayList<Command>();
	}
	
}
