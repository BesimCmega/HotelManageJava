package HoteliQytetit;

public class SallaPerKonferenca extends Hapesira implements Monitorohet{
	
	private int kapaciteti;

	public SallaPerKonferenca(int nr, String pershkrimi, double cmimi, int kapaciteti) throws RezervimiException {
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
		
		return "Kamerat";
	}

	@Override
	public boolean kaWifi() {
		
		return true;
	}
	
	public String toString() {
		
		return "SallaPerKonferenca " + super.toString() + " me kapacitet " + kapaciteti;
	}
	
}
