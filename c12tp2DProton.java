import org.jlab.jnp.hipo4.io.*;
import org.jlab.jnp.hipo4.data.*;
import org.jlab.clas.physics.*;
import org.jlab.groot.data.*;
import org.jlab.groot.graphics.*;
import org.jlab.groot.math.*;
import org.jlab.groot.ui.*;
import java.util.Random;
import javax.swing.JFrame;
import org.jlab.groot.data.H1F;
import org.jlab.groot.data.H2F;
import org.jlab.groot.graphics.EmbeddedCanvas;


//---- imports for GROOT library  
// Create the reader and load in the file

HipoReader reader = new HipoReader(); 
reader.open("SimOuts.hipo"); 
 
Event     event = new Event(); 
Bank  particles = new Bank(reader.getSchemaFactory().getSchema("REC::Particle"));
 
// Loop over the events in the file and read particle bank and check for electron in the first raw, 
// if there is one we can create lorentz vector for the particle and calculate W2 and Q2, and plot it. 
// First we will declare histogram objects and canvas object:

// H1F  hW = new H1F("hW" ,100, 0.5, 8.0);
H2F hQ2 = new H2F("hQ2",100, 0.1, 8.0, 100, 0.5, 8.0);
H2F hW = new H2F("hW",100, 0.1, 8.0, 100, 0.5, 8.0);
H2F htheta = new H2F("htheta", 90, 0, 90, 360, -180, 180);
H2F hphi = new H2F("hphi", 360, -180, 180, 360, -180, 180);

hQ2.setTitleY("W [GeV]");
hQ2.setTitleX("Q^2 [GeV/c^2]");
hQ2.setTitle("Q^2 vs W");
htheta.setTitleY("phi [Degrees]");
htheta.setTitleX("theta [Degrees]");
htheta.setTitle("theta vs phi");

TCanvas ec = new TCanvas("ec",600,600);
 
// Loop over the events and count how many events we have where at least one electron and at least two pos and neg pions are detected.

int counter = 0;
int elec = 0;
int pos_pion = 0;
int neg_pion = 0;
  
while(reader.hasNext()==true){
     reader.nextEvent(event);
     event.read(particles);
     if(particles.getRows()>0){
         int pid = particles.getInt("pid",0);
              if(pid == 11) elec++;
              if(pid == 211) pos_pion++;
              if(pid == -211) neg_pion++;
         }
     }
     if(elec>=1&&pos_pion>=2&&neg_pion>=2){
          counter++;
}
System.out.println("processed # " + counter + " , electrons : " + elec + " , pos pions : " + pos_pion + " , neg pions : " + neg_pion);
 
// Loop over the events and calculate Q2 and W from the LorentzVectors 

LorentzVector  vBeam   = new LorentzVector(0.0,0.0,5.014,5.014);
LorentzVector  vTarget = new LorentzVector(0.0,0.0,0.0,0.938);
LorentzVector electron = new LorentzVector(); 
LorentzVector       vW = new LorentzVector(); 
LorentzVector      vQ2 = new LorentzVector();

// Read the first event and reset to the begining of the file

reader.getEvent(event,0); 
  
while(reader.hasNext()==true){
     reader.nextEvent(event);
     event.read(particles);
     if(particles.getRows()>0){
	for (int row=0; row<particles.getRows(); row++){ 
         int pid = particles.getInt("pid",row);
	 int charge = particles.getInt("charge",row);
         if(pid==2212){
            electron.setPxPyPzM(
                particles.getFloat("px",row), 
                particles.getFloat("py",row),
                particles.getFloat("pz",row),
                0.0005
                );
             
            vW.copy(vBeam);
            vW.add(vTarget);
            vW.sub(electron);
             
            vQ2.copy(vBeam);
            vQ2.sub(electron);
             
          //  hW.fill(vW.mass());
            hQ2.fill(-vQ2.mass2(), vW.mass());
//	    Math.toDegrees(electron.theta());
//	    Math.toDegrees(electron.phi());
            htheta.fill(Math.toDegrees(electron.theta()), Math.toDegrees(electron.phi()));

         }
	}
     }
}
  
ec.divide(1,1);
  
//ec.cd(0).draw(hQ2);
ec.cd(0).draw(htheta);
