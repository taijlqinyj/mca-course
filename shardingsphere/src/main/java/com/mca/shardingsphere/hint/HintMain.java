package com.mca.shardingsphere.hint;

import com.mca.shardingsphere.hint.service.ExampleService;
import com.mca.shardingsphere.hint.service.impl.OrderServiceImpl;
import org.apache.shardingsphere.api.hint.HintManager;
import org.apache.shardingsphere.shardingjdbc.api.yaml.YamlMasterSlaveDataSourceFactory;
import org.apache.shardingsphere.shardingjdbc.api.yaml.YamlShardingDataSourceFactory;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * ClassName: HintMain
 * Package: com.mca.shardingsphere.hint
 * Description:
 *
 * @Author: yujie.qin
 * @Create: 2023/3/9 - 21:14
 * @version: v1.0
 */
public class HintMain {
    private static final HintType TYPE = HintType.DATABASE_TABLES;
//    private static final HintType TYPE = HintType.DATABASE_ONLY;
//    private static final HintType TYPE = HintType.MASTER_ONLY;
    public static void main(String[] args) throws IOException, SQLException {
        DataSource dataSource = getDataSource();
        ExampleService exampleService = getExampleService(dataSource);
        exampleService.initEnvironment();
        exampleService.processSuccess();
        processWithHintValue(dataSource);
//        exampleService.cleanEnvironment();
    }

    private static DataSource getDataSource() throws IOException, SQLException {
        switch (TYPE) {
            case DATABASE_TABLES:
                return YamlShardingDataSourceFactory.createDataSource(getFile("E:\\project\\mca-course\\shardingsphere\\src\\main\\resources\\hint-databases-tables.yaml"));
            case DATABASE_ONLY:
                return YamlShardingDataSourceFactory.createDataSource(getFile("E:\\project\\mca-course\\shardingsphere\\src\\main\\resources\\hint-databases-only.yaml"));
            case MASTER_ONLY:
                return YamlMasterSlaveDataSourceFactory.createDataSource(getFile("E:\\project\\mca-course\\shardingsphere\\src\\main\\resources\\hint-master-only.yaml"));
            default:
                throw new UnsupportedOperationException("unsupported type");
        }
    }

    private static File getFile(final String configFile) {
        return new File(configFile);
    }

    private static ExampleService getExampleService(final DataSource dataSource) {
        return new OrderServiceImpl(dataSource);
    }

    private static void processWithHintValue(final DataSource dataSource) throws SQLException {
        try (HintManager hintManager = HintManager.getInstance();
             Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            setHintValue(hintManager);
            statement.execute("select * from t_order");
            statement.execute("SELECT i.* FROM t_order o, t_order_item i WHERE o.order_id = i.order_id");
            statement.execute("select * from t_order_item");
            statement.execute("INSERT INTO t_order (user_id, address_id, status) VALUES (1, 1, 'Jack init data')");
        }
    }

    private static void setHintValue(final HintManager hintManager) {
        switch (TYPE) {
            case DATABASE_TABLES:
                hintManager.addDatabaseShardingValue("t_order", 1L);
                hintManager.addTableShardingValue("t_order", 0L);
                return;
            case DATABASE_ONLY:
                hintManager.setDatabaseShardingValue(1L);
                return;
            case MASTER_ONLY:
                hintManager.setMasterRouteOnly();
                return;
            default:
                throw new UnsupportedOperationException("unsupported type");
        }
    }
}
