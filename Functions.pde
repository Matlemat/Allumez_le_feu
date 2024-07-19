int[][] createDiscretizedCircle(int[][] result, int centerX, int centerY, int radius, int number) {
  // Full the cercle with number 
  for (int i = 0; i < m; i++) {
    for (int j = 0; j < n; j++) {
      float distance = dist(i, j, centerX, centerY);
      if (distance <= radius) {
        result[i][j] = number;
      }
    }
  }
  
  return result;
}

Arbre findArbre(ArrayList<Arbre> arr, int a, int b) {
  Arbre temp = new Arbre(-1,-1); 
  for (Arbre arbre_looked : arr) {
    if (arbre_looked.i == a && arbre_looked.j == b) {
      temp = arbre_looked;
    }
  }
  return temp;
}

