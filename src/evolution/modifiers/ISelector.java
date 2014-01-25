package evolution.modifiers;

import evolution.individual.AbstractIndividual;
import java.util.ArrayList;

/**
 * Selects individuals used for next generation
 * @author Andi
 */
public interface ISelector<T extends AbstractIndividual> {
    public ArrayList<T> selection(ArrayList<T> individuals, int amount);
}
