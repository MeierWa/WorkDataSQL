
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
import thread.WriteDataThread;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    private MindFileControl mfc = null;
    private DataAdmin dataAdmin;
    private StringBuilder stringBuilder,checkString;
    private int cxk;
    private Pattern cPattern10,cPattern11,cPattern12,cPattern20,cPattern22,cPattern31,cPattern35;
    private Matcher cMatcher;
    public static final int BREAK=1,MISMATCH=2,SAVE=3;

    public void run(){
        while(true){
            System.out.print("\n\n\n\n\n");
            stringBuilder.delete(0,stringBuilder.length());
            checkString.delete(0,checkString.length());
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
                    checkString.append(scanner.next());
                    cxk=check(checkString.toString());
                    if(cxk==BREAK){
                        jd=action=0;
                        break;
                    }else if(cxk==MISMATCH) {
                        break;
                    }
                    dataAdmin.addModel(checkString.toString());
                    action=0;
                    break;
                case 12://
                    //显示存在model
                    for(int i=0;i<workDatas.size();i++){
                        System.out.println(""+i+"."+workDatas.get(i).getModel());
                    }
                    if(workDatas.isEmpty()||workDatas.size()==0){
                        System.out.println("列表为空");
                        jd=0;
                        action=0;
                    }else {
                        System.out.println("输入model序号");
                        checkString.append(scanner.next());
                        cxk=check(checkString.toString());
                        if(cxk==BREAK){
                            jd=action=0;
                            break;
                        }else if(cxk==MISMATCH){
                            break;
                        }
                        int index=Integer.parseInt(checkString.toString());
                        if(!workDatas.isEmpty()&&index>=0&&index<workDatas.size()){
                            dataAdmin.setSelectAction(workDatas.get(index));
                            action=0;
                        }
                    }
                    break;
                case 10://
                    System.out.println("1删除model 2创建procedure 3修改model 4选择一个procedure");
                    checkString.append(scanner.next());
                    cxk=check(checkString.toString());
                    if(cxk==BREAK){
                        jd=action=0;
                        break;
                    }else if(cxk==SAVE){
                        dataAdmin.save(mfc);
                    }else if(cxk==MISMATCH){
                        break;
                    }
                    action=Integer.parseInt(checkString.toString());
                    jd++;
                    break;
                case 21://
                    dataAdmin.deleteModel();
                    jd=0;
                    action=0;
                    break;
                case 22://
                    System.out.println("输入procedure名称(例如：接里布,黑色）");
                    checkString.append(scanner.next());
                    cxk=check(checkString.toString());
                    if(cxk==BREAK){
                        jd=1;
                        action=0;
                        break;
                    }else if(cxk==MISMATCH){
                        break;
                    }
                    String s=checkString.toString();
                    int partition=s.indexOf(",");
                    if(partition==-1){
                        partition=s.indexOf("，");
                    }
                    dataAdmin.addProcedure(s.substring(0,partition),s.substring(partition+1));
                    jd=2;
                    action=0;
                    break;
                case 23://
                    System.out.println("输入新的model名称");
                    checkString.append(scanner.next());
                    cxk=check(checkString.toString());
                    if(cxk==BREAK){
                        jd=1;
                        action=0;
                        break;
                    }else if(cxk==MISMATCH) {
                        break;
                    }
                    dataAdmin.modifyModel(checkString.toString());
                    jd=1;
                    action=0;
                    break;
                case 24://
                    List<Procedure> pds=dataAdmin.getSelectAction().getProcedures();
                    if(dataAdmin.getSelectAction().getProcedures()==null||dataAdmin.getSelectAction().getProcedures().isEmpty()){
                        System.out.println("列表为空");
                        jd=1;
                        action=0;
                        break;
                    }
                    for(int i=0;i<pds.size();i++){
                        System.out.println(""+i+"."+pds.get(i).getName()+"_"+pds.get(i).getColor());
                    }
                    System.out.println("输入procedure序号");
                    checkString.append(scanner.next());
                    cxk=check(checkString.toString());
                    if(cxk==BREAK){
                        jd=1;
                        action=0;
                        break;
                    }else if(cxk==MISMATCH){
                        break;
                    }
                    int index=Integer.parseInt(checkString.toString());
                    if(index>=0&&index<dataAdmin.getSelectAction().getProcedures().size()){
                        dataAdmin.setSelectProcedure(index);
                        jd=2;
                        action=0;
                    }
                    break;
                case 20://
                    System.out.println("1添加size 2修改procedure 3删除procedure 4删除size 5修改size 6清空size");
                    checkString.append(scanner.next());
                    cxk=check(checkString.toString());
                    if(cxk==BREAK){
                        jd=1;
                        action=0;
                        break;
                    }else if(cxk==SAVE){
                        dataAdmin.save(mfc);
                        break;
                    }else if(cxk==MISMATCH){
                        break;
                    }
                    action=Integer.parseInt(checkString.toString());
                    jd++;
                    break;
                case 31://
                    System.out.println("输入要添加的size");
                    checkString.append(scanner.next());
                    cxk=check(checkString.toString());
                    if(cxk==BREAK){
                        jd=2;
                        action=0;
                        break;
                    }else if(cxk==MISMATCH){
                        break;
                    }
                    dataAdmin.addSize(checkString.toString());
                    jd=2;
                    action=0;
                    break;
                case 32://
                    System.out.println("输入新的procedure名称(例如：接里布,黑色）");
                    checkString.append(scanner.next());
                    cxk=check(checkString.toString());
                    if(cxk==BREAK){
                        jd=2;
                        action=0;
                        break;
                    }else if(cxk==MISMATCH){
                        break;
                    }
                    String s2=checkString.toString();
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
                    jd=1;
                    action=0;
                    break;
                case 34://
                    System.out.println("输入要删除的size");
                    checkString.append(scanner.next());
                    cxk=check(checkString.toString());
                    if(cxk==BREAK){
                        jd=2;
                        action=0;
                        break;
                    }else if(cxk==MISMATCH){
                        break;
                    }
                    dataAdmin.deleteSize(checkString.toString());
                    jd=2;
                    action=0;
                    break;
                case 35://
                    System.out.println("输入要删除的size和新的size，例如：39，10m");
                    checkString.append(scanner.next());
                    cxk=check(checkString.toString());
                    if(cxk==BREAK){
                        jd=2;
                        action=0;
                        break;
                    }else if(cxk==MISMATCH){
                        break;
                    }
                    String s3=checkString.toString();
                    int partition3=s3.indexOf(",");
                    if(partition3==-1){
                        partition3=s3.indexOf("，");
                    }
                    dataAdmin.modifySize(s3.substring(0,partition3),s3.substring(partition3+1));
                    jd=2;
                    action=0;
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


    public InputSystem(){
        scanner=new Scanner(System.in);
        workDatas=new ArrayList<WorkData>();
        dh=new DataHelper();
        System.out.println(">>>>文件验证...");

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
        checkString=new StringBuilder();
        checkString.append("i'm come to checking!");
        cPattern10=Pattern.compile("^[1234]$");
        cPattern11=Pattern.compile("^[A-Za-z0-9]+$");
        cPattern12=Pattern.compile("^[0-9]+$");
        cPattern20=Pattern.compile("^[123456]$");
        cPattern22=Pattern.compile("^[\\u4e00-\\u9fa5]+[,，]{1}[\\u4e00-\\u9fa5]+$");
        cPattern31=Pattern.compile("^[1-9][0-9]*([.][5])?[a-zA-Z]?$");
        cPattern35=Pattern.compile("^[1-9][0-9]*([.][5])?[a-zA-Z]?[,，][1-9][0-9]*([.][5])?[a-zA-Z]?$");
        /**AB式流程 A为阶段，B为操作
         * 00为初始阶段
         * 11创建model 12选择一个model
         * 21删除model 22创建procedure 23修改model 24选择一个procedure
         * 31添加size 32修改procedure 33删除procedure 34删除size 35(41)修改size 36清空size
         */
    }

    public int check(String s){
        if(s.equals("up")){
            return BREAK;
        }
        if(s.equals("save")){
            return SAVE;
        }
        if(jd==1){
            if(action==1&&!(cMatcher=cPattern11.matcher(s)).find()) {
                return MISMATCH;
            }else if(action==2&&!(cMatcher=cPattern12.matcher(s)).find()){
                return MISMATCH;
            }else if(action==0&&!(cMatcher=cPattern10.matcher(s)).find()){
                return MISMATCH;
            }
        }else if(jd==2){
            if(action==2&&!(cMatcher=cPattern22.matcher(s)).find()){
                return MISMATCH;
            }else if(action==4&&!(cMatcher=cPattern12.matcher(s)).find()){
                return MISMATCH;
            }else if(action==1&&!(cMatcher=cPattern11.matcher(s)).find()) {
                return MISMATCH;
            }else if(action==0&&!(cMatcher=cPattern20.matcher(s)).find()){
                return MISMATCH;
            }
        }else if(jd==3){
            if(action==1&&!(cMatcher=cPattern31.matcher(s)).find()){
                return MISMATCH;
            }else if(action==2&&!(cMatcher=cPattern22.matcher(s)).find()){
                return MISMATCH;
            }else if(action==4&&!(cMatcher=cPattern31.matcher(s)).find()){
                return MISMATCH;
            }else if(action==5&&!(cMatcher=cPattern35.matcher(s)).find()){
                return MISMATCH;
            }
        }
        return 0;
    }
}
