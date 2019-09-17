package tool;

public class FileHeadCreater
{
	
	/*创建文件头
	**
	*/
	public static final char[] toFileHead(int element_num){
		char[] fh=new char[8];
		//标识符Cu
		fh[0]='C';
		fh[1]='u';
		//文件版本号
		fh[2]='1';
		//单元数量
		StringBuilder num=new StringBuilder(SixtyToTen.TenToSixty(element_num));
		if(num.length()<=1){
			num.append("0");
		}
		num.reverse();
		fh[3]=num.charAt(0);
		fh[4]=num.charAt(1);
		//单元类型
		fh[5]='1';
		//2个预留字节
		fh[6]='0';
		fh[7]='0';
		return fh;
	}
	
}
