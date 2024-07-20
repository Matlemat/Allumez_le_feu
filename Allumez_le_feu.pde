
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

void setup() {
    size(800,800);
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

void draw() {
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
        line(float(i) * (float(sizex)/float(n)),0 ,float(i) * (float(sizex)/float(n)), height);
    }
    for (int j = 0; j<n; j++){
        line(0,float(j) * (float(sizey)/float(n)),width,float(j) * (float(sizey)/float(n)));
    }

}

void keyPressed() {
    if (key == ' '){
        paused = !paused;
    }
}