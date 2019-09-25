package tool;

public class SixtyToTen
{
	public static int sixtyToTen(String src){
		char[] chars=src.toUpperCase().toCharArray();
		int result=0;
		for(int i=0;i<chars.length;i++){
			switch(chars[chars.length-1-i]){
				case 'A':
					result+=10*Math.pow(16,i);
					break;
				case 'B':
					result+=11*Math.pow(16,i);
					break;
				case 'C':
					result+=12*Math.pow(16,i);
					break;
				case 'D':
					result+=13*Math.pow(16,i);
					break;
				case 'E':
					result+=14*Math.pow(16,i);
					break;
				case 'F':
					result+=15*Math.pow(16,i);
					break;
				default:
					result+=(chars[chars.length-1-i]-48)*Math.pow(16,i);
				break;
			}
		}
		return result;
	}
	
	public static String TenToSixty(int src){
		StringBuilder result=new StringBuilder();
		int decimal=src;
		while(decimal!=0) {
			int hexvalue=decimal%16;
			switch(hexvalue){
				case 10:
					result.append("a");
					break;
				case 11:
					result.append("b");
					break;
				case 12:
					result.append("c");
					break;
				case 13:
					result.append("d");
					break;
				case 14:
					result.append("e");
					break;
				case 15:
					result.append("f");
					break;
				default:
					result.append(hexvalue);
					break;
			}
		
			decimal/=16;
			}
		
		return result.toString();
	}
	/*
	public static String TenToSixty(int decimal) {
		String hex="";
		while(decimal!=0) {
			int hexvalue=decimal%16;
			hex=tohexchar(hexvalue)+hex;
			decimal/=16;
		}
		return hex;
	}
	public static char tohexchar(int hexvalue) {
		if(hexvalue<=9&&hexvalue>=0)
			return (char)(hexvalue+'0');
		else
			return (char)(hexvalue-10+'A');
		}*/
}
