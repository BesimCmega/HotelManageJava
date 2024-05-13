package HoteliQytetit;

public abstract class Hapesira {
	
	private int nr;
	private String pershkrimi;
	private double cmimi;
	
	public Hapesira(int nr, String pershkrimi, double cmimi) throws RezervimiException {
			
		if(nr <= 0) {
			throw new RezervimiException("Numri identifikues nuk mund te jete 0 ose me i vogel!");
		}
		this.nr = nr;
		
		if(pershkrimi.trim().isEmpty()) {
			throw new RezervimiException("Pershrimi nuk mund te jete i zbrazet!");
		}
		this.pershkrimi = pershkrimi;
		
		if(cmimi <= 0) {
			throw new RezervimiException("Cmimi nuk mund te jete 0 ose me i vogel!");
		}
		this.cmimi = cmimi;
		
		
		
	}

	public int getNr() {
		return nr;
	}

	

	public String getPershkrimi() {
		return pershkrimi;
	}



	public double getCmimi() {
		return cmimi;
	}

	public void setCmimi(double cmimi) throws RezervimiException {
		if(cmimi <= 0) {
			throw new RezervimiException("Cmimi nuk mund te jete 0 ose me i vogel!");
		}
		this.cmimi = cmimi;
	}
	
	public abstract boolean kaWifi();
	
	public String toString() {
		return nr + " - " + pershkrimi + " : " + cmimi;
	}
	
	public boolean equals(Object o) {
		
		if(o instanceof Hapesira) {
			Hapesira h = (Hapesira)o;
			if(h.getNr() == nr && h.getPershkrimi().equals(pershkrimi) && h.getCmimi() == cmimi) {
				return true;
			}
		}
		
		return false;
		
	}
	
	
	
}
