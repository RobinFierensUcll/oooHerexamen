package model.db;

import model.domain.Log;

import java.util.List;

public interface DbLog {
    void add(Log log);
    List<Log> get();
    void delete(Log log);
}
