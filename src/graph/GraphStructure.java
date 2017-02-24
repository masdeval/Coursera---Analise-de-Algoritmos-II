/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package graph;

/**
 *
 * @author Christian
 */
class GraphStructure
{
    Integer id;

    public GraphStructure(Integer id)
    {
        this.id = id;
    }

    public Integer getId()
    {
        return this.id;
    }

    @Override
    public int hashCode()
    {
        return 31 * 17 + this.id;
    }

    @Override
    public boolean equals(Object o)
    {
        if (!(o instanceof GraphStructure))
        {
            return false;
        }
        GraphStructure v = (GraphStructure) o;
        return this.id == v.id;
    }
    
}
