package qqai;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * 测试类
 * by qqai
 * 2021/1/8 01:04
 */
public class Test {

    private static final String BASE_SQL = "sid, `name`, age, birthday ";

    public static void main(String[] args) throws SQLException, ParseException {
        t5();
//        t4();
//        t3();
//        t2();
//        t1();
    }

    /**
     * delete
     */
    public static void t5() throws SQLException {
        String sql = "delete from student";
        HashMap<String, Object> params = new HashMap<>();
        params.put("sid", 25);
        params.put("name", "无锡底细");
        Comment.delete(sql, params);
    }

    /**
     * update by sid
     */
    public static void t4() throws SQLException {
        HashMap<String, Object> params = new HashMap<>();
        params.put("name", "无锡底细");
        params.put("age", 19);
        params.put("sid", 25);
        String sql = "update student set ";
        Comment.update(sql, params);
    }

    /**
     * insert
     */
    public static void t3() throws ParseException, SQLException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date s = format.parse("1970-12-02");
        HashMap<String, Object> insertMap = new HashMap<>();
        insertMap.put("name", "快乐妹");
        insertMap.put("age", 43);
        insertMap.put("birthday", format.format(s));
        String sql = "insert into student";
        Comment.insert(sql, insertMap);
    }

    /**
     * 预编译sql
     */
    public static void t2() throws SQLException {
        String sql = "select " + BASE_SQL + " from student";
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put("sid", 1);
        queryParams.put("age", 30);
        // 参数集
        ResultSet rs = Comment.executeQuery(sql, queryParams);
        // 获取实体
        getEntity(rs);
    }

    /**
     * 无预编译的sql执行查找
     */
    public static void t1() {
        try {
            // 封装的方法直接返回结果集
            ResultSet rs = Comment.executeQuery("select " + BASE_SQL + " from student");
            getEntity(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 从结果集获取实体对象
     *
     * @param rs 结果集
     * @throws SQLException
     */
    private static void getEntity(ResultSet rs) throws SQLException {
        // 拿到结果集封装实体对象
        while (rs.next()) {
            Student student = new Student();
            student.setSid(rs.getInt("sid"))
                    .setName(rs.getNString("name")).setBirthday(rs.getDate("birthday"))
                    .setAge(rs.getInt("age"));
            // 打印
            System.out.println(student);
        }
    }
}
