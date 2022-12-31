package project;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Project {

    public static void main(String[] args) throws FileNotFoundException
    {
        int[][] initialMatrix = new int[3][3];
        int[][] goalMatrix = { {1, 2, 3}, 
                               {8, 0, 4},
                               {7, 6, 5} };
        File file1 = new File("D:\\test.txt");
        File file2 = new File("D:\\solutions found.txt");
        Scanner s1 = new Scanner(file1);
        Scanner s2 = new Scanner(file2);
        Scanner s3 = new Scanner(file1);
        String f = String.valueOf(s3.nextLine());
test:
for(int s=0;s<10;s++) {

if(s2.nextLine().equals(f))
{
        for(int i=0;i<3;i++) {
            for (int j = 0; j < 3; j++) {
                initialMatrix[i][j] = s1.nextInt();
            }
        }
        break test;
} 
if(s == 9)
{
    System.out.println("                    This Program by S.Manar ");
    System.out.println();
    System.out.println();
    System.out.println("Solution not found -_- ");
    System.out.println();
    System.exit(0);
}
}

        Node root = new Node();
        root.setState(initialMatrix);
        root.setH(manhattanDist(initialMatrix,goalMatrix));
        root.setG(0);
        root.setF(root.getH()+root.getG());

        Node goalnode = new Node();
        goalnode.setState(goalMatrix);
        goalnode.setH(0);

        CompareNode nc = new CompareNode();
        PriorityQueue<Node> pq = new PriorityQueue<Node>(10, nc);

        pq.add(root);

        ArrayList<Node> explored = new ArrayList<Node>(10000000);
        ArrayList<Node> p = new ArrayList<Node>();

        int depth =0;
        int isGoal=0;

        Node node = new Node();
        node = pq.poll();
        while(!(Arrays.deepEquals(node.getState(),goalMatrix)))        
        {
          explored.add(node);                                         
            ArrayList<Node> sucessorNodes = new ArrayList<Node>();
            sucessorNodes = genChild(goalMatrix, node);                
            for (Node child: sucessorNodes){
                //Check if the child is goal Node
                if(Arrays.deepEquals(child.getState(),goalMatrix))
                {
                   isGoal =1;
                   depth= child.getG();
                   break;
                }
                if (priorityQueueContains(pq,child)) {                 
                    continue;
                }
                if (exploredContains(explored,child)) {                 
                    continue;
                }
                pq.add(child);                                         
            }
            if(isGoal==0) {                                            
                node = pq.poll();
            }
            else {
                break;
            }
        }
        
        goalnode.setG(depth);
        goalnode.setF(goalnode.getG()+goalnode.getH());
        p.add(goalnode);
        while(!Arrays.deepEquals(node.getState(),initialMatrix))
        {
            p.add(node);
            node = node.getAncestor();
        }
        p.add(root);
        Collections.reverse(p);

        System.out.println();
        System.out.println("                    This Program by S.Manar ");
        System.out.println();
        System.out.println();
        System.out.println("Solution is found ^_^ ");
        System.out.println();
        System.out.println("Number of Nodes Expanded: "+explored.size());
        System.out.println();
        System.out.println("Length of the solution: "+depth);
        System.out.println();
        System.out.println("Solution Path from Initial State to Goal State:");
        System.out.println();
        System.out.println("        \\/");
        for ( int i = 0 ; i < p.size() ; i++ )
        {
            if(i == (p.size() - 1))
            {                
            System.out.println("   Final State: ");
            System.out.println();
            printMatrix(p.get(i));
            }
            else if(i == 0)
            {
            System.out.println();
            System.out.println("   Initial State: ");
            System.out.println();
            printMatrix(p.get(i));
            }
            else {
            printMatrix(p.get(i));
            System.out.println("        \\/");
            System.out.println("        \\/");
            System.out.println();
            }
        }

    }

    static boolean priorityQueueContains(PriorityQueue<Node> pq, Node child)
    {
        for ( Node x: pq)
        {
            int[][] curr = x.getState();
            int[][] chi = child.getState();
            if(Arrays.deepEquals(curr,chi))
            {
                return true;
            }
        }
        return false;
    }

    static boolean exploredContains(ArrayList<Node> explore, Node child)
    {
        for ( Node x: explore)
        {
            int[][] curr = x.getState();
            int[][] chi = child.getState();
            if(Arrays.deepEquals(curr,chi))
            {
                return true;
            }
        }
        return false;
    }
    static void printMatrix(Node n)
    {
        int[][]a = n.getState();
        System.out.print(" _____ _____ _____\n"+"|     |     |     |\n"+"|  "+a[0][0]+"  |"+"  "
                         +a[0][1]+"  |"+"  "+a[0][2]+"  |\n"+"|_____|_____|_____|\n"
                         +"|     |     |     |\n"+"|  "+a[1][0]+"  |"+"  "+a[1][1]+"  |"+"  "
                         +a[1][2]+"  |\n"+"|_____|_____|_____|\n"+
                         "|     |     |     |\n"+"|  "+a[2][0]+"  |"+"  "+a[2][1]+"  |"+"  "
                         +a[2][2]+"  |\n"+"|_____|_____|_____|\n");

        System.out.println();
        System.out.println();
        System.out.println("       "+n.getAction());
        System.out.println("       F= "+n.getF());
        System.out.println();
    }

    static ArrayList<Node> genChild(int[][] goalMatrix, Node node)
    {

        ArrayList<Node> children = new ArrayList<Node>();
        String s = new String();
        int [][] state= node.getState();
        for(int x = 0;x<3;x++)
        {
            for(int y = 0;y<3;y++)
            {
                if(state[x][y]==0)
                {
                    s = String.valueOf(x)+String.valueOf(y);
                }
            }
        }

        if(s.equals("00"))                    
        {
            int[][] childa = new int[state.length][state[0].length];
            int[][] child1 = new int[state.length][state[0].length];
            int[][] child2 = new int[state.length][state[0].length];
            Node child_1 = new Node();
            Node child_2 = new Node();
            for (int z = 0; z < childa.length; z++)
                childa[z] = Arrays.copyOf(state[z], state[z].length);
            for(int k = 0;k<3;k++)
            {
                for ( int l = 0;l<3;l++)
                {
                    if((k==0&&l==0)||(k==0&&l==1))
                    {
                        child1[0][1] = childa[0][0];
                        child1[0][0] = childa[0][1];
                        child_1.setAction("Right");
                        
                    }
                    else
                    {
                        child1[k][l] = childa[k][l];
                    }
                }
            }
            child_1.setState(child1);
            child_1.setAncestor(node);
            child_1.setH(manhattanDist(child1,goalMatrix));
            child_1.setG(child_1.getAncestor().getG() + 1);
            child_1.setF(child_1.getH()+child_1.getG());
            children.add(child_1);
            for(int k = 0;k<3;k++)
            {
                for ( int l = 0;l<3;l++)
                {
                    if((k==0&&l==0)||(k==1&&l==0))
                    {
                        child2[0][0] = childa[1][0];
                        child2[1][0] = childa[0][0];
                        child_2.setAction("Down");
                    }
                    else
                    {
                        child2[k][l] = childa[k][l];
                    }
                }
            }
            child_2.setState(child2);
            child_2.setAncestor(node);
            child_2.setH(manhattanDist(child2,goalMatrix));
            child_2.setG(child_2.getAncestor().getG() + 1);
            child_2.setF(child_2.getH()+child_2.getG());
            children.add(child_2);

            
        }
        else if(s.equals("01"))   
        {
            int[][] childb = new int[state.length][state[0].length];
            int[][] child3 = new int[state.length][state[0].length];
            int[][] child4 = new int[state.length][state[0].length];
            int[][] child5 = new int[state.length][state[0].length];
            Node child_3 = new Node();
            Node child_4 = new Node();
            Node child_5 = new Node();
            for (int z = 0; z < childb.length; z++)
                childb[z] = Arrays.copyOf(state[z], state[z].length);
            for(int k = 0;k<3;k++)
            {
                for ( int l = 0;l<3;l++)
                {
                    if((k==0&&l==0)||(k==0&&l==1))
                    {
                        child3[0][1] = childb[0][0];
                        child3[0][0] = childb[0][1];
                        child_3.setAction("Left");
                    }
                    else
                    {
                        child3[k][l] = childb[k][l];
                    }
                }
            }
            child_3.setState(child3);
            child_3.setAncestor(node);
            child_3.setH(manhattanDist(child3,goalMatrix));
            child_3.setG(child_3.getAncestor().getG() + 1);
            child_3.setF(child_3.getH()+child_3.getG());
            children.add(child_3);
            for(int k = 0;k<3;k++)
            {
                for ( int l = 0;l<3;l++)
                {
                    if((k==0&&l==1)||(k==0&&l==2))
                    {
                        child4[0][1] = childb[0][2];
                        child4[0][2] = childb[0][1];
                        child_4.setAction("Right");
                    }
                    else
                    {
                        child4[k][l] = childb[k][l];
                    }
                }
            }
            child_4.setState(child4);
            child_4.setAncestor(node);
            child_4.setH(manhattanDist(child4,goalMatrix));
            child_4.setG(child_4.getAncestor().getG() + 1);
            child_4.setF(child_4.getH()+child_4.getG());
            children.add(child_4);
            for(int k = 0;k<3;k++)
            {
                for ( int l = 0;l<3;l++)
                {
                    if((k==0&&l==1)||(k==1&&l==1))
                    {
                        child5[0][1] = childb[1][1];
                        child5[1][1] = childb[0][1];
                        child_5.setAction("Down");
                    }
                    else
                    {
                        child5[k][l] = childb[k][l];
                    }
                }
            }
            child_5.setState(child5);
            child_5.setAncestor(node);
            child_5.setH(manhattanDist(child5,goalMatrix));
            child_5.setG(child_5.getAncestor().getG() + 1);
            child_5.setF(child_5.getH()+child_5.getG());
            children.add(child_5);
        }

        else if(s.equals("02"))  
        {
            int[][] childc = new int[state.length][state[0].length];
            int[][] child6 = new int[state.length][state[0].length];
            int[][] child7 = new int[state.length][state[0].length];
            Node child_6 = new Node();
            Node child_7 = new Node();

            for (int z = 0; z < childc.length; z++)
                childc[z] = Arrays.copyOf(state[z], state[z].length);
            for(int k = 0;k<3;k++)
            {
                for ( int l = 0;l<3;l++)
                {
                    if((k==0&&l==2)||(k==0&&l==1))
                    {
                        child6[0][1] = childc[0][2];
                        child6[0][2] = childc[0][1];
                        child_6.setAction("Left");
                    }
                    else
                    {
                        child6[k][l] = childc[k][l];
                    }
                }
            }
            child_6.setState(child6);
            child_6.setAncestor(node);
            child_6.setH(manhattanDist(child6,goalMatrix));
            child_6.setG(child_6.getAncestor().getG() + 1);
            child_6.setF(child_6.getH()+child_6.getG());
            children.add(child_6);
            for(int k = 0;k<3;k++)
            {
                for ( int l = 0;l<3;l++)
                {
                    if((k==0&&l==2)||(k==1&&l==2))
                    {
                        child7[0][2] = childc[1][2];
                        child7[1][2] = childc[0][2];
                        child_7.setAction("Down");
                    }
                    else
                    {
                        child7[k][l] = childc[k][l];
                    }
                }
            }
            child_7.setState(child7);
            child_7.setAncestor(node);
            child_7.setH(manhattanDist(child7,goalMatrix));
            child_7.setG(child_7.getAncestor().getG() + 1);
            child_7.setF(child_7.getH()+child_7.getG());
            children.add(child_7);
        }
        else if(s.equals("10"))    
        {
            int[][] childd = new int[state.length][state[0].length];
            int[][] child8 = new int[state.length][state[0].length];
            int[][] child9 = new int[state.length][state[0].length];
            int[][] child10 = new int[state.length][state[0].length];
            Node child_8 = new Node();
            Node child_9 = new Node();
            Node child_10 = new Node();

            for (int z = 0; z < childd.length; z++)
                childd[z] = Arrays.copyOf(state[z], state[z].length);
            for(int k = 0;k<3;k++)
            {
                for ( int l = 0;l<3;l++)
                {
                    if((k==1&&l==0)||(k==0&&l==0))
                    {
                        child8[1][0] = childd[0][0];
                        child8[0][0] = childd[1][0];
                        child_8.setAction(" Up");
                    }
                    else
                    {
                        child8[k][l] = childd[k][l];
                    }
                }
            }
            child_8.setState(child8);
            child_8.setAncestor(node);
            child_8.setH(manhattanDist(child8,goalMatrix));
            child_8.setG(child_8.getAncestor().getG() + 1);
            child_8.setF(child_8.getH()+child_8.getG());
            children.add(child_8);
            for(int k = 0;k<3;k++)
            {
                for ( int l = 0;l<3;l++)
                {
                    if((k==1&&l==0)||(k==1&&l==1))
                    {
                        child9[1][0] = childd[1][1];
                        child9[1][1] = childd[1][0];
                        child_9.setAction("Right");
                    }
                    else
                    {
                        child9[k][l] = childd[k][l];
                    }
                }
            }
            child_9.setState(child9);
            child_9.setAncestor(node);
            child_9.setH(manhattanDist(child9,goalMatrix));
            child_9.setG(child_9.getAncestor().getG() + 1);
            child_9.setF(child_9.getH()+child_9.getG());
            children.add(child_9);
            for(int k = 0;k<3;k++)
            {
                for ( int l = 0;l<3;l++)
                {
                    if((k==1&&l==0)||(k==2&&l==0))
                    {
                        child10[1][0] = childd[2][0];
                        child10[2][0] = childd[1][0];
                        child_10.setAction("Down");
                    }
                    else
                    {
                        child10[k][l] = childd[k][l];
                    }
                }
            }
            child_10.setState(child10);
            child_10.setAncestor(node);
            child_10.setH(manhattanDist(child10,goalMatrix));
            child_10.setG(child_10.getAncestor().getG() + 1);
            child_10.setF(child_10.getH()+child_10.getG());
            children.add(child_10);
        }
        else if(s.equals("11")) 
        {
            int[][] childe = new int[state.length][state[0].length];
            int[][] child11 = new int[state.length][state[0].length];
            int[][] child12 = new int[state.length][state[0].length];
            int[][] child13 = new int[state.length][state[0].length];
            int[][] child14 = new int[state.length][state[0].length];
            Node child_11 = new Node();
            Node child_12 = new Node();
            Node child_13 = new Node();
            Node child_14 = new Node();

            for (int z = 0; z < childe.length; z++)
                childe[z] = Arrays.copyOf(state[z], state[z].length);
            for(int k = 0;k<3;k++)
            {
                for ( int l = 0;l<3;l++)
                {
                    if((k==1&&l==1)||(k==0&&l==1))
                    {
                        child11[1][1] = childe[0][1];
                        child11[0][1] = childe[1][1];
                        child_11.setAction(" Up");
                    }
                    else
                    {
                        child11[k][l] = childe[k][l];
                    }
                }
            }
            child_11.setState(child11);
            child_11.setAncestor(node);
            child_11.setH(manhattanDist(child11,goalMatrix));
            child_11.setG(child_11.getAncestor().getG() + 1);
            child_11.setF(child_11.getH()+child_11.getG());
            children.add(child_11);
            for(int k = 0;k<3;k++)
            {
                for ( int l = 0;l<3;l++)
                {
                    if((k==1&&l==1)||(k==1&&l==0))
                    {
                        child12[1][1] = childe[1][0];
                        child12[1][0] = childe[1][1];
                        child_12.setAction("Left");
                    }
                    else
                    {
                        child12[k][l] = childe[k][l];
                    }
                }
            }
            child_12.setState(child12);
            child_12.setAncestor(node);
            child_12.setH(manhattanDist(child12,goalMatrix));
            child_12.setG(child_12.getAncestor().getG() + 1);
            child_12.setF(child_12.getH()+child_12.getG());
            children.add(child_12);
            for(int k = 0;k<3;k++)
            {
                for ( int l = 0;l<3;l++)
                {
                    if((k==1&&l==1)||(k==1&&l==2))
                    {
                        child13[1][1] = childe[1][2];
                        child13[1][2] = childe[1][1];
                        child_13.setAction("Right");
                    }
                    else
                    {
                        child13[k][l] = childe[k][l];
                    }
                }
            }
            child_13.setState(child13);
            child_13.setAncestor(node);
            child_13.setH(manhattanDist(child13,goalMatrix));
            child_13.setG(child_13.getAncestor().getG() + 1);
            child_13.setF(child_13.getH()+child_13.getG());
            children.add(child_13);
            for(int k = 0;k<3;k++)
            {
                for ( int l = 0;l<3;l++)
                {
                    if((k==1&&l==1)||(k==2&&l==1))
                    {
                        child14[1][1] = childe[2][1];
                        child14[2][1] = childe[1][1];
                        child_14.setAction("Down");
                    }
                    else
                    {
                        child14[k][l] = childe[k][l];
                    }
                }
            }
            child_14.setState(child14);
            child_14.setAncestor(node);
            child_14.setH(manhattanDist(child14,goalMatrix));
            child_14.setG(child_14.getAncestor().getG() + 1);
            child_14.setF(child_14.getH()+child_14.getG());
            children.add(child_14);
        }
        else if(s.equals("12"))   
        {
            int[][] childf = new int[state.length][state[0].length];
            int[][] child15 = new int[state.length][state[0].length];
            int[][] child16 = new int[state.length][state[0].length];
            int[][] child17= new int[state.length][state[0].length];
            Node child_15 = new Node();
            Node child_16 = new Node();
            Node child_17 = new Node();

            for (int z = 0; z < childf.length; z++)
                childf[z] = Arrays.copyOf(state[z], state[z].length);
            for(int k = 0;k<3;k++)
            {
                for ( int l = 0;l<3;l++)
                {
                    if((k==1&&l==2)||(k==0&&l==2))
                    {
                        child15[1][2] = childf[0][2];
                        child15[0][2] = childf[1][2];
                        child_15.setAction(" Up");
                    }
                    else
                    {
                        child15[k][l] = childf[k][l];
                    }
                }
            }
            child_15.setState(child15);
            child_15.setAncestor(node);
            child_15.setH(manhattanDist(child15,goalMatrix));
            child_15.setG(child_15.getAncestor().getG() + 1);
            child_15.setF(child_15.getH()+child_15.getG());
            children.add(child_15);
            for(int k = 0;k<3;k++)
            {
                for ( int l = 0;l<3;l++)
                {
                    if((k==1&&l==2)||(k==1&&l==1))
                    {
                        child16[1][2] = childf[1][1];
                        child16[1][1] = childf[1][2];
                        child_16.setAction("Left");
                    }
                    else
                    {
                        child16[k][l] = childf[k][l];
                    }
                }
            }
            child_16.setState(child16);
            child_16.setAncestor(node);
            child_16.setH(manhattanDist(child16,goalMatrix));
            child_16.setG(child_16.getAncestor().getG() + 1);
            child_16.setF(child_16.getH()+child_16.getG());
            children.add(child_16);
            for(int k = 0;k<3;k++)
            {
                for ( int l = 0;l<3;l++)
                {
                    if((k==1&&l==2)||(k==2&&l==2))
                    {
                        child17[1][2] = childf[2][2];
                        child17[2][2] = childf[1][2];
                        child_17.setAction("Down");
                    }
                    else
                    {
                        child17[k][l] = childf[k][l];
                    }
                }
            }
            child_17.setState(child17);
            child_17.setAncestor(node);
            child_17.setH(manhattanDist(child17,goalMatrix));
            child_17.setG(child_17.getAncestor().getG() + 1);
            child_17.setF(child_17.getH()+child_17.getG());
            children.add(child_17);
        }
        else if(s.equals("20"))    
        {
            int[][] childg = new int[state.length][state[0].length];
            int[][] child18 = new int[state.length][state[0].length];
            int[][] child19 = new int[state.length][state[0].length];
            Node child_18 = new Node();
            Node child_19 = new Node();

            for (int z = 0; z < childg.length; z++)
                childg[z] = Arrays.copyOf(state[z], state[z].length);
            for(int k = 0;k<3;k++)
            {
                for ( int l = 0;l<3;l++)
                {
                    if((k==2&&l==0)||(k==1&&l==0))
                    {
                        child18[2][0] = childg[1][0];
                        child18[1][0] = childg[2][0];
                        child_18.setAction(" Up");
                    }
                    else
                    {
                        child18[k][l] = childg[k][l];
                    }
                }
            }
            child_18.setState(child18);
            child_18.setAncestor(node);
            child_18.setH(manhattanDist(child18,goalMatrix));
            child_18.setG(child_18.getAncestor().getG() + 1);
            child_18.setF(child_18.getH()+child_18.getG());
            children.add(child_18);
            for(int k = 0;k<3;k++)
            {
                for ( int l = 0;l<3;l++)
                {
                    if((k==2&&l==0)||(k==2&&l==1))
                    {
                        child19[2][0] = childg[2][1];
                        child19[2][1] = childg[2][0];
                        child_19.setAction("Right");
                    }
                    else
                    {
                        child19[k][l] = childg[k][l];
                    }
                }
            }
            child_19.setState(child19);
            child_19.setAncestor(node);
            child_19.setH(manhattanDist(child19,goalMatrix));
            child_19.setG(child_19.getAncestor().getG() + 1);
            child_19.setF(child_19.getH()+child_19.getG());
            children.add(child_19);
        }
        else if(s.equals("21"))  
        {
            int[][] childh = new int[state.length][state[0].length];
            int[][] child20 = new int[state.length][state[0].length];
            int[][] child21 = new int[state.length][state[0].length];
            int[][] child22 = new int[state.length][state[0].length];
            Node child_20 = new Node();
            Node child_21 = new Node();
            Node child_22 = new Node();

            for (int z = 0; z < childh.length; z++)
                childh[z] = Arrays.copyOf(state[z], state[z].length);
            for(int k = 0;k<3;k++)
            {
                for ( int l = 0;l<3;l++)
                {
                    if((k==2&&l==1)||(k==2&&l==0))
                    {
                        child20[2][1] = childh[2][0];
                        child20[2][0] = childh[2][1];
                        child_20.setAction("Left");
                    }
                    else
                    {
                        child20[k][l] = childh[k][l];
                    }
                }
            }
            child_20.setState(child20);
            child_20.setAncestor(node);
            child_20.setH(manhattanDist(child20,goalMatrix));
            child_20.setG(child_20.getAncestor().getG() + 1);
            child_20.setF(child_20.getH()+child_20.getG());
            children.add(child_20);
            for(int k = 0;k<3;k++)
            {
                for ( int l = 0;l<3;l++)
                {
                    if((k==2&&l==1)||(k==2&&l==2))
                    {
                        child21[2][1] = childh[2][2];
                        child21[2][2] = childh[2][1];
                        child_21.setAction("Right");
                    }
                    else
                    {
                        child21[k][l] = childh[k][l];
                    }
                }
            }
            child_21.setState(child21);
            child_21.setAncestor(node);
            child_21.setH(manhattanDist(child21,goalMatrix));
            child_21.setG(child_21.getAncestor().getG() + 1);
            child_21.setF(child_21.getH()+child_21.getG());
            children.add(child_21);
            for(int k = 0;k<3;k++)
            {
                for ( int l = 0;l<3;l++)
                {
                    if((k==2&&l==1)||(k==1&&l==1))
                    {
                        child22[2][1] = childh[1][1];
                        child22[1][1] = childh[2][1];
                        
                    }
                    else
                    {
                        child22[k][l] = childh[k][l];
                    }
                }
            }
            child_22.setState(child22);
            child_22.setAncestor(node);
            child_22.setH(manhattanDist(child22,goalMatrix));
            child_22.setG(child_22.getAncestor().getG() + 1);
            child_22.setF(child_22.getH()+child_22.getG());
            
            children.add(child_22);
            child_22.setAction(" Up");
        }
        else if(s.equals("22"))     
        {
            int[][] childi = new int[state.length][state[0].length];
            int[][] child23 = new int[state.length][state[0].length];
            int[][] child24 = new int[state.length][state[0].length];
            Node child_23 = new Node();
            Node child_24 = new Node();

            for (int z = 0; z < childi.length; z++)
                childi[z] = Arrays.copyOf(state[z], state[z].length);
            for(int k = 0;k<3;k++)
            {
                for ( int l = 0;l<3;l++)
                {
                    if((k==2&&l==2)||(k==1&&l==2))
                    {
                        child23[2][2] = childi[1][2];
                        child23[1][2] = childi[2][2];
                        child_23.setAction(" Up");
                    }
                    else
                    {
                        child23[k][l] = childi[k][l];
                    }
                }
            }
            child_23.setState(child23);
            child_23.setAncestor(node);
            child_23.setH(manhattanDist(child23,goalMatrix));
            child_23.setG(child_23.getAncestor().getG() + 1);
            child_23.setF(child_23.getH()+child_23.getG());
            children.add(child_23);
            for(int k = 0;k<3;k++)
            {
                for ( int l = 0;l<3;l++)
                {
                    if((k==2&&l==2)||(k==2&&l==1))
                    {
                        child24[2][2] = childi[2][1];
                        child24[2][1] = childi[2][2];
                        child_24.setAction("Left");
                    }
                    else
                    {
                        child24[k][l] = childi[k][l];
                    }
                }
            }
            child_24.setState(child24);
            child_24.setAncestor(node);
            child_24.setH(manhattanDist(child24,goalMatrix));
            child_24.setG(child_24.getAncestor().getG() + 1);
            child_24.setF(child_24.getH()+child_24.getG());
            children.add(child_24);
        }
        return children;
    }

    public static int manhattanDist(int[][] curr, int[][] goal)
        {
        int manhattan = 0;
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                for(int k =0;k<3;k++)
                {
                    for(int l =0;l<3;l++)
                    {
                        if (curr[i][j]== goal[k][l])
                        {
                            manhattan =manhattan + Math.abs(i-k)+Math.abs(j-l);
                        }
                    }
                }

            }
        }
        return manhattan;

    }

}
class CompareNode implements Comparator<Node>
{
    public int compare(Node a, Node b)
    {
        if (a.getF() > b.getF())
        {
            return 1;
        }
        if (a.getF() < b.getF())
        {
            return -1;
        }
        return 0;
    }
}
    class Node {
    public int f;
    public int g;
    public int h;
    public int[][] state = new int[3][3];
    public ArrayList<Node> successors;
    public Node ancestor;
    public String action = "";

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Node() {
    }
    public Node (int[][] inState){
        this.state = inState;
        this.f = 0;
        this.action = null;
        this.ancestor = null;
    }
    public int getF() {
        return f;
    }

    public void setF(int f) {
        this.f = f;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public int[][] getState() {
        return state;
    }

    public void setState(int[][] state) {
        this.state = state;
    }

    public ArrayList<Node> getSuccessors() {
        return successors;
    }

    public void setSuccessors(ArrayList<Node> successors) {
        this.successors = successors;
    }

    public Node getAncestor() {
        return ancestor;
    }

    public void setAncestor(Node ancestor) {
        this.ancestor = ancestor;
    }

    public void setF(int g, int h) {
        this.f = g+h;
    }
}

