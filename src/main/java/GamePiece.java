import javafx.scene.control.Button;

public class GamePiece extends Button{
    String buttonNumber;
    int rowNum;
    int columnNum;
    String buttonColor;

    GamePiece(){
        buttonNumber = null;
        rowNum = 0; //start columns from 1
        columnNum = 0;
        buttonColor = null; //actual game piece is yellow, blank button is black
    }

    public String getButtonNumber(){
        return this.buttonNumber;
    }

    public void setButtonNumber(String num){
        this.buttonNumber = num;
    }

    public int getRowNum(){
        return this.rowNum;
    }

    public void setRowNum(int rowNum){
        this.rowNum = rowNum;
    }

    public int getColumnNum(){
        return this.columnNum;
    }

    public void setColumnNum(int columnNum){
        this.columnNum = columnNum;
    }

    public String getButtonColor(){
        return this.buttonColor;
    }

    public void setButtonColor(String color){
        this.buttonColor= color;
    }


}
