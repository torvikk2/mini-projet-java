
public class Health {
    private int life;
    private int resurection;

    /**
     * Constructeur de la classe Health
     * @param life Donne les points de vies du personnage
     * @param resurection Donne la valeur de la resurrection
     */
    Health(int life, int resurection) {
        this.life = life;
        this.resurection = resurection;
    }
    
    /** 
     * Methode toString pour les statistiques de vies
     * @return String
     */
    public String toString() {
        return ("(Life:" + life + " ,Resurection:" + resurection + ")");
    }

    /** 
     * Permet de calculer les points de vies restants
     * @param damage
     */
    public void substractLife(int damage){
        if(this.life - damage>0){
            this.life -= damage;
        }else{
            this.life -= damage;
            substractResurection();
        }
    }

    /** 
     * Permet de calculer si le personnage peut etre ressucite
     */
    public void substractResurection(){
        if((this.resurection+this.life)>=0){
            int res = Utility.randInt(0,this.resurection+this.life);
            this.resurection=this.resurection+this.life-res;
            this.life=res;
        } else {
            this.life=0;
        }
    }
    
    /** 
     * Getter pour les points de vie
     * @return int
     */
    public int getLife(){
        return(this.life);
    }

    /** 
     * Getter pour la resurrection
     * @return int
     */
    public int getResurection(){
        return(this.resurection);
    }
}
