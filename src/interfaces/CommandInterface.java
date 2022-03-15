package interfaces;

import Collection.Organization;

import java.util.List;

public interface CommandInterface {
    String getName();
    String getDescription();
    void execute(Object [] args);
}
