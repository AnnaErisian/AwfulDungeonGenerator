/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeongenerator;

/**
 *
 * @author Jake
 */
public class Cell {
    
    //fields
    private int     open; //0=closed cell, 1=open cell.  Other values may be used later
    //This variable has a case of the fukits.  It's an integer and memory can suck it.
    private boolean mark; //used for tracking
    private int     zone; //used for tracking
    private int     xPos; //horizontal position
    private int     yPos; //vertical position
    private Dungeon dungeon; //parent dungeon
    
    public Cell()
    {
        this(0,false,0,0,0);
    }
    
    public Cell(int x, int y)
    {
        this(0,false,0,x,y);
    }
    
    public Cell(int openness, int x, int y)
    {
        this(openness,false,0,x,y);
    }

    public Cell(int openness, boolean marked, int zoning, int x, int y)
    {
        open = openness;
        mark = marked;
        zone = zoning;
        xPos=x;
        yPos=y;
    }
    
    //Copy Constructor
    public Cell(Cell original)
    {
        open = original.open;
        mark = original.mark;
        zone = original.zone;
        xPos = original.xPos;
        yPos = original.yPos;
    }
    
    public int getOpen(){return open;}
    public boolean getMark(){return mark;}
    public int getZone(){return zone;}
    public int getX(){return xPos;}
    public int getY(){return yPos;}
    public Dungeon getDungeon(){return dungeon;}
    
    public void setOpen(int opn){open = opn;}
    public void setMark(boolean mrk){mark = mrk;}
    public void setZone(int zn){zone = zn;}
    public void setX(int x){xPos = x;}
    public void setY(int y){yPos = y;}
    public void setDungeon(Dungeon dng){dungeon = dng;}
}