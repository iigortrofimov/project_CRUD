package project.webapp.sql;

import org.postgresql.util.PSQLException;
import project.webapp.exception.ExistStorageException;
import project.webapp.exception.StorageException;

import java.sql.SQLException;

public class ExceptionUtil {
    private ExceptionUtil() {
    }

    public static StorageException convertException(SQLException e) {
        if (e instanceof PSQLException) {

// http://www.postgresql.org/docs/9.3/static/errcodes-appendix.html - PostgreSQL Error: unique_violation
// повторяющееся значение ключа нарушает ограничение уникальности "id"

            if (e.getSQLState().equals("23505")) {
                return new ExistStorageException(null);
            }
        }
        return new StorageException(e);
    }
}