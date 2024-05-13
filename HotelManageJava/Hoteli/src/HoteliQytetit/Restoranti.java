package HoteliQytetit;

public class Restoranti extends Hapesira implements Monitorohet{
	
	private int kapaciteti;

	public Restoranti(int nr, String pershkrimi, double cmimi, int kapaciteti) throws RezervimiException {
		super(nr, pershkrimi, cmimi);
		
		if(kapaciteti <= 0) {
			throw new RezervimiException("Kapaciteti nuk mund te jete 0 ose me i vogel!");
		}
		this.kapaciteti = kapaciteti;
		
	}

	public int getKapaciteti() {
		return kapaciteti;
	}

	public void setKapaciteti(int kapaciteti) throws RezervimiException {
		if(kapaciteti <= 0) {
			throw new RezervimiException("Kapaciteti nuk mund te jete 0 ose me i vogel!");
		}
		this.kapaciteti = kapaciteti;
	}

	@Override
	public String getMonitorimi() {
		
		return "Sigurimi Fizik";
	}

	@Override
	public boolean kaWifi() {
		
		return true;
	}
	
	public String toString() {
		
		return "Restoranti " + super.toString() + " me kapacitet " + kapaciteti;
	}
	
	
	
	
	
	
}
