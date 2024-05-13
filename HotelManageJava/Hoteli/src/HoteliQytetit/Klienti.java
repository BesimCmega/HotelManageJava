package HoteliQytetit;

import java.util.ArrayList;

public class Klienti {
  private  String emri;
  private  int mosha;
  private  char gjinia;
  private ArrayList<Hapesira> hapsiratERezervuara;

  public Klienti(String emri, int mosha, char gjinia) throws RezervimiException {
	  if(emri.trim().isEmpty()) {
			throw new RezervimiException("Emri nuk mund te jete i zbrazet!");
		}
		this.emri = emri;
		
		if(mosha <= 0) {
			throw new RezervimiException("Mosha nuk mund te jete 0 ose me i vogel!");
		}
		this.mosha = mosha;
		
		if(!(gjinia == 'M' || gjinia == 'F')) {
			throw new RezervimiException("Gjinia  mund te jete vetem M ose F!!");
		}
    this.gjinia = gjinia;
    
    this.hapsiratERezervuara = new ArrayList<Hapesira>();
  }

  public String getEmri() {
    return emri;
  }
  
  public void setEmri(String emri) {
	  this.emri = emri;
  }

  public int getMosha() {
    return mosha;
  }
  

  public void setMosha(int mosha) {
	this.mosha = mosha;
}

public char getGjinia() {
    return gjinia;
  }
  
  public void setGjinia(char gjinia) {
	this.gjinia = gjinia;
}

public ArrayList<Hapesira> getHapsiratERezervuara() {
    return hapsiratERezervuara;
  }

public boolean merreRadhen(Hoteli h) throws RezervimiException{
    if (h == null){
    	
        throw new RezervimiException("Hoteli nuk mund te jete null!");
    }
    return h.getRadha().tryLock();
}

public void rezervo(Hoteli h) throws RezervimiException{
    if (h == null){
        throw new RezervimiException("Hoteli nuk mund te jete null!");
    }
    if (merreRadhen(h)){
    	
        Hapesira hapesiraERezervuar = h.rezervoHapesiren(this);
        
        h.getRadha().unlock();
        
        System.out.println(emri + " rezervoj me sukses hapësirën " + hapesiraERezervuar + " ne hotelin " + h.getEmri());
    }else {
        System.out.println(emri + " nuk e mori radhen ne hotelin " + h.getEmri());
    }
}

  @Override
  public String toString() {
    return emri + " - " + (gjinia == 'M' ? "mashkull" : "femër") + " " + mosha + " vjec";
  }

  @Override
  public boolean equals(Object o) {
	  if(o instanceof Klienti) {
		  Klienti k = (Klienti)o;
		  if(k.getEmri() == emri && k.gjinia == gjinia && k.mosha == mosha) {
			  return true;
		  }
	  }
	  
	  return false;
  }
}

