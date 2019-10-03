
/**
 * @author mewCu
 * @description
 * @date 2019/10/1 21:45
 * @modified
 */

import adapter.CP;
import adapter.FeatureAdapter;
import io.MindFileControl;
import middleware.DataAdmin;
import middleware.DataContainer;
import middleware.DataHelper;
import modle.Procedure;
import modle.WorkData;
import thread.ReadDataThread;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 *@ClassName InputSystem
 *@Description TODO
 *@Author mewCu
 *Date 2019/10/1 21:45
 */
public class InputSystem {

    private int jd=0;
    private int action=0;
    private Scanner scanner;
    private InputSystem is;
    private ArrayList<WorkData> workDatas;
    private DataHelper dh;
    private DataAdmin dataAdmin;
    private StringBuilder stringBuilder;

    public void run(){
        while(true){
            System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            stringBuilder.delete(0,stringBuilder.length());
            if(dataAdmin.getSelectAction()!=null){
                stringBuilder.append(">>>"+dataAdmin.getSelectAction().getModel());
                if(dataAdmin.getSelectProcedure()!=null){
                    stringBuilder.append(">"+dataAdmin.getSelectProcedure().getName()+"_"+dataAdmin.getSelectProcedure().getColor());
                    if(dataAdmin.getSelectProcedure().getSize()!=null&&dataAdmin.getSelectProcedure().getSize().length()!=0){
                        stringBuilder.append(">"+dataAdmin.getSelectProcedure().getSize());
                    }
                }
            }
            System.out.println(stringBuilder.toString());
            int key=jd*10+action;
            switch (key){
                case 0://
                    System.out.println("输入1创建model  输入2选择一个model");
                    action=scanner.nextInt();
                    jd++;
                    break;
                case  11://
                    System.out.println("输入model名称");
                    dataAdmin.addModel(scanner.next());
                    action=0;
                    break;
                case 12://
                    //显示存在model
                    for(int i=0;i<workDatas.size();i++){
                        System.out.println(""+i+"."+workDatas.get(i).getModel());
                    }
                    System.out.println("输入model序号");
                    dataAdmin.setSelectAction(workDatas.get(scanner.nextInt()));
                    action=0;
                    break;
                case 10://
                    System.out.println("1删除model 2创建procedure 3修改model 4选择一个procedure");
                    action=scanner.nextInt();
                    jd++;
                    break;
                case 21://
                    dataAdmin.deleteModel();
                    jd=0;
                    action=0;
                    break;
                case 22://
                    System.out.println("输入procedure名称(例如：接里布,黑色）");
                    String s=scanner.next();
                    int partition=s.indexOf(",");
                    if(partition==-1){
                        partition=s.indexOf("，");
                    }
                    dataAdmin.addProcedure(s.substring(0,partition),s.substring(partition+1));
                    jd++;
                    action=0;
                    break;
                case 23://
                    System.out.println("输入新的model名称");
                    dataAdmin.modifyModel(scanner.next());
                    jd=1;
                    action=0;
                    break;
                case 24://
                    List<Procedure> pds=dataAdmin.getSelectAction().getProcedures();
                    for(int i=0;i<pds.size();i++){
                        System.out.println(""+i+"."+pds.get(i).getName()+"_"+pds.get(i).getColor());
                    }
                    System.out.println("输入procedure序号");
                    dataAdmin.setSelectProcedure(scanner.nextInt());
                    jd++;
                    action=0;
                    break;
                case 20://
                    System.out.println("1添加size 2修改procedure 3删除procedure 4删除size 5修改size 6清空size");
                    action=scanner.nextInt();
                    jd++;
                    break;
                case 31://
                    System.out.println("输入要添加的size");
                    dataAdmin.addSize(scanner.next());
                    jd=2;
                    action=0;
                    break;
                case 32://
                    System.out.println("输入新的procedure名称(例如：接里布,黑色）");
                    String s2=scanner.next();
                    int partition2=s2.indexOf(",");
                    if(partition2==-1){
                        partition2=s2.indexOf("，");
                    }
                    dataAdmin.modifyProcedure(s2.substring(0,partition2),s2.substring(partition2+1));
                    jd=2;
                    action=0;
                    break;
                case 33://
                    dataAdmin.deleteProcedure();
                    jd=2;
                    action=0;
                    break;
                case 34://
                    dataAdmin.deleteSize(scanner.next());
                    jd=2;
                    action=0;
                    break;
                case 35://
                    System.out.println("输入要删除的size和新的size，例如：39，10m");
                    String s3=scanner.next();
                    int partition3=s3.indexOf(",");
                    if(partition3==-1){
                        partition3=s3.indexOf("，");
                    }
                    dataAdmin.modifySize(s3.substring(0,partition3),s3.substring(partition3+1));
                    break;
                case 36://
                    dataAdmin.clearSize();
                    jd=2;
                    action=0;
                    break;
                default:
                    jd=action=0;
                    break;
            }
        }
    }

    public void check(int i){

    }

    public void check(String s){

    }

    public InputSystem(){
        scanner=new Scanner(System.in);
        workDatas=new ArrayList<WorkData>();
        dh=new DataHelper();
        System.out.println(">>>>文件验证...");
        MindFileControl mfc = null;
        File f1=new File(CP.PATH_NAME_PC+"\\"+ CP.FILE_NAME);
        File f2=new File(CP.PATH_NAME_PHONE+"/"+ CP.FILE_NAME);
        if(f1.exists()){
            mfc=new MindFileControl(f1.getAbsolutePath());
        }else {
            mfc=new MindFileControl(f2.getAbsolutePath());
        }


        FeatureAdapter adp=new FeatureAdapter();
        DataContainer dc=new DataContainer(workDatas);

        //读取数据
        new Thread(new ReadDataThread(mfc,workDatas,dh,adp)).start();

        try
        {
            Thread.sleep(1000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        System.out.println("mind____"+adp.toJSONObject(workDatas.get(0)));
        System.out.println("文件标识符："+ Arrays.toString(dh.getIdentifier()));
        System.out.println();
        dataAdmin=new DataAdmin(dc);
        stringBuilder=new StringBuilder();
        stringBuilder.append("hello mewCu!");
        /**AB式流程 A为阶段，B为操作
         * 00为初始阶段
         * 11创建model 12选择一个model
         * 21删除model 22创建procedure 23修改model 24选择一个procedure
         * 31添加size 32修改procedure 33删除procedure 34删除size 35(41)修改size 36清空size
         */
    }

}
