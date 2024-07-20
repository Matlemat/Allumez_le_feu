class Case {

    // Coord
    protected int i;
    protected int j;

    protected float x;
    protected float y; 

    // borders = [up, right, down, left]
    protected boolean[] borders = new boolean[4];

    // Propreties 
    color c;
    boolean combustible;

    Case (int i0,int j0) {
        this.i = i0;
        this.j = j0;
        this.c = color(0,0,0);

        this.x = float(this.i) * (float(sizex)/float(n));
        this.y = float(this.j) * (float(sizey)/float(n));

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


    void show(){
        noStroke();
        fill(c);
        rect(this.x, this.y, (float(sizex)/float(n)), (float(sizey)/float(n)));
    }
}
