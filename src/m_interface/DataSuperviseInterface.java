package m_interface;

import modle.WorkData;

/**
 * 数据库的管理
 */

public interface DataSuperviseInterface {

    WorkData add();//
    WorkData delete();//删
    WorkData find();//查
    WorkData replace();//改

}
