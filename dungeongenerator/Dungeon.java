/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeongenerator;

import java.awt.Point;
import java.util.Arrays;
import java.util.ArrayList;

/**
 *
 * @author Jake
 */
public class Dungeon {
    private Cell[][] dng;
    private int width;
    private int height;
    
    public Dungeon(int x, int y)
    {
        width=x;
        height=y;
        dng = new Cell[x][y];
        for(int i = 0; i<width; i++)
        {
            for(int j = 0; j<height; j++)
            {
                this.dng[i][j] = new Cell(0, false, 0, i, j);
            }
        }
    }
    
    //copy constructor
    public Dungeon(Dungeon d)
    {
        width = d.getWidth();
        height = d.getHeight();
        
        dng = new Cell[width][height];
        
        for(int i = 0; i<width; i++)
        {
            for(int j = 0; j<height; j++)
            {
                this.dng[i][j]=d.dng[i][j];
            }
        }
    }
    
    public boolean get(int x,int y){ return dng[x][y].getOpen()==1;}
    public boolean getMark(int x,int y){ return dng[x][y].getMark();}
    public int getZone(int x,int y){ return dng[x][y].getZone();}
    
    public void set(int x, int y, int val){dng[x][y].setOpen(val);}
    public void setMark(int x, int y, boolean val){dng[x][y].setMark(val);}
    public void setZone(int x, int y, int val){dng[x][y].setZone(val);} 
    
    public int getHeight(){return height;}
    public int getWidth(){return width;}
    
    public void resetMarks(){
        for (Cell[] ba : dng)
        {
            for (Cell c : ba)
            {
                c.setMark(false);
            }
        }
    }
    
    public void resetZones(){
        for (Cell[] ba : dng)
        {
            for (Cell c : ba)
            {
                c.setZone(0);
            }
        }
    }
    
    public int countMarks(){
        int sum = 0;
        for(int i = 0; i<width; i++)
        {
            for(int j = 0; j<height; j++)
            {
                if(dng[i][j].getMark()) sum++;
            }
        }
        return sum;
    }
    
    public int countZones()
    {
        int max = highestZone();
        boolean[] presence = new boolean[max+1];
        
        int sum = 0;
        for(int i = 0; i<width; i++)
        {
            for(int j = 0; j<height; j++)
            {
                if(!presence[getZone(i,j)])
                {
                    sum++;
                    presence[getZone(i,j)]=true;
                }
            }
        }
        return sum;
    }
    
    public int highestZone()
    {
        int max = 0;
        for(int i = 0; i<width; i++)
        {
            for(int j = 0; j<height; j++)
            {
                if(dng[i][j].getZone()>max) max=dng[i][j].getZone();
            }
        }
        return max;
    }
    
    public void zoneBySeparation()
    {
        int currentZone=1;
        for(int i = 0; i<width; i++)
        {
            for(int j = 0; j<height; j++)
            {
                if(dng[i][j].getZone()==0 && dng[i][j].getOpen()==1)
                {
                    setZonesAround(i,j,currentZone);
                    currentZone++;
                    //System.out.println("NEW ZONE");
                    DungeonGenerator.counter=0;
                }
                
            }
        }
    }
    //32 48
    private void setZonesAround(int x, int y, int zone)
    {
        System.out.println(zone);
        /**
        //DungeonGenerator.counter++;
        //System.out.println(DungeonGenerator.counter);
        dng[x][y].setZone(zone);
        //System.out.println(x+","+y);
        if(x!=0)
        {
            if(getZone(x-1,y)==0 && dng[x-1][y].getOpen()==1){
                setZonesAround(x-1,y,zone);
            }
        }
        if(y!=0)
        {
            if(getZone(x,y-1)==0 && dng[x][y-1].getOpen()==1){
                setZonesAround(x,y-1,zone);
            }
        }
        if(x!=width-1)
        {
            if(getZone(x+1,y)==0 && dng[x+1][y].getOpen()==1){
                setZonesAround(x+1,y,zone);
            }
        }
        if(y!=height-1)
        {
            if(getZone(x,y+1)==0 && dng[x][y+1].getOpen()==1){
                setZonesAround(x,y+1,zone);
            }
        }
        **/
        
        //loop until no new zones were set
        
        boolean setMade = true;
        ArrayList<Point> cellsToCheck = new ArrayList<Point>();
        cellsToCheck.add(new Point(x,y));
        
        while(cellsToCheck.size()>0)
        {
            //get x and y for this looping
            x=cellsToCheck.get(0).x;
            y=cellsToCheck.get(0).y;
            
            dng[x][y].setZone(zone);
            
            cellsToCheck.remove(0);
            
            Point p;
            
            if(x!=0)
            {
                if(getZone(x-1,y)==0 && dng[x-1][y].getOpen()==1)
                {
                    p = new Point(x-1,y);
                    if(!cellsToCheck.contains(p))
                    cellsToCheck.add(p);
                }
            }
            if(y!=0)
            {
                if(getZone(x,y-1)==0 && dng[x][y-1].getOpen()==1)
                {
                    p = new Point(x,y-1);
                    if(!cellsToCheck.contains(p))
                    cellsToCheck.add(p);
                }
            }
            if(x!=width-1)
            {
                if(getZone(x+1,y)==0 && dng[x+1][y].getOpen()==1)
                {
                    p = new Point(x+1,y);
                    if(!cellsToCheck.contains(p))
                    cellsToCheck.add(p);
                }
            }
            if(y!=height-1)
            {
                if(getZone(x,y+1)==0 && dng[x][y+1].getOpen()==1)
                {
                    p = new Point(x,y+1);
                    if(!cellsToCheck.contains(p))
                    cellsToCheck.add(p);
                }
            }
        }
        
    }
    
    public void reconnect()
    {
        
        boolean allMarksMade = false;
        
        while(!allMarksMade)
        {
            
            ArrayList<int[]> points = new ArrayList<int[]>();
            
            int zones = countZones();

            for(int i = 0; i<width; i++)
            {
                for(int j = 0; j<height; j++)
                {
                    //at each point, count surrounding zones.  If it's above one, mark it.

                    int surroundingZones = 0;
                    int lastSeenZone = -1;

                    if(i!=0)
                    {
                        if(get(i-1,j)){
                            if(getZone(i-1,j)!=lastSeenZone)
                            {
                                surroundingZones++;
                                lastSeenZone=getZone(i-1, j);
                            }
                        }
                    }
                    if(j!=0)
                    {
                        if(get(i,j-1)){
                            if(getZone(i,j-1)!=lastSeenZone)
                            {
                                surroundingZones++;
                                lastSeenZone=getZone(i, j-1);
                            }
                        }
                    }
                    if(i!=width-1)
                    {
                        if(get(i+1,j)){
                            if(getZone(i+1,j)!=lastSeenZone)
                            {
                                surroundingZones++;
                                lastSeenZone=getZone(i+1, j);
                            }
                        }
                    }
                    if(j!=height-1)
                    {
                        if(get(i,j+1))
                        {
                            if(getZone(i,j+1)!=lastSeenZone)
                            {
                                surroundingZones++;
                            }
                        }
                    }

                    if(surroundingZones>1)
                    {
                        int[] arg = {i,j};
                        points.add(arg);
                        //System.out.println("SZ" + surroundingZones+"\n LSZ"+lastSeenZone);
                    }
                    
                }
            }

            if(points.size()>0)
            {
                //System.out.println("Opening");
                int randomPointToUse = (int)(Math.random()*points.size());
                int[] pointToUseArray = points.get(randomPointToUse);
                set(pointToUseArray[0], pointToUseArray[1], 1);
            }else
            {
                allMarksMade=true;
            }
            
            //reset zones
            resetZones();
            zoneBySeparation();
            
            //check for completion
            if(highestZone()==1) allMarksMade=true;
            //System.out.println(highestZone());
        }
    }
    
}
