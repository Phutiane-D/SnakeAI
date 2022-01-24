import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MyAgent extends za.ac.wits.snake.MyAgent {
	
    public static void main(String args[]) throws IOException {
        MyAgent agent = new MyAgent();
        MyAgent.start(agent, args);
    
    }
    
    @Override
    public void run() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String initString = br.readLine();
            String[] temp = initString.split(" ");
            int nSnakes = Integer.parseInt(temp[0]);

            
            while (true) {
            	int board[][] = new int[50][50];
            	
                String line = br.readLine();
                if (line.contains("Game Over")) {
                    break;
                }

                String[] apple = line.split(" "); 
                int appleX = Integer.parseInt(apple[0]);
                int appleY = Integer.parseInt(apple[1]);
                node AppleN = new node(appleX, appleY);
                
                
                int nObstacles = 3;
                for (int obstacle = 0; obstacle < nObstacles; obstacle++) {
                    String obs = br.readLine();
                    String[] Obs = obs.split(" ");
                    for(String k : Obs)
                    {
                    	String[] Obs_coord = k.split(",");
                    	int x = Integer.parseInt(Obs_coord[0]);
                    	int y = Integer.parseInt(Obs_coord[1]);
                    	board[x][y] = -1;
                    }
                   
                }
         
                int mySnakeNum = Integer.parseInt(br.readLine());          
                node snakehead = null;
                node snakeneck = null;
                for (int i = 0; i < nSnakes; i++) {
                    String snakeLine = br.readLine();
                    String[] snakes = snakeLine.split(" ");
                    if (i == mySnakeNum) 
                    {
                    	String[] snake_coord = snakes[3].split(",");
                    	int x = Integer.parseInt(snake_coord[0]);
                    	int y = Integer.parseInt(snake_coord[1]);
                    	snakehead = new node(x,y);
                    	snake_coord = snakes[4].split(",");
                    	int X = Integer.parseInt(snake_coord[0]);
                    	int Y = Integer.parseInt(snake_coord[1]);
                    	snakeneck = new node(X,Y);
	                    
                    }                 
                    for(int j = 3; j < snakes.length; j++)
                    {
                    	String[] snake_coord = snakes[j].split(",");
                    	int x = Integer.parseInt(snake_coord[0]);
                    	int y = Integer.parseInt(snake_coord[1]);
                    	board[x][y] = -1;
                    }
                    
                }
                //time to move
                // 0 up  rel. b
                // 1 down  rel. b
                // 2 left  rel. b
                // 3 right rel. b
                
                // 4 left rel. head
                // 5 straight rel. head
                // 6 right  rel. head
                Move(snakehead, snakeneck, AppleN);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //time to move
    // 0 up  rel. b
    // 1 down  rel. b
    // 2 left  rel. b
    // 3 right rel. b
    
    // 4 left rel. head
    // 5 straight rel. head
    // 6 right  rel. head
	private void Move(node head, node neck, node apple) {
		if(head.x < apple.x) {
			 
		}
		
	}
}