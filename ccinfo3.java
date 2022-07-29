import org.jlab.groot.data.*;
import org.jlab.groot.graphics.*;
import org.jlab.groot.math.*;
import org.jlab.groot.ui.*;


import java.util.Random;
import javax.swing.JFrame;

import org.jlab.groot.data.H1F;
import org.jlab.groot.graphics.EmbeddedCanvas;


//---- imports for HIPO4 library
import org.jlab.jnp.hipo4.io.*; 
import org.jlab.jnp.hipo4.data.*;

//---- imports for GROOT library
import org.jlab.groot.data.*;
import org.jlab.groot.graphics.*;

//---- imports for PHYSICS library
import org.jlab.clas.physics.*;

//for cc 
import org.jlab.jnp.hipo4.data.Event;
import org.jlab.jnp.hipo4.data.Bank;
//import org.jlab.jnp.hipo4.io.HipoReader;
//import org.jlab.jnp.physics.*;
//import org.jlab.jnp.pdg.PhysicsConstants;

//import org.jlab.groot.base.GStyle
//import org.jlab.groot.data.*;
//import org.jlab.groot.ui.*;




  
// Create the reader and load in the file

HipoReader reader = new HipoReader(); 
reader.open("recsis2.hipo"); 
 
Event     event = new Event(); 
Bank  particles = new Bank(reader.getSchemaFactory().getSchema("EVENT::particle"));
Bank       bank   = new Bank(reader.getSchemaFactory().getSchema("EVENT::particle"));
Bank       ccpb   = new Bank(reader.getSchemaFactory().getSchema("DETECTOR::ccpb"));

//float cc_nphe;

boolean cutCCnphe = false;
boolean cutCCstat = false;

double NPHE_MIN = 28;
 
// Loop over the events in the file and read particle bank and check for electron in the first raw, 
// if there is one we can create lorentz vector for the particle and calculate W2 and Q2, and plot it. 
// First we will declare histogram objects and canvas object:

H1F  hW = new H1F("hW" ,100, 0.5, 8.0);
H1F hQ2 = new H1F("hQ2",100, 0.1, 8.0);
H1F htheta = new H1F("htheta", 90, 0, 90.0);
H1F hphi = new H1F("hphi", 360, -180, 180.0);
H1F h1_cc_nphe = new H1F("h1_cc_nphe",200,0.0,200.0);
H1F h1_cc_nphe_cut = new H1F("h1_cc_nphe_cut",200,0.0,200.0);



h1_cc_nphe.setTitle("eg2 -- cc_nphe");
h1_cc_nphe.setTitleX("Number of Photoelectrons");
h1_cc_nphe.setTitleY("Counts");
h1_cc_nphe.setFillColor(42);
h1_cc_nphe_cut.setTitle("eg2 -- cc_nphe_cut");
h1_cc_nphe_cut.setTitleX("Number of Photoelectrons");
h1_cc_nphe_cut.setTitleY("Counts");
h1_cc_nphe_cut.setFillColor(43);
hW.setTitleX("W [GeV]");
hQ2.setTitleX("Q^2 [GeV/c^2]");
htheta.setTitleX("Theta [Degrees]");
hphi.setTitleX("Phi [Degrees]");

  
TCanvas ec = new TCanvas("ec",900,900);
 
// Loop over the events and count how many events we have where at least one electron and at least two pos and neg pions are detected.

int counter = 0;
int elec = 0;
int pos_pion = 0;
int neg_pion = 0;
  
while(reader.hasNext()==true){
     reader.nextEvent(event);
     event.read(particles);
     event.read(ccpb);
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
     event.read(ccpb);
     if(particles.getRows()>0){
	for (int row=0; row<particles.getRows(); row++){
         int pid = particles.getInt("pid",row);
	 int charge = particles.getInt("charge",row);
         float cc_nphe = ccpb.getFloat("nphe", row);
         if(pid==11 ||( pid==0 && charge==-1)){
            electron.setPxPyPzM(
 	        particles.getFloat("px",row), 
                particles.getFloat("py",row),
                particles.getFloat("pz",row),
                0.0005
                );
	   
            if(bank.getInt("ccstat",row)>0 && ccpb.getRows()>0){
              cutCCstat = true;
              cc_nphe = ccpb.getFloat("nphe",bank.getInt("ccstat",row)-1);
           
            }
	   
            vW.copy(vBeam);
            vW.add(vTarget);
            vW.sub(electron);
             
            vQ2.copy(vBeam);
            vQ2.sub(electron);
             
            hW.fill(vW.mass());
            hQ2.fill(-vQ2.mass2());
//	    Math.toDegrees(electron.theta());
            htheta.fill(Math.toDegrees(electron.theta()));
//            Math.toDegrees(electron.phi());
            hphi.fill(Math.toDegrees(electron.phi()));
//	    h1_cc_nphe.fill(cc_nphe);
     	   
         }
//	 if (cutCCstat==true){
	 h1_cc_nphe.fill(cc_nphe);
         if(cc_nphe>=NPHE_MIN){
             cutCCnphe = true;
             h1_cc_nphe_cut.fill(cc_nphe);
//         }
	 } 
	}
     }
}
  
ec.divide(3,2);
  
ec.cd(0).draw(hW);
ec.cd(1).draw(hQ2);
ec.cd(2).draw(h1_cc_nphe_cut);
ec.cd(3).draw(h1_cc_nphe);
ec.cd(4);
ec.draw(h1_cc_nphe);
ec.draw(h1_cc_nphe_cut, "same");
