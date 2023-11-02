public class Position {
    private int x;
    private int y;

    /**
     * Constructeur de la classe Position
     * @param x Donne la valeur en abscisse
     * @param y Donne la valeur en ordonne
     */
    Position(int x, int y) {
        this.x = x;
        this.y = y;
    }


    /**
     * Permet d'ajouter une position a une autre
     * @param p Donne une position a additioner
     */
    public void add(Position p){
        this.x += p.x;
        this.y += p.y;
    }

    /**
     * Permet de soustraire une position a une autre
     * @param p Donne une posiiton a soustraire
     */
    public void substract(Position p){
        this.x -= p.x;
        this.y -= p.y;
    }

    /**
     * Permet de totalement inverser une positon
     * Surtout utiliser pour inverser les vecteurs directeurs
     */
    public void opposite(){
        this.x = -this.x;
        this.y = -this.y;
    }

    /**
     * Permet d'inverser l'ordonne d'une position
     * Surtout utiliser pour inverser les vecteurs directeurs
     */
    public void oppositeY(){
        this.y = -this.y;
    }

     /**
     * Permet d'inverser l'abscisse d'une position
     * Surtout utiliser pour inverser les vecteurs directeurs
     */
    public void oppositeX(){
        this.x = -this.x;
    }

    
    /** 
     * Getter pour la valeur en abscisse
     * @return int Retourne la veleur de l'abscisse
     */
    public int getX(){
        return this.x;
    }
    
    
    /** 
     * Getter pour la valeur en ordonne
     * @return int Retourne la veleur de l'ordonne
     */
    public int getY(){
        return this.y;
    }

    
    /** 
     * Permet de verifier si une position est egal a une autre
     * @param p Donne une position a verifier
     * @return boolean Retourne vrai si les position sont les meme
     */
    public boolean equal(Position p){
        return (this.x == p.x) && (this.y == p.y);
    }

    /**
     * Methode toString de la classe Position
     */
    public String toString() {
        return ("[" + x + "," + y + "]");
    }
}
