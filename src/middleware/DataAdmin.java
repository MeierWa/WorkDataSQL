package middleware;
import m_interface.*;
import java.util.*;

/**
 * @author  mewCu
 * @description
 * @date  2019/9/24 19:52
 * @modified 
 */

public class DataAdmin implements InterReactive
{

	private ArrayList<Command> commandQueue=null;

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
