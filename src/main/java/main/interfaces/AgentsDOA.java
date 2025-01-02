
package main.interfaces;

import java.util.List;
import main.models.Agents;

public interface AgentsDOA {
    void save(Agents var1);

    List<Agents> getAllAgents();

    void update(Agents var1);

    void delete(Agents var1);

    void updatePassword(String var1, String var2);
}
