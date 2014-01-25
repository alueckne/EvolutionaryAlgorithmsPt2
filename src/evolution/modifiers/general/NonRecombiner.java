package evolution.modifiers.general;

import evolution.individual.AbstractIndividual;
import evolution.modifiers.IRecombiner;
import java.util.ArrayList;
import java.util.List;

/**
 * This class doesn't recombine.
 * @author Andi
 */
public class NonRecombiner implements IRecombiner<AbstractIndividual>{

    @Override
    public List<AbstractIndividual> recombine(List<AbstractIndividual> individuals, int size) {
        return new ArrayList<>();
    }
    
}
