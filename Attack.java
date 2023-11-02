
public class Attack {
    private int hability;
    private int strengh;


    /**
     * Constructeur de la classe Attack
     * @param hability Donne l'habilite du personnage
     * @param strengh Donne la force du personnage
     */
    Attack(int hability, int strengh) {
        this.hability = hability;
        this.strengh = strengh;
    }
    
    /** 
     * Methode toString pour les statistiques d'attaques
     * @return String
     */
    public String toString() {
        return ("(Hability:" + hability + ",Strenght:" + strengh + ")");
    }

    
    /** 
     * Getter pour l'habilite
     * @return int
     */
    public int getHability(){
        return(this.hability);
    }

    
    /** 
     * Getter pour la force
     * @return int
     */
    public int getStrengh(){
        return(this.strengh);
    }
}
