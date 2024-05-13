package HoteliQytetit;

public class DhomaStandarde extends Hapesira implements Monitorohet {
	
	public boolean kaTV;

	public DhomaStandarde(int nr, String pershkrimi, double cmimi, boolean kaTV) throws RezervimiException {
		super(nr, pershkrimi, cmimi);
		
		this.kaTV = kaTV;
		
	}

	public boolean getKaTV() {
		return kaTV;
	}

	public void setKaTV(boolean kaTV) {
		this.kaTV = kaTV;
	}

	@Override
	public String getMonitorimi() {
		
		return "Kamerat";
	}

	@Override
	public boolean kaWifi() {
		
		return false;
	}
	
	public String toString() {
		
		return "Dhoma standarde " + super.toString() + (kaTV?" ":"nuk ") + " ka TV";
	}
	
	
	
	
	
	
	
	
}
