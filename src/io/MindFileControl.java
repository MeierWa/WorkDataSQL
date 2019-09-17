package io;
import java.io.*;
import modle.*;

import java.nio.charset.StandardCharsets;
import java.util.*;
import middleware.*;
import tool.*;
import org.json.*;

/**MindFileControl 文件控制
*创建、读取、写入
*/
public class MindFileControl
{
	
	public final static int CREATE_ERROR=-1,CREATE_EXIST=1,CREATE_SUCCESS=2;//错误-1，存在1,成功2
	public final static int READ_NOEXIST=-1,READ_NOMATCH=1,READ_SUCCESS=2;
	public final static int WRITE_ERROR=-1,WRITE_NOMACTH=1,WRITE_SUCCESS=2;
	
	public static String path="";//不存在-1，不匹配1,成功2
	
	
	
	/**文件读取
	*
	*/
	public int readFile ( String path, String fileName, DataHelper helper,ArrayList<JSONObject> al) throws JSONException, IOException{
		MindFileControl.path =path+"/"+fileName;
		File f = new File(path+"/"+fileName);
		File bat_f = new File(path+"/"+ String.copyValueOf(fileName.toCharArray(),0,fileName.lastIndexOf("."))+".bat");
		if(!f.exists()){
			if(bat_f.exists()){
				f=bat_f;
			}else{
			return READ_NOEXIST;}
		}
		if(al==null){
			return READ_NOMATCH;
		}
		FileInputStream fis;
		InputStreamReader isr;
			fis=new FileInputStream(f);
			isr=new InputStreamReader(fis, StandardCharsets.UTF_8);
			//格式是否匹配
			int len=0;
			char[] fileHead=new char[8];
			if ((len=isr.read(fileHead, 0, 8)) != -1)
			{
				if(fileHead[0]=='C'&&fileHead[1]=='u'){
					helper.setData(fileHead);
				}else{
					return READ_NOMATCH;
				}
			}
			//读取单元数据(读取单元大小，读取json)
			for(int i=0;i<helper.getElementNum();i++){
				
				char[] bs=new char[4];
				if((len=isr.read(bs,0,4))!=-1){
					int size=SixtyToTen.SixtyToTen(String.valueOf(bs));
					//读json
					char[] data=new char[size];
					isr.read(data,0,size);
					String js=String.valueOf(data);
					al.add(new JSONObject(js));
				}
			}
			
			
			fis.close();
		
		
		return READ_SUCCESS;
	}
	
	/*
	**文件写入
	*/
	public int write(ArrayList<WorkData> src_wds) throws Exception{
		String str_bat_file= String.copyValueOf(path.toCharArray(),0,path.lastIndexOf("."));
		//创建bat文件
		File f = new File(path);
		File f_out = new File(str_bat_file+".bat");
		if (!f_out.exists()){
			f_out.createNewFile();
		}
		FileInputStream fis;
		InputStreamReader isr;
		FileOutputStream fos;
		OutputStreamWriter osw;
		try
		{
			fis = new FileInputStream(f);
			isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
			fos = new FileOutputStream(f_out);
			osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
			char[] datas=new char[1024*50];
			
			int len=0;
			while((len=isr.read(datas))!=-1){
				osw.write(datas,0,len);
			}
			//逆序关闭
			osw.flush();
			osw.close();
			fos.close();
			isr.close();
			fis.close();
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}finally{
			
		}
		//从建文件
		f=new File(path);
		
		f.createNewFile();
		//文件头创建
		char[] fileHead = FileHeadCreater.toFileHead(src_wds.size());
			fos = new FileOutputStream(f);
			osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
			//文件头写入
			osw.write(fileHead);
			//写入jos源数据
			
			StringBuilder ss=new StringBuilder();
			for(WorkData wd:src_wds){
				ss.append(SixtyToTen.TenToSixty(134));
				int n=4-ss.length();
				for(int i=0;i<n;i++){
					ss.append("0");
				}
				ss.reverse();
				osw.write(ss.toString());
				osw.write(wd.toString());
			}
			//关闭
			osw.flush();
			osw.close();
			fos.close();
		
		
		//验证文件
		return WRITE_SUCCESS;
	}
	
	
}
