import java.util.ArrayList;

public class Grid {
    private ArrayList<Personage> equipeA;
    private ArrayList<Personage> equipeB;
    private char[][] plateau;
    private int nbPlayers;
    private boolean isRunning;
    private int height;
    private int width;
    private boolean devMode;

    /**
     * Constructeur de la classe Grid
     * 
     * @param height    Hauteur du plateau
     * @param width     Largeur du plateau
     * @param nbPlayers Nombre de joueur par equipe
     * @param devMode   Dit si on est en mode developpeur
     */
    Grid(int height, int width, int nbPlayers, boolean devMode) {
        this.equipeA = new ArrayList<Personage>();
        this.equipeB = new ArrayList<Personage>();
        this.height = height;
        this.width = width;
        this.nbPlayers = nbPlayers;
        this.devMode = devMode;
        this.plateau = new char[height][width];
        this.isRunning = true;
        for (int i = 0; i < plateau.length; i++) {
            for (int j = 0; j < plateau[0].length; j++) {
                plateau[i][j] = '.';
            }
        }
        initEquipe();
    }

    /**
     * Contructeur de base de la classe Gris
     */
    Grid() {
        this(10, 10, 5, false);
    }

    /**
     * Permet d'initialiser les equipes
     */
    private void initEquipe() {
        char name = 'a';
        for (int i = 0; i < nbPlayers; i++) {
            Attack attack = new Attack(Utility.randInt(10, 20), Utility.randInt(10, 20));
            Defence defense = new Defence(Utility.randInt(8, 12), Utility.randInt(8, 12));
            Health sante = new Health(Utility.randInt(10, 20), Utility.randInt(5, 10));
            boolean retry;
            Position p;
            do {
                retry = false;
                p = new Position(Utility.randInt(0, plateau[0].length - 1), Utility.randInt(0, plateau.length - 1));
                for (Personage a : equipeA) {
                    if (p.equal(a.getPos())) {
                        retry = true;
                    }
                }
            } while (retry);
            equipeA.add(new Personage(attack, defense, sante, true, p, Utility.randOr(), name));
            plateau[p.getY()][p.getX()] = name;
            name += 1;
        }

        name = 'A';
        for (int i = 0; i < nbPlayers; i++) {
            Attack attack = new Attack(Utility.randInt(10, 20), Utility.randInt(10, 20));
            Defence defense = new Defence(Utility.randInt(8, 12), Utility.randInt(8, 12));
            Health sante = new Health(Utility.randInt(10, 20), Utility.randInt(5, 10));
            boolean retry;
            Position p;
            do {
                retry = false;
                p = new Position(Utility.randInt(0, plateau[0].length - 1), Utility.randInt(0, plateau.length - 1));
                for (Personage a : equipeA) {
                    if (p.equal(a.getPos())) {
                        retry = true;
                    }
                }
                for (Personage b : equipeB) {
                    if (p.equal(b.getPos())) {
                        retry = true;
                    }
                }
            } while (retry);
            equipeB.add(new Personage(attack, defense, sante, true, p, Utility.randOr(), name));
            plateau[p.getY()][p.getX()] = name;
            name += 1;
        }
    }

    /**
     * Permet de recuperer le contenu d'une case
     * 
     * @param p Position ou l'on recupere le contenue
     * @return char Retourne le contenue de la case
     */
    public char getCase(Position p) {
        return this.plateau[p.getY()][p.getX()];
    }

    /**
     * Permet de modifier le contenu d'une case
     * 
     * @param pos Position de la case modifie
     * @param c   Nouvelle valeur de la case
     */
    public void setCase(Position pos, char c) {
        this.plateau[pos.getY()][pos.getX()] = c;
    }

    /**
     * Permet d'afficher la grille
     */
    public void displayGrid() {
        System.out.print("+");
        for (int j = 0; j < plateau[0].length; j++) {
            System.out.print("-");
        }
        System.out.print("+\n");
        for (int i = 0; i < plateau.length; i++) {
            System.out.print("|");
            for (int j = 0; j < plateau[0].length; j++) {
                System.out.print(plateau[i][j]);
            }
            System.out.print("|\n");
        }
        System.out.print("+");
        for (int j = 0; j < plateau[0].length; j++) {
            System.out.print("-");
        }
        System.out.print("+\n");
    }

    /**
     * Permet de realiser un tour de jeu, tous les joueurs de chaque equipe joue une
     * fois
     * 
     * @param i     Numero du tour
     * @param speed Vitesse du jeux, temps en milliseconde que reste afficher un
     *              tour d'un joueur
     * @return boolean Retourne si le jeux doit continuer ou pas
     * @throws InterruptedException
     */
    public boolean update(int i, int speed) throws InterruptedException {
        boolean runA = false;
        boolean runB = false;

        for (Personage p : equipeA) {
            if (p.getAlive()) {
                System.out.println("- Team lower case -");
                System.out.println(p.getName() + " turn");
                p.move(this);
                this.displayGrid();
                System.out.println("Round " + i);
                if (!devMode) {
                    Thread.sleep(speed);
                    Utility.ClearConsole();
                }
                runA = true;
            }
        }
        for (Personage p : equipeB) {
            if (p.getAlive()) {
                System.out.println("- Team upper case -");
                System.out.println(p.getName() + " turn");
                p.move(this);
                this.displayGrid();
                System.out.println("Round " + i);
                if (!devMode) {
                    Thread.sleep(speed);
                    Utility.ClearConsole();
                }
                runB = true;
            }
        }

        return (runA && runB);
    }

    /**
     * Boucle qui fait tourner le jeu
     * 
     * @throws InterruptedException
     */
    public void run() throws InterruptedException {
        int i = 1;
        int speed = 250;
        int eqA = 0;
        int eqB = 0;
        System.out.println(isRunning);
        while (isRunning) {
            isRunning = update(i, speed);
            i++;
            if (i % 10 == 0) {
                eqA = 0;
                eqB = 0;
                for (Personage p : equipeA) {
                    if (p.getAlive()) {
                        eqA++;
                    }
                }

                for (Personage p : equipeB) {
                    if (p.getAlive()) {
                        eqB++;
                    }
                }
                this.displayGrid();
                System.out.println(
                        eqA + " players left in team lower case and " + eqB + " players left in team upper case");
                System.out.println("Do you want to stop ? y: yes stop | n: no continue");
                char res = Clavier.saisirChar();
                while (res != 'y' && res != 'n') {
                    System.out.println("Error, it's not the right button, please retry : ");
                    res = Clavier.saisirChar();
                }
                if (res == 'y') {
                    isRunning = false;
                } else {
                    if (!this.devMode) {
                        System.out.println("Do you to speed up the game ? (x2) y: yes | n: no");
                        res = Clavier.saisirChar();
                        while (res != 'y' && res != 'n') {
                            System.out.println("Error, it's not the right button, please retry : ");
                            res = Clavier.saisirChar();
                        }
                        if (res == 'y') {
                            speed /= 2;
                        }
                    }
                }
            }
        }
        if (eqA > eqB) {
            System.out.println("Team lower case win !");
        } else if (eqB > eqA) {
            System.out.println("Team upper case win !");
        } else {
            System.out.println("It's a draw !");
        }
        System.out.println("Goodbye, see you soon !");
    }

    /**
     * Permet a un joueur d'attaquer un autre joueur a partir de son nom
     * 
     * @param qui_attaque Joueur qui attaque un autre joueur
     * @param name        Nom du joueur qui est attaque
     */
    public void attack(Personage qui_attaque, char name) {
        for (Personage est_attaquer : equipeA) {
            if (est_attaquer.getName() == name) {
                qui_attaque.attack(est_attaquer, this);
            }
        }
        for (Personage est_attaquer : equipeB) {
            if (est_attaquer.getName() == name) {
                qui_attaque.attack(est_attaquer, this);
            }
        }
    }

    /**
     * getter pour la hauteur du plateau
     * 
     * @return int Retourne la hauteur du plateau
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * getter pour la largeur du plateau
     * 
     * @return int Retourne la largeur du plateau
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * getter pour savoir si on est en mode developpeur
     * 
     * @return boolean Retourne si on est en mode developpeur
     */
    public boolean getDevMode() {
        return this.devMode;
    }
}