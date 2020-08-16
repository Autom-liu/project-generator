package generator.core.utils;

import generator.core.config.DatabaseConfig;
import generator.core.config.TableConfig;
import generator.core.config.TableColumn;
import org.springframework.util.StringUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * JDBC数据库访问工具类，目前仅支持mysql连接
 */
public class DBUtil {

    private static Connection conn;

    public static Connection getConnection(DatabaseConfig databaseConfig) {
        if (conn == null) {
            conn = createConnection(databaseConfig);
        }
        return conn;
    }

    private static Connection createConnection(DatabaseConfig databaseConfig) {
        try {
            Class.forName(databaseConfig.getDriverClass());
            String connectUrl = databaseConfig.getConnectUrl();
            String decodedUrl = connectUrl.replaceAll("\\$\\{connectIp\\}", databaseConfig.getConnectIp());
            return DriverManager.getConnection(decodedUrl, databaseConfig.getUserId(), databaseConfig.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static List<TableColumn> descColumnList(TableConfig tableConfig) {
        String tableName = tableConfig.getTableName();
        String sql = "select COLUMN_NAME, DATA_TYPE, CHARACTER_MAXIMUM_LENGTH, COLUMN_KEY, COLUMN_COMMENT from INFORMATION_SCHEMA.Columns where table_name= ?";
        final List<TableColumn> result = new ArrayList<>();
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, tableName);
            try (final ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    final String columnName = rs.getString("COLUMN_NAME");
                    final String dataType = rs.getString("DATA_TYPE");
                    final String characterMaximumLength = rs.getString("CHARACTER_MAXIMUM_LENGTH");
                    final String columnKey = rs.getString("COLUMN_KEY");
                    final String columnComment = rs.getString("COLUMN_COMMENT");
                    Boolean isPrimary = "PRI".equals(columnKey);
                    final TableColumn tableColumn = new TableColumn(columnName, dataType, characterMaximumLength, columnComment, isPrimary);
                    result.add(tableColumn);
                }
            }
            return result;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    public static void close() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            conn = null;
        }
    }

}
