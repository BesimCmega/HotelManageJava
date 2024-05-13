package HoteliQytetit;

public class DhomaVIP extends Hapesira implements Monitorohet{
	
	public boolean kaGjakuzi;

	public DhomaVIP(int nr, String pershkrimi, double cmimi, boolean kaGjakuzi) throws RezervimiException {
		super(nr, pershkrimi, cmimi);
		
		this.kaGjakuzi = kaGjakuzi;
	}

	public boolean getKaGjakuzi() {
		return kaGjakuzi;
	}

	public void setKaGjakuzi(boolean kaGjakuzi) {
		this.kaGjakuzi = kaGjakuzi;
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
		
		return "Dhoma VIP " + super.toString() + (kaGjakuzi?" ":"nuk ") + " ka Gjakuzi";
	}
	
	
	
	
	
	
}
