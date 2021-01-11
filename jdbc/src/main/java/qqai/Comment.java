package qqai;

import java.sql.*;
import java.util.HashMap;

/**
 * 工具类
 * by qqai
 * 2021/1/8 01:00
 */
public class Comment {
    private static final String URL = "jdbc:mysql:///heimadb?serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";


    public static void delete(String sql, HashMap<String, Object> params) throws SQLException {
        StringBuilder sqlBuilder = new StringBuilder(sql);
        sqlBuilder.append(" where");
        String[] arr = new String[params.size()];
        int index = 0;
        for (String s : params.keySet()) {
            sqlBuilder.append(" and ").append(s).append(" = ?");
            arr[index++] = s;
        }
        sql = sqlBuilder.toString().replaceFirst("and", "");
        System.out.println(sql);
        PreparedStatement preparedStatement = getConn().prepareStatement(sql);
        for (int i = 0; i < arr.length; i++) {
            preparedStatement.setString(i + 1, params.get(arr[i]).toString());
        }
        boolean b = preparedStatement.execute();
        if (b) {
            throw new RuntimeException("delete error");
        }
    }

    /**
     * update by sid
     *
     * @param sql    基础sql
     * @param params 参数集
     * @throws SQLException 更新失败
     */
    public static void update(String sql, HashMap<String, Object> params) throws SQLException {
        // 根据idea建议创建StringBuilder对象
        StringBuilder sqlBuilder = new StringBuilder(sql);
        // key数组
        String[] arr = new String[params.size() - 1];
        // 下标
        int index = 0;
        for (String s : params.keySet()) {
            // 拼接预编译sql 参数集的sid是where条件 不需要拼接在set中
            if (!s.equals("sid")) {
                sqlBuilder.append(", ").append(s).append(" = ?");
                arr[index++] = s;
            }
        }
        // 拼接
        sqlBuilder.append(" where sid = ?");
        // 替换set , 为 set
        sql = sqlBuilder.toString().replaceFirst("set ,", "set");
        // 打印预编译sql
        System.out.println(sql);
        // 获取预编译对象
        PreparedStatement preparedStatement = getConn().prepareStatement(sql);
        // 设置更新值
        for (int i = 0; i < arr.length; i++) {
            preparedStatement.setString(i + 1, params.get(arr[i]).toString());
        }
        // 设置条件
        preparedStatement.setString(arr.length + 1, params.get("sid").toString());
        // 执行
        int i = preparedStatement.executeUpdate();
        if (i == 0) {
            throw new RuntimeException("update error");
        }
    }

    /**
     * 添加学生信息
     *
     * @param sql    基础sql
     * @param params 数据集
     */
    public static void insert(String sql, HashMap<String, Object> params) throws SQLException {
        // 按照idea建议把基本sql修改为StringBuilder
        StringBuilder sqlBuilder = new StringBuilder(sql);
        // 拼接
        sqlBuilder.append("(");
        // key数组
        String[] arr = new String[params.size()];
        // 数组下标
        int index = 0;
        // 拼接  保存key到arr
        for (String s : params.keySet()) {
            arr[index++] = s;
            sqlBuilder.append(", ").append(s);
        }
        // 拼接
        sqlBuilder.append(") values(");
        sqlBuilder.append(", ?".repeat(params.size()));
        sqlBuilder.append(")");
        // 转换把所有的(, 转换成(
        sql = sqlBuilder.toString().replace("(,", "(");
        // 打印
        System.out.println(sql);
        // 获取预编译对象
        PreparedStatement preparedStatement = getConn().prepareStatement(sql);
        // 添加预编译字段
        for (int i = 0; i < arr.length; i++) {
            // string比较简单 直接用了string
            preparedStatement.setString(i + 1, params.get(arr[i]).toString());
        }
        // 执行
        boolean b = preparedStatement.execute();
        if (!b) {
            throw new RuntimeException("execute失败");
        }
    }

    /**
     * 预编译
     *
     * @param query 参数集
     * @param sql   基本sql
     * @return
     */
    public static ResultSet executeQuery(String sql, HashMap<String, Object> query) throws SQLException {
        // 条件查询 加上where
        sql += " where";
        // 随时更新sql 创建一StringBuilder对象
        StringBuilder sqlBuilder = new StringBuilder(sql);
        // 保存key
        String[] arr = new String[query.size()];
        // arr下标
        int index = 0;
        // 遍历参数集的所有key 把sql拼接成预编译形式
        for (String s : query.keySet()) {
            arr[index++] = s;
            sqlBuilder.append(" and ").append(s).append(" = ?");
        }
        // 得到sql
        sql = sqlBuilder.toString();
        // 替换第一个and 也就是where后面的and为空
        sql = sql.replaceFirst("and ", "");
        // 拿到预编译对象
        PreparedStatement preparedStatement = getConn().prepareStatement(sql);
        // 从key数组获取到每一个对象的value   标记 此处string比较简单就直接用了string拼接
        for (int i = 0; i < arr.length; i++) {
            preparedStatement.setString(i + 1, query.get(arr[i]).toString());
        }
        // 执行
        return preparedStatement.executeQuery();
    }

    /**
     * 非预编译
     *
     * @param sql
     * @return
     * @throws SQLException
     */
    public static ResultSet executeQuery(String sql) throws SQLException {
        Statement statement = getConn().createStatement();
        return statement.executeQuery(sql);
    }

    /**
     * 获取数据库连接
     *
     * @return
     * @throws SQLException
     */
    private static Connection getConn() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

}
