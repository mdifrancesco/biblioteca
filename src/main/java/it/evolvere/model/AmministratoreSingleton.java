package it.evolvere.model;

public class AmministratoreSingleton {

    private  static AmministratoreSingleton amministratore;

    private AmministratoreSingleton(){
     //eventuali inizializzazioni di parametri
    }

    public static synchronized AmministratoreSingleton creaAmministratore(){
        if(amministratore != null){
            return amministratore;
        }
        amministratore = new AmministratoreSingleton();
        return amministratore;
    }
}
