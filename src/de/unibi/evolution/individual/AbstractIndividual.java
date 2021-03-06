package de.unibi.evolution.individual;

import com.jme3.export.JmeExporter;
import com.jme3.export.JmeImporter;
import com.jme3.export.Savable;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.terrain.geomipmap.TerrainQuad;
import com.jme3.terrain.heightmap.AbstractHeightMap;
import com.jme3.terrain.heightmap.ImageBasedHeightMap;
import de.unibi.config.EvolutionConfig;
import de.unibi.util.Mutations;
import java.io.IOException;
import de.unibi.util.Assets;

/**
 * This is an abstract class for each individual type and should be extended if
 * a new individual has to be created. AbstractCreature has to be extended too.
 *
 * @author Andi
 */
public abstract class AbstractIndividual<T extends AbstractCreature> implements Comparable, Savable {
    
    protected TerrainQuad terrain;
    protected float fitness;
    protected int id;
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public AbstractIndividual() {
    }
    
    public float getFitness() {
        return fitness;
    }
    
    public void setFitness(float fitness) {
        this.fitness = fitness;
    }
    
    public abstract T getCreature();
    
    public abstract void setCreature(T creature);
    
    public TerrainQuad getTerrain() {
        return terrain;
    }
    
    public void setTerrain(TerrainQuad terrain) {
        this.terrain = terrain;
    }
    
    public abstract AbstractIndividual clone();
    
    public abstract void write(JmeExporter ex) throws IOException;
    
    public abstract void read(JmeImporter im) throws IOException;
    
    public abstract AbstractIndividual<T> createRandomIndividual(EvolutionConfig config);
    
    protected TerrainQuad createNewRandomTerrain(EvolutionConfig config) {
        AbstractHeightMap heightmap = new ImageBasedHeightMap(Assets.heightMapImage129.getImage().clone(), 0.5f);;
        if (config.getTerrainSize() == 129) {
            heightmap = new ImageBasedHeightMap(Assets.heightMapImage129.getImage().clone(), 0.5f);
        } else if (config.getTerrainSize() == 257) {
            heightmap = new ImageBasedHeightMap(Assets.heightMapImage257.getImage().clone(), 0.5f);
        } else if (config.getTerrainSize() == 513) {
            heightmap = new ImageBasedHeightMap(Assets.heightMapImage513.getImage().clone(), 0.5f);
        }
        heightmap.load();
        heightmap.smooth(0.9f, 1);
        
        TerrainQuad terrainf = new TerrainQuad("my terrain", 65, config.getTerrainSize(), heightmap.getHeightMap());
        if (config.getColor().equals("MINT")) {
            terrainf.setMaterial(Assets.matTerrainMint);
        } else {
            terrainf.setMaterial(Assets.matTerrainGreen);
        }
        terrainf.setLocalTranslation(0, -100, 0);
        terrainf.setLocalScale(1f, 1f, 1f);
        
        terrainf.setName("TERRAIN");
        terrainf.setShadowMode(RenderQueue.ShadowMode.CastAndReceive);
        return Mutations.mutateTerrain(terrainf, 50, 0);
        
    }

    /* @param   o the object to be compared.
     * @return  a negative integer, zero, or a positive integer as this object
     *          is less than, equal to, or greater than the specified object.
     */
    @Override
    public int compareTo(Object o) {
        
        return Float.compare(fitness, ((AbstractIndividual) o).getFitness());
        
    }
    
    public abstract String getType();
}