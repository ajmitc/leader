package leader.game.population;

import java.util.HashMap;
import java.util.Map;

public class PopulationStatistics {
    public static final PersonAttribute[] STAT_ORDER = {
            PersonAttribute.MALE,
            PersonAttribute.FEMALE,

            PersonAttribute.ELDERLY,
            PersonAttribute.ADULT,
            PersonAttribute.ADOLESCENT,
            PersonAttribute.CHILD
    };
    private Map<PersonAttribute, Double> _stats;

    public PopulationStatistics() {
        _stats = new HashMap<>();
        _stats.put(PersonAttribute.MALE, 0.0);
        _stats.put(PersonAttribute.FEMALE, 0.0);
        _stats.put(PersonAttribute.CHILD, 0.0);
        _stats.put(PersonAttribute.ADOLESCENT, 0.0);
        _stats.put(PersonAttribute.ADULT, 0.0);
        _stats.put(PersonAttribute.ELDERLY, 0.0);
    }

    public Double get(PersonAttribute t) {
        if (_stats.containsKey(t))
            return _stats.get(t);
        return null;
    }

    public boolean set(PersonAttribute t, Double v) {
        if (_stats.containsKey(t)) {
            _stats.put(t, v);
            return true;
        }
        return false;
    }

    public boolean inc(PersonAttribute t, Double v) {
        if (_stats.containsKey(t)) {
            _stats.put(t, _stats.get(t) + v);
            return true;
        }
        return false;
    }

    public Map<PersonAttribute, Double> getStats() {
        return _stats;
    }
}