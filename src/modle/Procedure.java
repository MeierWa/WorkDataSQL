package modle;

public class Procedure{

	private String color="";
	private String name="";
	private String size="";

	public void setColor(String color)
	{
		this.color = color;
	}

	public String getColor()
	{
		return color;
	}
	
	
	

	public void setName(String name){
		this.name=name;
	}

	public void setSize(String size){
		if(size==""){
			System.out.println("the size hava a value");
		}
		this.size=size;
	}

	public void addSize(String size){
		this.size=this.size+","+size;
	}

	public String getName(){
		return this.name;
	}

	public String getSize(){
		return this.size;
	}

	public Procedure(String name,String color){
		this.name=name;
		this.color=color;
	}
	public Procedure(){}

	@Override
	public String toString()
	{
		// TODO: Implement this method
		StringBuilder sb=new StringBuilder();
		sb.append("{");
		sb.append("\"color\":\""+color+"\"");
		sb.append(",\"name\":\""+name+"\"");
		sb.append(",\"size\":\""+size+"\"");
		sb.append("}");
		return sb.toString();
	}
	
}
