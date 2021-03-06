package utils;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.statement.delete.Delete;
import net.sf.jsqlparser.statement.insert.Insert;
import net.sf.jsqlparser.statement.update.Update;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.MethodUtils;

import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.util.*;
import java.util.regex.Matcher;

/**
 * @author steven01.gan
 * @version 1.0
 */
public class SqlUtils {

    static List<String> exculedColumnList = Arrays.asList("create_time", "update_time");

    public static String parse2Select(String sql) {
        String selectSql = null;
        try {
            String table = null;
            List<Column> columnList = new ArrayList<Column>();
            List<Expression> expressionList = new ArrayList<Expression>();
            String where = "  WHERE ";
            if (sql.toUpperCase().trim().startsWith("INSERT")) {
                Insert insert = (Insert) CCJSqlParserUtil.parse(sql);
                columnList = insert.getColumns();
                table = insert.getTable().getName();
                expressionList = ((ExpressionList) insert.getItemsList()).getExpressions();
            } else if (sql.toUpperCase().trim().startsWith("UPDATE")) {
                Update update = (Update) CCJSqlParserUtil.parse(sql);
                columnList = update.getColumns();
                table = update.getTable().getName();
                expressionList = update.getExpressions();
            } else if (sql.toUpperCase().trim().startsWith("DELETE")) {
                Delete delete = (Delete) CCJSqlParserUtil.parse(sql);
                table = delete.getTable().getName();
                Expression expression = delete.getWhere();
                if (StringUtils.isNotEmpty(expression.toString())) {
                    selectSql = "SELECT * FROM " + table + where + expression.toString();
                }
            }

            for (int i = 0; i < columnList.size(); i++) {

                if (isContains(columnList.get(i).getColumnName())) {
                    continue;
                }

                where += columnList.get(i).getColumnName() + "= " + expressionList.get(i);

                if (i != columnList.size() - 1) {
                    where += " AND ";
                }
            }

            if (where.endsWith(" AND ")) {
                where = where.substring(0, where.length() - 5);
            }

            if (StringUtils.isNotEmpty(table) && !where.trim().equals("WHERE")) {

                selectSql = "SELECT * FROM " + table + where;
            }

        } catch (JSQLParserException e) {
            e.printStackTrace();
        }
        return selectSql;
    }

    private static boolean isContains(String column) {
        for (String not : exculedColumnList) {
            if (column.contains(not)) {
                return true;
            }
        }
        return false;
    }

    public static String showSql(Object boundSql) {
        String sql = "";
        try {
            // sql?????????????????????????????????????????????
            sql = InvokedUtils.getPrivateField(boundSql, "sql").toString().replaceAll("[\\s]+", " ");

            // ????????????
            Object parameterObject = InvokedUtils.getPrivateField(boundSql, "parameterObject");
            List<Object> parameterMappings = (List<Object>) InvokedUtils.getPrivateField(boundSql, "parameterMappings");
            Object metaObject = InvokedUtils.getPrivateField(boundSql, "metaParameters");

            if (CollectionUtils.isNotEmpty(parameterMappings) && parameterObject != null) {
                for (Object parameterMapping : parameterMappings) {
                    String propertyName = (String) MethodUtils.invokeMethod(parameterMapping, "getProperty");
                    Boolean hasGetter = (Boolean) MethodUtils.invokeMethod(metaObject, "hasGetter", propertyName);
                    Boolean hasAdditionalParameter = (Boolean) MethodUtils.invokeMethod(boundSql, "hasAdditionalParameter", propertyName);

                    if (hasGetter) {
                        Object obj = MethodUtils.invokeMethod(metaObject, "getValue", propertyName);
                        sql = sql.replaceFirst("\\?",
                                Matcher.quoteReplacement(getParameterValue(obj)));
                    } else if (hasAdditionalParameter) {
                        // ??????????????????sql
                        Object obj = MethodUtils.invokeMethod(boundSql, "getAdditionalParameter", propertyName);
                        sql = sql.replaceFirst("\\?",
                                Matcher.quoteReplacement(getParameterValue(obj)));
                    } else {
                        // ????????????????????????????????????
                        sql = sql.replaceFirst("\\?", "unknown");
                    }

                }
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        return sql;
    }


    public static String showSql(Object configuration, Object boundSql) {
        String sql = "";
        try {
            // sql?????????????????????????????????????????????
            sql = InvokedUtils.getPrivateField(boundSql, "sql").toString().replaceAll("[\\s]+", " ");

            // ????????????
            Object parameterObject = InvokedUtils.getPrivateField(boundSql, "parameterObject");
            List<Object> parameterMappings = (List<Object>) InvokedUtils.getPrivateField(boundSql, "parameterMappings");

            if (CollectionUtils.isNotEmpty(parameterMappings) && parameterObject != null) {
                // ??????????????????????????????????????????????????????????????????java?????????????????????????????????
                Object typeHandlerRegistry = MethodUtils.invokeMethod(configuration, "getTypeHandlerRegistry");
                // ????????????parameterObject.getClass(??????????????????????????????????????????
                Boolean hasTypeHandler = (Boolean) MethodUtils.invokeMethod(typeHandlerRegistry, "hasTypeHandler", parameterObject.getClass());
                if (hasTypeHandler) {
                    sql = sql.replaceFirst("\\?",
                            Matcher.quoteReplacement(getParameterValue(parameterObject)));
                } else {
                    // MetaObject??????????????????originalObject??????????????????get???set??????????????????????????????originalObject????????????,???????????????JavaBean???Collection???Map???????????????????????????
                    Object metaObject = MethodUtils.invokeMethod(configuration, "newMetaObject", parameterObject);
                    for (Object parameterMapping : parameterMappings) {
                        String propertyName = (String) MethodUtils.invokeMethod(parameterMapping, "getProperty");
                        Boolean hasGetter = (Boolean) MethodUtils.invokeMethod(metaObject, "hasGetter", propertyName);
                        Boolean hasAdditionalParameter = (Boolean) MethodUtils.invokeMethod(boundSql, "hasAdditionalParameter", propertyName);

                        if (hasGetter) {
                            Object obj = MethodUtils.invokeMethod(metaObject, "getValue", propertyName);
                            sql = sql.replaceFirst("\\?",
                                    Matcher.quoteReplacement(getParameterValue(obj)));
                        } else if (hasAdditionalParameter) {
                            // ??????????????????sql
                            Object obj = MethodUtils.invokeMethod(boundSql, "getAdditionalParameter", propertyName);
                            sql = sql.replaceFirst("\\?",
                                    Matcher.quoteReplacement(getParameterValue(obj)));
                        } else {
                            // ????????????????????????????????????
                            sql = sql.replaceFirst("\\?", "unknown");
                        }
                    }
                }
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        return sql;
    }

    /**
     * ???????????????String?????????????????????
     * ????????????????????????????????????????????????????????????????????? ????????????null?????????null?????????????????????
     */
    private static String getParameterValue(Object obj) {
        String value;
        if (obj instanceof String) {
            value = "'" + obj.toString() + "'";
        } else if (obj instanceof Date) {
            DateFormat formatter = DateFormat.getDateTimeInstance(DateFormat.DEFAULT,
                    DateFormat.DEFAULT, Locale.CHINA);
            value = "'" + formatter.format(new Date()) + "'";
        } else {
            if (obj != null) {
                value = obj.toString();
            } else {
                value = "";
            }
        }
        return value;
    }

    public static void main(String[] args) {
        String sql = "insert into tpc_order_lock_control ( `order_sn`,`create_time`,`aaa` ,`update_time`) values ( '2254124175763776','1111111','1234' ,'333');";
        String selectSql = parse2Select(sql);
        System.out.println(selectSql);
    }
}
