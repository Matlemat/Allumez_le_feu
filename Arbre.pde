class Arbre extends Case {

    float duration;
    boolean burning;
    boolean intense; 

    // proba_propagate = [up,right,down,left]
    float[] proba_propagate;

    Arbre (int i0, int j0) {
        super(i0,j0);
        this.combustible = true;

<<<<<<< HEAD
        this.duration = abs(randomGaussian()*100);
=======
        this.duration = abs(randomGaussian()*50);
>>>>>>> b449a8fe936b7ea926d71c1a9e80542c1860ddb0
        
        this.burning = false;
        this.intense = (random(0,10) > 3);

        this.proba_propagate = new float[] {0.05,0.05,0.05,0.05};
    }
    
    void update_color(){
        if (this.duration > 0 && !burning){
<<<<<<< HEAD
             this.c = lerpColor(color(0,255,0), color(0,100,0), this.duration/(2*100));
=======
             this.c = lerpColor(color(0,255,0), color(0,100,0), this.duration/(2*50));
>>>>>>> b449a8fe936b7ea926d71c1a9e80542c1860ddb0
        }else{
            if (burning){
                if (intense){this.c = color(127,0,0);}else{this.c = color(255,0,0);}
            }else{
                this.c = color(112,112,112);
            }
        }
    }

    void update_burn(){
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

    void update_propagation(){
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
