package connect;

import java.util.Map;

interface JdbcCRUD {
    boolean insert(SampleObject so);
    Map<String,SampleObject> selectAll();
    SampleObject selectUnit(String strNum);
    boolean update(String strNum);
    boolean deleteAll();
    boolean deleteUnit(String strNum);
}
