package m_interface;

/**
 * 命令执行者（仓库）
 */
public interface CommandExecutor {

    void addSize(String s);//添加尺码
    void addProcedure(String sp,String sc);//添加工序（工序名称,颜色）
    void addModel(String model);//添加一个新单
    void addDescription(String des);//添加描述


}
