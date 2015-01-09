/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeongenerator;

import gui.GUIFrame;
import java.util.ArrayList;
import javax.swing.JFrame;

/**
 *
 * @author Jake
 */


public class DungeonGenerator {

    public static int counter = 0;
    
    public static DungeonDisplay displayWindow;

    
    public static void main(String[] args)
    {
        int x = 50;
        int y = 50;
        int n = 50*50;
        int r1 = 5;
        int r2 = 5;
        
        Dungeon dng = makeDungeon(x, y, n, r1, r2);
        
        showDungeon(dng);
        
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUIFrame().setVisible(true);
            }
        });
        
    }
    
    public static Dungeon makeDungeon(int x, int y, int n, int rw, int rb)
    {
        Dungeon dng = new Dungeon(x,y);
        
        //randomly open spaces
        for(int i = 0; i<n; i++)
        {
            dng.set((int)(Math.random()*x),(int)(Math.random()*y),1);
        }
        //showDungeon(new Dungeon(dng));
        for(int cycle = 0; cycle<rw; cycle++)
        {
            //mark dead ends
            for(int i = 0; i<x; i++)
            {
                for(int j = 0; j<y; j++)
                {
                    int closedEdges = 0;
                    if(i!=0)
                    {
                        if(!dng.get(i-1,j)){
                            closedEdges++;
                        }
                    }
                    if(j!=0)
                    {
                        if(!dng.get(i,j-1)){
                            closedEdges++;
                        }
                    }
                    if(i!=x-1)
                    {
                        if(!dng.get(i+1,j)){
                            closedEdges++;
                        }
                    }
                    if(j!=y-1)
                    {
                        if(!dng.get(i,j+1))
                        {
                            closedEdges++;
                        }
                    }
                    
                    if(closedEdges>2){
                        dng.setMark(i, j, true);}
                }
            }
            
            for(int i = 0; i<x; i++)
            {
                for(int j = 0; j<y; j++)
                {
                    if(dng.getMark(i, j))
                        dng.set(i,j,0);
                }
            }
            //showDungeon(new Dungeon(dng));
        }
        dng.resetMarks();
        for(int cycle = 0; cycle<rb; cycle++)
        {
            //mark dead ends
            for(int i = 0; i<x; i++)
            {
                for(int j = 0; j<y; j++)
                {
                    int closedEdges = 0;
                    if(i!=0)
                    {
                        if(dng.get(i-1,j)){
                            closedEdges++;
                        }
                    }
                    if(j!=0)
                    {
                        if(dng.get(i,j-1)){
                            closedEdges++;
                        }
                    }
                    if(i!=x-1)
                    {
                        if(dng.get(i+1,j)){
                            closedEdges++;
                        }
                    }
                    if(j!=y-1)
                    {
                        if(dng.get(i,j+1))
                        {
                            closedEdges++;
                        }
                    }
                    
                    if(closedEdges>2){
                        dng.setMark(i, j, true);}
                }
            }
            
            for(int i = 0; i<x; i++)
            {
                for(int j = 0; j<y; j++)
                {
                    if(dng.getMark(i, j))
                        dng.set(i,j,1);
                }
            }
            //showDungeon(new Dungeon(dng));
        }
        
        dng.zoneBySeparation();
        dng.reconnect();
        return dng;
    }
    
    public static Dungeon makeDungeonLabyrinth(int x, int y)
    {
        Dungeon dng = new Dungeon(x, y);
        
        //each step, select adjacent space that has an adjacent open space.
        int xCur=(int)(Math.random()*x);
        int yCur=(int)(Math.random()*y);
        boolean noMovesInTenRetractions = false;
        while(!noMovesInTenRetractions)
        {
            ArrayList<int[]> options = new ArrayList<int[]>();
            
            //get options
            if(x!=0)
            {
                if(dng.get(x-1, y))
                {
                    
                }
            }
            if(y!=0)
            {
                if(dng.get(x, y-1))
                {
                    
                }
            }
            if(x!=dng.getWidth()-1)
            {
                if(dng.get(x+1, y))
                {
                    
                }
            }
            if(y!=dng.getHeight()-1)
            {
                if(dng.get(x, y+1))
                {
                    
                }
            }
            
        }
        
        return dng;
    }
    
    private static int countAdjacentOpenSquares(Dungeon dng,int x,int y)
    {
        int count = 0;
        
        if(x!=0)
        {
            if(dng.get(x-1, y)) count++;
        }
        if(y!=0)
        {
            if(dng.get(x, y-1)) count++;
        }
        if(x!=dng.getWidth()-1)
        {
            if(dng.get(x+1, y)) count++;
        }
        if(y!=dng.getHeight()-1)
        {
            if(dng.get(x, y+1)) count++;
        }
        
        return count;
    }
    
    public static void showDungeon(Dungeon dng)
    {
        //print to console
        for(int i = 0; i<dng.getWidth();i++)
        {
            for(int j = 0; j<dng.getHeight();j++)
            {
                System.out.print(dng.get(i,j) ? 1:0);
            }
            System.out.println();
        }
        System.out.println();
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                displayWindow = new DungeonDisplay(dng);
                displayWindow.setVisible(true);
            }
        });
    }
}
