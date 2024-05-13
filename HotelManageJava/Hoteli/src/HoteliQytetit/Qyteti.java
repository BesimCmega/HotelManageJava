package HoteliQytetit;
import java.io.*;
import java.util.*;


public class Qyteti {
    private Hoteli hotel;
    private ArrayList<Klienti> klientet;

    public Qyteti(Hoteli hotel) {
        this.hotel = hotel;
        this.klientet = new ArrayList<>();
    }

    public class Rezervimi extends Thread {
        private Hoteli hotel;
        private Klienti klient;

        public Rezervimi(Hoteli hotel, Klienti klient) {
            this.hotel = hotel;
            this.klient = klient;
        }

        @Override
        public void run() {
        	try {
        		while (hotel.kaHapesira()) {
            Hapesira hapsira = hotel.rezervoHapesiren(klient);
            if (hapsira != null) {
                int zgjatja = new Random().nextInt(1250) + 250;
                    sleep(zgjatja);
                    hotel.regjistroHapesiren(klient, hapsira);
                    }
        		}
        		} catch (Exception e) {
                    e.printStackTrace();
                }
                
            }
        }
    

    public void lexoHapsirat() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("hapesirat.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                String[] hapsiraData = line.split(":");
                if (hapsiraData.length != 5) {
                    continue;
                }
                String lloj = hapsiraData[0];
                int nr = Integer.parseInt(hapsiraData[1]);
                String pershkrimi = hapsiraData[2];
                double cmimi = Double.parseDouble(hapsiraData[3]);
                
              
                Hapesira hapesira = null;
                if (lloj.equals("DhomaStandarde")) {
                	boolean kaTV = Boolean.parseBoolean(hapsiraData[4]);
                	hapesira = new DhomaStandarde(nr, pershkrimi, cmimi, kaTV);
                } else if (lloj.equals("DhomaVIP")) {
                	boolean kaGjakuzi = Boolean.parseBoolean(hapsiraData[4]);
                	hapesira = new DhomaVIP(nr, pershkrimi, cmimi, kaGjakuzi);
                } else if (lloj.equals("Restorant")) {
                	int kapaciteti = Integer.parseInt(hapsiraData[4]);
                	hapesira = new Restoranti(nr, pershkrimi, cmimi, kapaciteti);
                } else if (lloj.equals("SallePerKonferenca")) {
                	int kapaciteti = Integer.parseInt(hapsiraData[4]);
                	hapesira = new SallaPerKonferenca(nr, pershkrimi, cmimi, kapaciteti);
                }
                if (hapesira != null) {
                    hotel.shtoHapsiren(hapesira);
                }
            }
            br.close();
        } catch (IOException  | RezervimiException e) {
            e.printStackTrace();
        }
    }
    
    public void shtoKlientin(Klienti k) throws RezervimiException {
    	if (k == null) {
            throw new RezervimiException("Klienti i dhene eshte null!");
        }
        if (!klientet.contains(k)) {
          klientet.add(k);
        }
      }
    
    public void lexoKlientet() {
        try {
        BufferedReader br = new BufferedReader(new FileReader("klientet.txt")); 
            String line;
            while ((line = br.readLine()) != null) {
                String [] data = line.split(";");
                if (data.length != 3) {
                    continue;
                }
                
                String emri = data[0];
                int mosha = Integer.parseInt(data[1]);
                char gjinia = data[2].charAt(0);
                Klienti klienti = new Klienti(emri, mosha, gjinia);
               
                
                shtoKlientin(klienti);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void filloRezervimet() {
        for (Klienti k : klientet) {
        	try {
          Rezervimi r = new Rezervimi(hotel, k);
          r.start();
         }
        	catch (Exception e) {
                System.out.println("Error gjate fillimit te rezervimit: " + e.getMessage());
            }
        }
      }
    
    public void shtypKlientet(){
        for (Klienti k : klientet){
            System.out.println(k);
        }
    }
    public void shtypHapesiratEHotelit(){
        for (Hapesira h : hotel.getHapesirat()){
            System.out.println(h);
        }
    }

    public void shtypRezervimet(){
        hotel.getRezervimet().forEach((k, v) -> {
            System.out.println("Klienti: " + k.getEmri()  + " ka bere: " + v.size() + " rezervime");
        });
    }
    
    public static void main(String[] args) {
        try {
            Hoteli hotel = new Hoteli("Besi Hotel");
            Qyteti qyteti = new Qyteti(hotel);
            System.out.println("----------Hapesirat e hotelit para se ti lexojme nga file ---------");
            qyteti.shtypHapesiratEHotelit();
            System.out.println("----------Hapesirat e hotelit pas leximit nga file ---------");
            qyteti.lexoHapsirat();
            qyteti.shtypHapesiratEHotelit();
            System.out.println("----------Klientet para se ti lexojme nga file-----");
            qyteti.shtypKlientet();
            System.out.println("---------Klientet pas leximit nga file-------");
            qyteti.lexoKlientet();
            qyteti.shtypKlientet();
            qyteti.filloRezervimet();
            System.out.println("-----------Rezervimet e klienteve--------------");
            qyteti.shtypRezervimet();
            if (!hotel.kaHapesira()){
            	hotel.faturo();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    

    
}

