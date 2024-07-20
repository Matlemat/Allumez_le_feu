/* autogenerated by Processing revision 1293 on 2024-07-19 */
import processing.core.*;
import processing.data.*;
import processing.event.*;
import processing.opengl.*;

import java.util.HashMap;
import java.util.ArrayList;
import java.io.File;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;

public class Allumez_le_feu extends PApplet {


final int n = 200;
final int m = 200;

final int sizex = 800;
final int sizey = 800;

Case [][] board = new Case[n][m];
Arbre [][] foret = new Arbre[n][m];

int [][] plan = new int[n][m];
ArrayList<Case> storage_case = new ArrayList<Case>();
ArrayList<Arbre> storage_arbre = new ArrayList<Arbre>();

ArrayList<Arbre> infire_arbre = new ArrayList<Arbre>();

boolean paused = true;

public void setup() {
    /* size commented out by preprocessor */;
    background(255);
    for(int i = 0; i<n; i++ ){
        for(int j = 0; j<n; j++){
            plan[i][j] = 1;
        }
    }
    // plan = createDiscretizedCircle(plan,m/2, n/2, 5, 0);
    plan = createlinecol(plan, n/2, 0);
    plan[n/2][m/2] = 1;
    for(int i = 0; i<n; i++){
        for(int j = 0; j<n;j++){
            switch (plan[i][j]) {
                case 0:
                    storage_case.add(new Case(i,j));
                    break;
                case 1:
                    storage_arbre.add(new Arbre(i,j));
                    break;
            }
        }
    }
    findArbre(storage_arbre,18,18).burning = true;
}

public void draw() {
    background(255);

    // Display
    // for(int i = 0; i<n; i++ ){
    //     for(int j = 0; j<n; j++){
    //         foret[i][j].update_burn();
    //         foret[i][j].update_color();
    //         foret[i][j].show();
    //     }
    // } 
    for (Case case_looked : storage_case){
        case_looked.show();
    }
    for (Arbre arbre_looked : storage_arbre){
        if (!paused){
            arbre_looked.update_propagation();
            arbre_looked.update_burn();
            arbre_looked.update_color();
        }
        arbre_looked.show();
    }


    // Line 
    stroke(255);
    for (int i = 0; i<n; i++){ 
        line(PApplet.parseFloat(i) * (PApplet.parseFloat(sizex)/PApplet.parseFloat(n)),0 ,PApplet.parseFloat(i) * (PApplet.parseFloat(sizex)/PApplet.parseFloat(n)), height);
    }
    for (int j = 0; j<n; j++){
        line(0,PApplet.parseFloat(j) * (PApplet.parseFloat(sizey)/PApplet.parseFloat(n)),width,PApplet.parseFloat(j) * (PApplet.parseFloat(sizey)/PApplet.parseFloat(n)));
    }

}

public void keyPressed() {
    if (key == ' '){
        paused = !paused;
    }
}
class Arbre extends Case {

    float duration;
    boolean burning;
    boolean intense; 

    // proba_propagate = [up,right,down,left]
    float[] proba_propagate;

    Arbre (int i0, int j0) {
        super(i0,j0);
        this.combustible = true;

        this.duration = abs(randomGaussian()*100);
        
        this.burning = false;
        this.intense = (random(0,10) > 3);

        this.proba_propagate = new float[] {0.05f,0.05f,0.05f,0.05f};
    }
    
    public void update_color(){
        if (this.duration > 0 && !burning){
             this.c = lerpColor(color(0,255,0), color(0,100,0), this.duration/(2*100));
        }else{
            if (burning){
                if (intense){this.c = color(127,0,0);}else{this.c = color(255,0,0);}
            }else{
                this.c = color(112,112,112);
            }
        }
    }

    public void update_burn(){
        if (burning){
            if (duration > 0){
                if (intense){
                    this.duration -= 2;
                }else{
                    this.duration -= 1;
                }
            }else{this.burning = false;}
        }
    }

    public void update_propagation(){
        if (this.burning){
            if ((random(0,1)<proba_propagate[3]) && (!this.borders[3])){
                if ((plan[this.i -1][this.j] == 1)){
                    Arbre temp = findArbre(storage_arbre,this.i - 1, this.j);
                    temp.burning = true;
                }
            }
            if ((random(0,1)<proba_propagate[1]) && (!this.borders[1])){
                if ((plan[this.i + 1][this.j] == 1)){
                    Arbre temp = findArbre(storage_arbre,this.i + 1, this.j);
                    temp.burning = true;
                }
            }
            if ((random(0,1)<proba_propagate[0]) && (!this.borders[0])){
                if ((plan[this.i][this.j-1] == 1)){
                    Arbre temp = findArbre(storage_arbre,this.i, this.j-1);
                    temp.burning = true;
                }
            }
            if ((random(0,1)<proba_propagate[2]) && (!this.borders[2])){
                if ((plan[this.i ][this.j+1] == 1)){
                    Arbre temp = findArbre(storage_arbre,this.i , this.j+1);
                    temp.burning = true;
                }
            }
        }   

    }
}
class Case {

    // Coord
    protected int i;
    protected int j;

    protected float x;
    protected float y; 

    // borders = [up, right, down, left]
    protected boolean[] borders = new boolean[4];

    // Propreties 
    int c;
    boolean combustible;

    Case (int i0,int j0) {
        this.i = i0;
        this.j = j0;
        this.c = color(0,0,0);

        this.x = PApplet.parseFloat(this.i) * (PApplet.parseFloat(sizex)/PApplet.parseFloat(n));
        this.y = PApplet.parseFloat(this.j) * (PApplet.parseFloat(sizey)/PApplet.parseFloat(n));

        if(i == 0){
            this.borders[3] = true;
        }
        if(i == (n-1)){
            this.borders[1] = true;
        }
        if (j == 0){
            this.borders[0] = true;
        }
        if (j== (n-1)){
            this.borders[2] = true;
        }

        this.combustible = false;
        
    }


    public void show(){
        noStroke();
        fill(c);
        rect(this.x, this.y, (PApplet.parseFloat(sizex)/PApplet.parseFloat(n)), (PApplet.parseFloat(sizey)/PApplet.parseFloat(n)));
    }
}
public int[][] createDiscretizedCircle(int[][] result, int centerX, int centerY, int radius, int number) {
  // Full the cercle with number 
  for (int i = 0; i < n; i++) {
    for (int j = 0; j < m; j++) {
      float distance = dist(i, j, centerX, centerY);
      if (distance <= radius) {
        result[i][j] = number;
      }
    }
  }
  
  return result;
}

public int[][] createlinecol(int[][] result, int i0, int number){
  for (int i = 0; i< n; i++){
    for (int j = 0; j<m; j++ ){
      if(i == i0){
        result[i][j] = number;
      }
    }
  }
  return result;
}

public Arbre findArbre(ArrayList<Arbre> arr, int a, int b) {
  Arbre temp = new Arbre(-1,-1); 
  for (Arbre arbre_looked : arr) {
    if (arbre_looked.i == a && arbre_looked.j == b) {
      temp = arbre_looked;
    }
  }
  return temp;
}


  public void settings() { size(800, 800); }

  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "Allumez_le_feu" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}