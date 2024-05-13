package HoteliQytetit;

import java.util.*;
import java.util.concurrent.locks.ReentrantLock;
import java.io.*;

public class Hoteli {
	  private String emri;
	  private ReentrantLock radha;
	  Vector<Hapesira> hapesirat = new Vector<>();
	  Hashtable<Klienti, ArrayList<Hapesira>> rezervimet = new Hashtable<>();

	  public Hoteli(String emri) {
	    this.emri = emri;
	    this.radha = new ReentrantLock();
	    
	  }

	  public String getEmri() {
	    return emri;
	  }

	  public ReentrantLock getRadha() {
	    return radha;
	  }
	  
	  public Vector<Hapesira> getHapesirat() {
	        return hapesirat;
	    }
	    public Hashtable<Klienti, ArrayList<Hapesira>> getRezervimet() {
	        return rezervimet;
	    }

	  public void shtoHapsiren(Hapesira hapsira) {
	    if (!hapesirat.contains(hapsira)) {
	      hapesirat.add(hapsira);
	    }
	  }

	  public boolean kaHapesira() {
	        return hapesirat.size() > 0;
	    }

	  public Hapesira rezervoHapesiren(Klienti k) throws RezervimiException {
	        radha.lock();
	        try {
	            if (k == null) {
	                throw new RezervimiException("Klienti i dhene eshte null!");
	            }
	            if (!kaHapesira()) {
	                return null;
	            }
	            Hapesira hapesiraERezervuar = hapesirat.remove(0);
	            regjistroHapesiren(k, hapesiraERezervuar);
	            return hapesiraERezervuar;
	        } finally {
	            radha.unlock();
	        }
	    }

	  public void regjistroHapesiren(Klienti k, Hapesira h) {
		  
	        ArrayList<Hapesira> rezervimetEKlientit = new ArrayList<Hapesira>();
	        
	        if (rezervimet.containsKey(k)) {
	        	
	            rezervimetEKlientit = rezervimet.get(k);
	        }
	        rezervimetEKlientit.add(h);
	        
	        rezervimet.put(k, rezervimetEKlientit);
	    }
	  


	  public void faturo() {
	        rezervimet.forEach((klienti, value) -> {
	            try {
	                Writer output = new FileWriter(klienti.getEmri() + ".txt");
	                double totali = 0;
	                output.write("Klienti " + klienti.toString() + "\n");
	                output.write("------------------------------------------------------------------------\n");
	                output.write("Numri i hapesirave te rezervuara: " + rezervimet.get(klienti).size() +"\n");
	                output.write("------------------------------------------------------------------------\n");
	                for (Hapesira h : rezervimet.get(klienti)) {
	                    output.write(h.toString()+"\n");
	                    totali += h.getCmimi();
	                }
	                output.write("------------------------------------------------------------------------\n");
	                output.write("Totali: " + totali+"\n");
	                output.write("------------------------------------------------------------------------\n");
	                output.close();
	            } catch (IOException e) {
	                throw new RuntimeException(e);
	            }
	        });
	    }

	
}

