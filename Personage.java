import java.util.ArrayList;

public class Personage {
    private Attack attack;
    private Defence defence;
    private Health health;
    private boolean isAlive;
    private Position position;
    private char name;
    private Position orientation;

    /**
     * Constructeur de la classe personnage
     * @param attack Donne la valeur de l'attaque du personnage
     * @param defence Donne la valeur de la defense du personnage
     * @param health Donne la valeur de la sante du personnage
     * @param isAlive Donne le booleen pour savoir si le personnage est en vie
     * @param position Donne la position du personnage
     * @param orientation Donne l'orientation du personnage
     * @param name Donne le nom du personnage
     */
    Personage(Attack attack, Defence defence, Health health, boolean isAlive, Position position, Position orientation,
            char name) {
        this.attack = attack;
        this.defence = defence;
        this.health = health;
        this.isAlive = isAlive;
        this.position = position;
        this.orientation = orientation;
        this.name = name;
    }


    /**
     * Permet au personnage de se deplacer
     * @param g La grille g permet d'avoir la grille de jeu dans la fonction d'attaque
     */
    public void move(Grid g) {
        if(g.getDevMode()){
            System.out.println("Old Pos : " + this.position + " | Old Or : " + this.orientation);
        }
        Position p1 = new Position(this.position.getX() + this.orientation.getX(),
                this.position.getY() + this.orientation.getY());
        if (p1.getX() >= 0 && p1.getY() >= 0 && p1.getX() < g.getWidth() && p1.getY() < g.getHeight()) {

            if (g.getCase(p1) == '.') {
                
                g.setCase(this.getPos(), '.');
                this.position.add(this.orientation);
                g.setCase(this.getPos(), this.name);
            } else if ((Character.isUpperCase(this.name) != (Character.isUpperCase(g.getCase(p1))))) {
                g.displayGrid();
                g.attack(this, g.getCase(p1));
                g.displayGrid();
                Utility.pressChar('j', "Press j to continue !");
            } else {
                this.findNewOr(g);
            }
        } else {
            this.opposite(p1, g);
            Position p2 = new Position(this.position.getX() + this.orientation.getX(),
                    this.position.getY() + this.orientation.getY());
            if (g.getCase(p2) == '.') {
                g.setCase(this.getPos(), '.');
                this.position.add(this.orientation);
                g.setCase(this.getPos(), this.getName());
            } else if ((Character.isUpperCase(this.name) != (Character.isUpperCase(g.getCase(p2))))) {
                g.displayGrid();
                g.attack(this, g.getCase(p2));
                g.displayGrid();
                Utility.pressChar('j', "Press j to continue !");
            } else {
                this.findNewOr(g);
            }
        }
        if(g.getDevMode()){
            System.out.println("New Pos : " + this.position + " | New Or : " + this.orientation);
        }
    }

    
    /** 
     * Permet au personnage d'attaquer
     * @param a Le personnage a est le personnage attaque
     * @param g La grille g permet d'avoir la grille de jeu dans la fonction d'attaque
     */
    public void attack(Personage a, Grid g) {
        System.out.println(this + " attack " + a);
        int dodge = a.getDefence().getDodge();
        int hability = this.attack.getHability();
        int armor = a.getDefence().getArmor();
        int strengh = this.attack.getStrengh();
        int damage = strengh + Utility.randInt(1, 6) - armor;
        if (hability > dodge - Utility.randInt(1, 6)) {
            if (damage > 0) {
                a.getHealth().substractLife(damage);
                if (a.getHealth().getLife() <= 0) {
                    a.setIsAlive(false);
                    System.out.println("After attack " + a + " is dead !");
                    g.setCase(this.getPos(), '.');
                    this.position.add(this.orientation);
                    g.setCase(a.getPos(), this.getName());
                } else {
                    System.out.println("After attack " + a + " is still alive !");
                    this.findNewOr(g);
                    g.setCase(a.getPos(), a.getName());
                }
            } else {
                System.out.println("Attack doesn't do any damage !");
                this.findNewOr(g);

                g.setCase(a.getPos(), a.getName());
            }
        } else {
            System.out.println("It's doesn't hit !");
            this.findNewOr(g);

            g.setCase(a.getPos(), a.getName());
        }
    }

    
    /** 
     * Getter pour obtenir la direction du personnage
     * @return Position
     */
    public Position getOr() {
        return (this.orientation);
    }


    /** 
     * Methode toString pour la classe personnage
     * @return String
     */
    public String toString() {
        return ("[" + name + ": " + attack + "," + defence + "," + health + "]");
    }

    
    /** 
     * Getter pour obtenir la position du personnage
     * @return Position
     */
    public Position getPos() {
        return this.position;
    }

    
    /** 
     * Getter pour savoir si le personnage est en vie
     * @return boolean
     */
    public boolean getAlive() {
        return this.isAlive;
    }

    
    /** 
     * Getter pour le nom du personnage
     * @return char
     */
    public char getName() {
        return this.name;
    }

    
    /** 
     * Getter pour les statistiques de defense
     * @return Defence
     */
    public Defence getDefence() {
        return this.defence;
    }

    
    /** 
     * Getter pour les statistiques d'attaques
     * @return Attack
     */
    public Attack getAttack() {
        return this.attack;
    }

    
    /** 
     * Getter pour les statistiques de vies
     * @return Health
     */
    public Health getHealth() {
        return this.health;
    }

    
    /** 
     * Permet de savoir si un personnage est encore en vie
     * @param isStillAlive Retourne vrai si un personnage est en vie
     */
    public void setIsAlive(boolean isStillAlive) {
        this.isAlive = isStillAlive;
    }

    
    /** 
     * Permet d'inverser la direction d'un personnage lorsqu'il touche un mur 
     * @param p Donne la prochaine position que le personnage devrait prendre avant modification
     * @param g Grille pour recuperer sa hauteur et sa largeur
     */
    private void opposite(Position p, Grid g) {
        if ((p.getX() < 0 && p.getY() < 0) ||
                (p.getX() > g.getWidth()-1 && p.getY() > g.getHeight()-1) ||
                (p.getX() > g.getWidth()-1 && p.getY() < 0) ||
                (p.getX() < 0 && p.getY() > g.getHeight()-1) ||
                (this.orientation.getX() == 0 || this.orientation.getY() == 0)) {
            this.orientation.opposite();
        } else if ((p.getY() < 0 || p.getY() > g.getHeight()-1)) {
            this.orientation.oppositeY();
        } else {
            this.orientation.oppositeX();
        }
    }

    
    /** 
     * Permet de trouver une nouvelle orientation apres une attaque
     * @param grid Retourne la nouvelle orientation trouvee
     */
    private void findNewOr(Grid grid) {
        ArrayList<Position> orientations = checkCase(grid);
        if (orientations.size() != 0) {
            int rand = (int) (orientations.size() * Math.random());
            this.orientation = orientations.get(rand);
            grid.setCase(this.getPos(), '.');
            this.position.add(this.orientation);
            grid.setCase(this.getPos(), this.name);
        } else {
            this.orientation = Utility.randOr();
        }

    }

    
    /**
     * Permet de verifier la disponibilite des cases adjacentes 
     * @param grid Donne la grille de jeu actuel
     * @return ArrayList<Position> Retourne une liste de position disponible
     */
    private ArrayList<Position> checkCase(Grid grid) {
        ArrayList<Position> orientations = new ArrayList<Position>();
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                try {
                    char a = grid.getCase(new Position(this.position.getX() + j, this.position.getY() + i));
                    if (a == '.') {
                        orientations.add(new Position(j, i));
                    }
                } catch (ArrayIndexOutOfBoundsException e) {

                }
            }
        }
        return orientations;

    }

}