package com.bigbata.craftsman.config.datasource;

import com.bigbata.craftsman.config.Constants;

import javax.sql.DataSource;
import java.util.Hashtable;
import java.util.Map;

/**
 * 数据源容器，包含系统中使用的所有数据源信息
 * Created by lixianghui on 15-7-13.
 */
public class DataSourceContainer {
    public static final ThreadLocal<String> holder = new ThreadLocal<String>();
    //当前用户使用的数据源
    public String currentDateSource = Constants.DEFAULT_DATA_SOURCE;

    private static Hashtable<String, DataSource> dsMap = new Hashtable<>();

    public static void add(String name, DataSource ds) {
        dsMap.put(name, ds);
    }

    public static DataSource get(String name) {
        return dsMap.get(name);
    }

    public static boolean containsKey(String name) {
        return dsMap.containsKey(name);
    }

    public static Map getDataSources() {
        return dsMap;
    }

    public static String getCurrentDateSource() {
        if (holder.get() != null)
            return holder.get();
        else
            return Constants.DEFAULT_DATA_SOURCE;
    }

    public static void setCurrentDateSource(String currentDateSource) {
        holder.set(currentDateSource);
    }
}
