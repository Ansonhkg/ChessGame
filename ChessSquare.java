import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ChessSquare extends JButton
{
	//*----- INSTANCE VARIABLES ------*//
	//--------------------------------------
	private int xPosition;
	private int yPosition;
	private int player;
	private String chessType;
	private ImageIcon chessIcon;
	private boolean selected;
	
	//CONSTRUCTOR----------
	//------------------------------
	public ChessSquare(int setX, int setY, String setChessType, ImageIcon setChessIcon, int setPlayer, boolean setSelected){
			xPosition = setX;
			yPosition = setY;
			chessType = setChessType;
			chessIcon = setChessIcon;
			setIcon(chessIcon);
			player = setPlayer;
			selected = setSelected;
	}
	//ACCESSORS----------
	//-------------------------
	public int getXPosition(){
		return xPosition;
	}
	public int getYPosition(){
		return yPosition;
	}
	public String getChessType(){
		return chessType;
	}
	public ImageIcon getChessIcon(){
		return chessIcon;
	}
	public int getPlayer(){
		return player;
	}
	public boolean getSelected(){
		return selected;
	}
	//MUTATORS----------
	//-------------------------
	public void setXPosition(int x){
		this.xPosition = x;
	}
	public void setYPosition(int y){
		this.yPosition = y;
	}
	public void setChessType(String setChessType){
		this.chessType = setChessType;
	}
	public void setChessIcon(ImageIcon setChessIcon){
		this.chessIcon = setChessIcon;
	}
	public void setPlayer(int setPlayer){
		this.player = setPlayer;
	}
	public void setSelected(boolean setSelected){
		this.selected = setSelected;
	}
	//METHODS----------
	//-----------------------
	public void moveTo(int x, int y, String currentChessType, ImageIcon currentChessIcon, int currentPlayer){
		this.setXPosition(x);
		this.setYPosition(y);
		this.setChessIcon(currentChessIcon);
		this.setIcon(chessIcon);
		this.setChessType(currentChessType);
		this.setPlayer(currentPlayer);
	}
	public boolean canMoveTo(int x, int y){
		if(this.getSelected() && this.getSelected()){
			return true;
		}
		return false;
	}
}