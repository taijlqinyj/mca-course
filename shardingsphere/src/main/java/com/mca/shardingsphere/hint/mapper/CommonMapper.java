package com.mca.shardingsphere.hint.mapper;

import java.sql.SQLException;
import java.util.List;

/**
 * ClassName: CommonMapper
 * Package: com.mca.shardingsphere.hint.mapper
 * Description:
 *
 * @Author: yujie.qin
 * @Create: 2023/3/9 - 20:57
 * @version: v1.0
 */
public interface CommonMapper<T, P> {
    /**
     * Create table if not exist.
     *
     * @throws SQLException SQL exception
     */
    void createTableIfNotExists() throws SQLException;

    /**
     * Drop table.
     *
     * @throws SQLException SQL exception
     */
    void dropTable() throws SQLException;

    /**
     * Truncate table.
     *
     * @throws SQLException SQL exception
     */
    void truncateTable() throws SQLException;

    /**
     * insert data.
     *
     * @param entity entity
     * @return generated primary key
     * @throws SQLException SQL exception
     */
    P insert(T entity) throws SQLException;

    /**
     * Delete data.
     *
     * @param primaryKey primaryKey
     * @throws SQLException SQL exception
     */
    void delete(P primaryKey) throws SQLException;

    /**
     * Select all data.
     *
     * @return all data
     * @throws SQLException SQL exception
     */
    List<T> selectAll() throws SQLException;
}
