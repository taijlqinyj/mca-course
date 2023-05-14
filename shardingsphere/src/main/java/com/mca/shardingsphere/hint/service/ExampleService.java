package com.mca.shardingsphere.hint.service;

import java.sql.SQLException;

/**
 * ClassName: ExampleService
 * Package: com.mca.shardingsphere.hint.service
 * Description:
 *
 * @Author: yujie.qin
 * @Create: 2023/3/9 - 21:08
 * @version: v1.0
 */
public interface ExampleService {
    /**
     * Initialize environment.
     *
     * @throws SQLException SQL exception
     */
    void initEnvironment() throws SQLException;

    /**
     * Clean environment.
     *
     * @throws SQLException SQL exception
     */
    void cleanEnvironment() throws SQLException;

    /**
     * Process success.
     *
     * @throws SQLException SQL exception
     */
    void processSuccess() throws SQLException;

    /**
     * Process failure.
     *
     * @throws SQLException SQL exception
     */
    void processFailure() throws SQLException;

    /**
     * Print data.
     *
     * @throws SQLException SQL exception
     */
    void printData() throws SQLException;
}
