import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ChessBoard extends JFrame
{
	JPanel p = new JPanel();
	int row = 8;
	int col = 8;
	
	//*----- TEMPORARY VARIABLES ------*//
	//* These values are changed whenever a chesspiece is clicked in order to keep track of all actions */
	int currentX; 
	int currentY;
	String currentChessType;
	ImageIcon currentChessIcon;
	int currentPlayer;
	boolean chessIsClicked = false; 
	
	
	ChessSquare squares[][] = new ChessSquare[row][col];
	SquareHandler sHandler = new SquareHandler();
	private String ChessType[] = {"NONE", "PAWN", "ROOK", "KNIGHT", "BISHOP", "QUEEN", "KING"};
	private ImageIcon ChessIcon[] = {
														new ImageIcon("images/EmptySquare.jpg"), 
														new ImageIcon("images/Pawn.jpg"), 
														new ImageIcon("images/Rook.jpg"), 
														new ImageIcon("images/Knight.jpg"), 
														new ImageIcon("images/Bishop.jpg"), 
														new ImageIcon("images/Queen.jpg"), 
														new ImageIcon("images/King.jpg"),
														new ImageIcon("images/SelectedSquare.jpg")
														};
	public static void main(String args[])
	{
		new ChessBoard();
	}
	
	public ChessBoard()
	{
		//*----- GAME WINDOW SETTINGS ------*//
		//* These are the settings of the window.  */
		setTitle("Chess");
		setSize(370,370);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		p.setLayout(new GridLayout(row,col));
		
		for(int x=row-1;x>=0;x--){
			for(int y=0;y<col;y++){
				squares[x][y] = new ChessSquare(x,y, ChessType[0], ChessIcon[0], 0, false);
				
				if(x==1){ 
					squares[x][y] = new ChessSquare(x,y, ChessType[1], ChessIcon[1], 1, false);
				}
				if((x==0 && y==0) || (x==0 && y==7)){ 
					squares[x][y] = new ChessSquare(x,y, ChessType[2], ChessIcon[2], 1, false);
				}
				if((x==0 && y==1) || (x==0 && y==6)){ 
					squares[x][y] = new ChessSquare(x,y, ChessType[3], ChessIcon[3], 1, false);
				}
				if((x==0 && y==2) || (x==0 && y==5)){ 
					squares[x][y] = new ChessSquare(x,y, ChessType[4], ChessIcon[4], 1, false);
				}
				if(x==0 && y==3){ 
					squares[x][y] = new ChessSquare(x,y, ChessType[5], ChessIcon[5], 1, false);
				}
				if(x==0 && y==4){ 
					squares[x][y] = new ChessSquare(x,y, ChessType[6], ChessIcon[6], 1, false);
				}
				squares[x][y].addActionListener(sHandler);
				p.add(squares[x][y]);
			}
		}
		add(p);
		setVisible(true);
	}
	
	private class SquareHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			for(int x=row-1;x>=0;x--){
				for(int y=0;y<col;y++){
					if(e.getSource() == squares[x][y]){
						if(chessIsClicked==false){
							if(squares[x][y].getPlayer()==1){
								updateTempValues(x, y, squares[x][y].getChessType(), squares[x][y].getChessIcon(), squares[x][y].getPlayer());
								chessIsClicked = true;
								//*----- PAWN VALID MOVES ------*//
								if(currentChessType==ChessType[1] && x<row){
									showSelected(x, y, 1, 0);
									if(x==1 && squares[x+1][y].getPlayer()!=1){
										showSelected(x, y, 2, 0);
									}
								}
								//*----- ROOK VALID MOVES ------*//
								if(currentChessType==ChessType[2]){
									// System.out.println("Available Steps: " + calculateAvailableSteps(x, y, "UP"));
									for(int i=0;i<calculateAvailableSteps(x, y, "UP");i++){
										showSelected(x, y, i, 0);
									}
									// System.out.println("Available Steps Downward: " + calculateAvailableSteps(x, y, "DOWN"));
									for(int i=0;i<calculateAvailableSteps(x, y, "DOWN");i++){
										showSelected(x, y, -i, 0);
									}
									// System.out.println("Available Steps Downward: " + calculateAvailableSteps(x, y, "DOWN"));
									for(int i=0;i<calculateAvailableSteps(x, y, "RIGHT");i++){
										showSelected(x, y, 0, i);
									}
									// System.out.println("Available Steps Downward: " + calculateAvailableSteps(x, y, "DOWN"));
									for(int i=0;i<calculateAvailableSteps(x, y, "LEFT");i++){
										showSelected(x, y, 0, -i);
									}
								}
								//*----- KNIGHT VALID MOVES ------*//
								if(currentChessType==ChessType[3]){
									showSelected(x, y, 2, -1); 
									showSelected(x, y, 1, -2); 
									showSelected(x, y, 2, 1); 
									showSelected(x, y, 1, 2); 
									showSelected(x, y, -2, -1); 
									showSelected(x, y, -1, -2); 
									showSelected(x, y, -2, 1); 
									showSelected(x, y, -1, 2); 
								}
								//*----- BISHOP VALID MOVES ------*//
								if(currentChessType==ChessType[4]){
									for(int i=0;i<calculateAvailableSteps(x, y, "UP_LEFT");i++){
										showSelected(x, y, i, -i); 
									}
									for(int i=0;i<calculateAvailableSteps(x, y, "UP_RIGHT");i++){
										showSelected(x, y, i, i); 
									}
									for(int i=0;i<calculateAvailableSteps(x, y, "DOWN_LEFT");i++){
										showSelected(x, y, -i, -i);
									}
									for(int i=0;i<calculateAvailableSteps(x, y, "DOWN_RIGHT");i++){
										showSelected(x, y, -i, i);
									}
								}
								//*----- QUEEN VALID MOVES ------*//
								if(currentChessType==ChessType[5]){
									for(int i=0;i<calculateAvailableSteps(x, y, "UP");i++){
										showSelected(x, y, i, 0);
									}
									// System.out.println("Available Steps Downward: " + calculateAvailableSteps(x, y, "DOWN"));
									for(int i=0;i<calculateAvailableSteps(x, y, "DOWN");i++){
										showSelected(x, y, -i, 0);
									}
									// System.out.println("Available Steps Downward: " + calculateAvailableSteps(x, y, "DOWN"));
									for(int i=0;i<calculateAvailableSteps(x, y, "RIGHT");i++){
										showSelected(x, y, 0, i);
									}
									// System.out.println("Available Steps Downward: " + calculateAvailableSteps(x, y, "DOWN"));
									for(int i=0;i<calculateAvailableSteps(x, y, "LEFT");i++){
										showSelected(x, y, 0, -i);
									}
									for(int i=0;i<calculateAvailableSteps(x, y, "UP_LEFT");i++){
										showSelected(x, y, i, -i); 
									}
									for(int i=0;i<calculateAvailableSteps(x, y, "UP_RIGHT");i++){
										showSelected(x, y, i, i); 
									}
									for(int i=0;i<calculateAvailableSteps(x, y, "DOWN_LEFT");i++){
										showSelected(x, y, -i, -i);
									}
									for(int i=0;i<calculateAvailableSteps(x, y, "DOWN_RIGHT");i++){
										showSelected(x, y, -i, i);
									}
								}
								//*----- KING VALID MOVES ------*//
								if(currentChessType==ChessType[6]){
										showSelected(x, y, 1, -1);
										showSelected(x, y, 1, 0);
										showSelected(x, y, 1, 1);
										showSelected(x, y, 0, -1);
										showSelected(x, y, 0, 1);
										showSelected(x, y, -1, -1);
										showSelected(x, y, -1, 0);
										showSelected(x, y, -1, 1);
								}
							}
							//Logs
							System.out.println("Clicked: (" + x + "," +  y + ") | ChessType: " + squares[x][y].getChessType() + " | ChessIsClicked: " + chessIsClicked + " | Player: " + squares[x][y].getPlayer());
						//IF ChessIsClicked == TRUE
						}else{
							//if currentPlayer == 0, then it must be a square, not a chess piece
							//if currentPlayer == 1, then it must be a chess piece
							if(currentPlayer!=squares[x][y].getPlayer()){
								if(squares[x][y].canMoveTo(squares[x][y].getXPosition(), squares[x][y].getYPosition())==true){
									emptySquare(currentX, currentY);
									squares[x][y].moveTo(x, y, currentChessType, currentChessIcon, currentPlayer);
									chessIsClicked = false;
								}
							}
							//Check if clicked on other chesses, if so, cancel movement
							if(squares[currentX][currentY].getPlayer()==1){
								chessIsClicked=false;
								System.out.println("Action Cancelled - Move to " + squares[x][y].getChessType() + "(" + squares[x][y].getXPosition() + "," + squares[x][y].getYPosition() +  ") is invalid.");
							}
							//Remove all selected squares
							removeSelected(row, col);
						}
					}
				}
			}
		}
	}
	
	private void emptySquare(int currentX, int currentY){
		squares[currentX][currentY].setChessIcon(ChessIcon[0]);
		squares[currentX][currentY].setIcon(ChessIcon[0]);
		squares[currentX][currentY].setChessType(ChessType[0]);
		squares[currentX][currentY].setPlayer(0);
	}
	
	private void updateTempValues(int x, int y, String ChessType, ImageIcon ChessIcon, int player){
		currentX = x;
		currentY = y;
		currentChessType = ChessType;
		currentChessIcon = ChessIcon;
		currentPlayer = player;
	}
	
	private void removeSelected(int row, int col){
		for(int i=row-1;i>=0;i--){
			for(int j=0;j<col;j++){
				if(squares[i][j].getPlayer()==0){
					squares[i][j].setIcon(ChessIcon[0]);
					squares[i][j].setSelected(false);
				}
			}
		}
	}
	
	private void showSelected(int x, int y, int a, int b){
		int nX = x + a;
		int nY = y + b;
		if(nX<row && nX>=0 && nY<col && nY>=0){
			if(squares[nX][nY].getPlayer()==0){
				squares[nX][nY].setIcon(ChessIcon[7]);
				squares[nX][nY].setSelected(true);
			}
		}
	}
	
	private int calculateAvailableSteps(int x, int y, String Direction){
		int availableSteps = 0;
		boolean collisionFound = false;
		int distance=0; //Distance between row/col and the chess X/Y positions
		int CollisionDistance=0; //Distance between row/col and the chess X/Y positions
		
		//*----- BOUNDARIES VALIDATION ------*//
		/* EVERY SQUARE UPWARD*/
		if(Direction=="UP"){
		distance = row-squares[x][y].getXPosition()-1;
			for(int i=1;i<=distance;i++){
				if(squares[x+i][y].getChessType()!=ChessType[0]){
					collisionFound = true;
					availableSteps = squares[x+i][y].getXPosition() - squares[x][y].getXPosition();
					break;
				}
			}
			if(collisionFound==false){
				availableSteps = distance+1;
			}
		}
		
		if(Direction=="RIGHT"){
		distance = row-squares[x][y].getYPosition()-1;
			for(int i=1;i<=distance;i++){
				if(squares[x][y+i].getChessType()!=ChessType[0]){
					collisionFound = true;
					availableSteps = squares[x][y+i].getYPosition() - squares[x][y].getYPosition();
					break;
				}
			}
			if(collisionFound==false){
				availableSteps = distance+1;
			}
		}
		
		if(Direction=="DOWN"){
		distance = row - (row-squares[x][y].getXPosition()-1);
			for(int i=1;i<=distance-1;i++){
				if(squares[x-i][y].getChessType()!=ChessType[0]){
					collisionFound = true;
					availableSteps = distance - squares[x-i][y].getXPosition();
					break;
				}
			}
			if(collisionFound==false){
				availableSteps = distance;
			}
		}

		if(Direction=="LEFT"){
		distance = row - (row-squares[x][y].getYPosition()-1);
			for(int i=1;i<=distance-1;i++){
				if(squares[x][y-i].getChessType()!=ChessType[0]){
					// System.out.println(x-i);
					collisionFound = true;
					availableSteps = distance - squares[x][y-i].getYPosition();
					break;
				}
			}
			if(collisionFound==false){
				availableSteps = distance;
			}
		}
		
		if(Direction=="UP_LEFT"){
			while((x+distance)<row && (y-distance>=0) && squares[x+distance][y-distance].getChessType()!=null){
					distance++;
			}
			for(int i=1;i<=distance-1;i++){
					if(squares[x+i][y-i].getChessType()!=ChessType[0]){
						collisionFound = true;
							while(((x+i)+CollisionDistance)<row && ((y-i)-CollisionDistance>=0) && squares[(x+i)+CollisionDistance][(y-i)-CollisionDistance].getChessType()!=null){
									CollisionDistance++;
							}
						availableSteps = distance - CollisionDistance;
						break;
					}
			}
			if(collisionFound==false){
				availableSteps = distance;
			}
		}
		
		if(Direction=="UP_RIGHT"){
			while((x+distance)<row && (y+distance<row) && squares[x+distance][y+distance].getChessType()!=null){
					distance++;
			}
			for(int i=1;i<=distance-1;i++){
					if(squares[x+i][y+i].getChessType()!=ChessType[0]){
						collisionFound = true;
							while(((x+i)+CollisionDistance)<row && ((y+i)+CollisionDistance<row) && squares[(x+i)+CollisionDistance][(y+i)+CollisionDistance].getChessType()!=null){
									CollisionDistance++;
							}
						availableSteps = distance - CollisionDistance;
						break;
					}
			}
			if(collisionFound==false){
				availableSteps = distance;
			}
		}
		
		if(Direction=="DOWN_LEFT"){
			while((x-distance)>=0 && (y-distance>=0) && squares[x-distance][y-distance].getChessType()!=null){
					distance++;
			}
			for(int i=1;i<=distance-1;i++){
					if(squares[x-i][y-i].getChessType()!=ChessType[0]){
						collisionFound = true;
							while(((x-i)-CollisionDistance)>=0 && ((y-i)-CollisionDistance>=0) && squares[(x-i)-CollisionDistance][(y-i)-CollisionDistance].getChessType()!=null){
									CollisionDistance++;
							}
						availableSteps = distance - CollisionDistance;
						break;
					}
			}
			if(collisionFound==false){
				availableSteps = distance;
			}
		}
		
		if(Direction=="DOWN_RIGHT"){
			while((x-distance)>=0 && (y+distance<row) && squares[x-distance][y+distance].getChessType()!=null){
					distance++;
			}
			for(int i=1;i<=distance-1;i++){
					if(squares[x-i][y+i].getChessType()!=ChessType[0]){
						collisionFound = true;
							while(((x-i)-CollisionDistance)>=0 && ((y+i)+CollisionDistance)<row && squares[(x-i)-CollisionDistance][(y+i)+CollisionDistance].getChessType()!=null){
									CollisionDistance++;
							}
							System.out.println(CollisionDistance);
						availableSteps = distance - CollisionDistance;
						break;
					}
			}
			if(collisionFound==false){
				availableSteps = distance;
			}
		}
			return availableSteps;
	}
}