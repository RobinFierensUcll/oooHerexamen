package model.db;

import model.domain.Log;

import java.util.ArrayList;
import java.util.List;

public class DbLogMemory implements DbLog {
    private List<Log> logs;

    public DbLogMemory(){
        logs = new ArrayList<>();
    }

    @Override
    public void add(Log log) {
        logs.add(log);
    }

    @Override
    public List<Log> get() {
        return logs;
    }

    @Override
    public void delete(Log log) {
        logs.remove(log);
    }
}
