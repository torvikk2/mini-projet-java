
public class Defence {
    private int armor;
    private int dodge;

    /**
     * Constructeur de la classe Defence
     * @param armor Donne l'armure du personnage
     * @param dodge Donne l'esquive du personnage
     */
    Defence(int armor, int dodge) {
        this.armor = armor;
        this.dodge = dodge;
    }
    
    /** 
     * Methode toString pour les statistiques de defense
     * @return String
     */
    public String toString() {
        return ("(Armor:" + armor + ",Dodge:" + dodge + ")");
    }

    
    /** 
     * Getter pour l'armure
     * @return int
     */
    public int getArmor(){
        return(this.armor);
    }

    
    /** 
     * Getter pour l'esquive
     * @return int
     */
    public int getDodge(){
        return(this.dodge);
    }
}
