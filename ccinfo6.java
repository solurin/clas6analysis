
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

import org.jlab.groot.base.GStyle
//import eg2Cuts.clas6beta
//import eg2Cuts.eg2Target


HipoReader reader = new HipoReader(); 
//reader.open("run_42011.pass2.A00.filtered.hipo"); 
reader.open("recsis2.hipo");
//reader.open("recsisC4.hipo"); 
Event     event = new Event(); 
Bank  particles = new Bank(reader.getSchemaFactory().getSchema("EVENT::particle"));
Bank       bank   = new Bank(reader.getSchemaFactory().getSchema("EVENT::particle"));
Bank       ccpb   = new Bank(reader.getSchemaFactory().getSchema("DETECTOR::ccpb"));
Bank       ecpb   = new Bank(reader.getSchemaFactory().getSchema("DETECTOR::ecpb"));
Bank       scpb   = new Bank(reader.getSchemaFactory().getSchema("DETECTOR::scpb"));


double[][] EC_SamplingFrac_C = new double[6][5];
double[][] EC_SamplingFrac_Fe = new double[6][5];
double[][] EC_SamplingFrac_Pb = new double[6][5];

EC_SamplingFrac_C[0][0] = 0.226726; EC_SamplingFrac_C[0][1] = 0.0379557; EC_SamplingFrac_C[0][2] = -0.00855326; EC_SamplingFrac_C[0][3] = 7.27022e-09; EC_SamplingFrac_C[0][4] = 0.0370079;
EC_SamplingFrac_C[1][0] = 0.222333; EC_SamplingFrac_C[1][1] = 0.0581705; EC_SamplingFrac_C[1][2] = -0.0131283; EC_SamplingFrac_C[1][3] = 3.12094e-12; EC_SamplingFrac_C[1][4] = 0.0413565;
EC_SamplingFrac_C[2][0] = 0.245212; EC_SamplingFrac_C[2][1] = 0.0213835; EC_SamplingFrac_C[2][2] = -0.00277372; EC_SamplingFrac_C[2][3] = 8.27916e-08; EC_SamplingFrac_C[2][4] = 0.0426498;
EC_SamplingFrac_C[3][0] = 0.238399; EC_SamplingFrac_C[3][1] = 0.0301926; EC_SamplingFrac_C[3][2] = -0.00720393; EC_SamplingFrac_C[3][3] = -3.81029e-09; EC_SamplingFrac_C[3][4] = 0.0309331;
EC_SamplingFrac_C[4][0] = 0.241834; EC_SamplingFrac_C[4][1] = 0.0442975; EC_SamplingFrac_C[4][2] = -0.0105584; EC_SamplingFrac_C[4][3] = 9.74651e-09; EC_SamplingFrac_C[4][4] = 0.0303602;
EC_SamplingFrac_C[5][0] = 0.245868; EC_SamplingFrac_C[5][1] = 0.0545128; EC_SamplingFrac_C[5][2] = -0.0149168; EC_SamplingFrac_C[5][3] = 1.43097e-08; EC_SamplingFrac_C[5][4] = 0.0483305;

EC_SamplingFrac_Fe[0][0] = 2.22E-1; EC_SamplingFrac_Fe[0][1] = 2.23E-2; EC_SamplingFrac_Fe[0][2] = -2.41E-3; EC_SamplingFrac_Fe[0][3] = 9.23E-3; EC_SamplingFrac_Fe[0][4] = 2.98E-2;
EC_SamplingFrac_Fe[1][0] = 2.34E-1; EC_SamplingFrac_Fe[1][1] = 1.95E-2; EC_SamplingFrac_Fe[1][2] = -2.08E-3; EC_SamplingFrac_Fe[1][3] = 8.66E-3; EC_SamplingFrac_Fe[1][4] = 3.09E-2;
EC_SamplingFrac_Fe[2][0] = 2.52E-1; EC_SamplingFrac_Fe[2][1] = 2.42E-2; EC_SamplingFrac_Fe[2][2] = -3.39E-3; EC_SamplingFrac_Fe[2][3] = 1.08E-2; EC_SamplingFrac_Fe[2][4] = 2.64E-2;
EC_SamplingFrac_Fe[3][0] = 2.51E-1; EC_SamplingFrac_Fe[3][1] = 2.08E-2; EC_SamplingFrac_Fe[3][2] = -3.27E-3; EC_SamplingFrac_Fe[3][3] = 7.22E-3; EC_SamplingFrac_Fe[3][4] = 2.98E-2;
EC_SamplingFrac_Fe[4][0] = 2.72E-1; EC_SamplingFrac_Fe[4][1] = 1.18E-2; EC_SamplingFrac_Fe[4][2] = -1.87E-3; EC_SamplingFrac_Fe[4][3] = 1.84E-2; EC_SamplingFrac_Fe[4][4] = 3.48E-2;
EC_SamplingFrac_Fe[5][0] = 2.52E-1; EC_SamplingFrac_Fe[5][1] = 2.28E-2; EC_SamplingFrac_Fe[5][2] = -3.11E-3; EC_SamplingFrac_Fe[5][3] = 4.11E-3; EC_SamplingFrac_Fe[5][4] = 3.55E-2;

//EC_SamplingFrac_Pb[0][0] = 2.53E-1; EC_SamplingFrac_Pb[0][1] = 1.38E-2; EC_SamplingFrac_Pb[0][2] = -1.40E-3; EC_SamplingFrac_Pb[0][3] = 7.67E-3; EC_SamplingFrac_Pb[0][4] = 3.54E-2;
EC_SamplingFrac_Pb[0][0] = 2.53E-1; EC_SamplingFrac_Pb[0][1] = 1.38E-2; EC_SamplingFrac_Pb[0][2] = -1.40E-3; EC_SamplingFrac_Pb[0][3] = 7.67E-3; EC_SamplingFrac_Pb[0][4] = 3.54E-2;
EC_SamplingFrac_Pb[1][0] = 2.49E-1; EC_SamplingFrac_Pb[1][1] = 1.47E-2; EC_SamplingFrac_Pb[1][2] = -1.49E-3; EC_SamplingFrac_Pb[1][3] = 7.53E-3; EC_SamplingFrac_Pb[1][4] = 3.38E-2;
EC_SamplingFrac_Pb[2][0] = 2.54E-1; EC_SamplingFrac_Pb[2][1] = 2.26E-2; EC_SamplingFrac_Pb[2][2] = -3.05E-3; EC_SamplingFrac_Pb[2][3] = 8.13E-3; EC_SamplingFrac_Pb[2][4] = 2.77E-2;
EC_SamplingFrac_Pb[3][0] = 2.55E-1; EC_SamplingFrac_Pb[3][1] = 1.90E-2; EC_SamplingFrac_Pb[3][2] = -3.05E-3; EC_SamplingFrac_Pb[3][3] = 7.20E-3; EC_SamplingFrac_Pb[3][4] = 3.04E-2;
EC_SamplingFrac_Pb[4][0] = 2.76E-1; EC_SamplingFrac_Pb[4][1] = 1.11E-2; EC_SamplingFrac_Pb[4][2] = -1.76E-3; EC_SamplingFrac_Pb[4][3] = 1.81E-2; EC_SamplingFrac_Pb[4][4] = 3.53E-2;
EC_SamplingFrac_Pb[5][0] = 2.62E-1; EC_SamplingFrac_Pb[5][1] = 1.92E-2; EC_SamplingFrac_Pb[5][2] = -2.62E-3; EC_SamplingFrac_Pb[5][3] = 1.99E-3; EC_SamplingFrac_Pb[5][4] = 3.76E-2;



//coeff,sector,targMass->
String targMass = "D";
int sector = 1;
int ECsector = 0;
int coeff = 0;
//String coeff = "";

boolean cutCCnphe = false;
boolean cutCCstat = false;
boolean cutECstat = false;
boolean cutSCstat = false;
boolean cutECin = false;
boolean cutdtECSC = false;
boolean cutECoverP = false;
boolean ret = false;

//double a;
//double b;
//double c;
//double d;
//double f;




double NPHE_MIN = 28;
double ECIN_MIN = 0.06;



H1F hW = new H1F("hW" ,100, 0.5, 8.0);
H1F hQ2 = new H1F("hQ2",100, 0.1, 8.0);
H1F htheta = new H1F("htheta", 90, 0, 90.0);
H1F hphi = new H1F("hphi", 360, -180, 180.0);
H1F h1_cc_nphe = new H1F("h1_cc_nphe",200,0.0,200.0);
H1F h1_cc_nphe_cut = new H1F("h1_cc_nphe_cut",200,0.0,200.0);
H1F h1_cc_nphe_withEC = new H1F("h1_cc_nphe_withEC",200,0.0,200.0);
H1F h1_cc_nphe_withEC_cut = new H1F("h1_cc_nphe_withEC_cut",200,0.0,200.0);
H2F h2_ECin_vs_ECout= new H2F("h1_ECin_vs_ECout",100,0.0,1.0,100,0.0,1.0);
H2F h2_ECin_vs_ECout_cut= new H2F("h1_ECin_vs_ECout",100,0.0,1.0,100,0.0,1.0);
H1F h1_dtECSC = new H1F("h1_dtECSC",100,-10.0,10.0);
H2F h2_P_vs_ECtotP = new H2F("h2_P_vs_ECtotP",100,0.0,5.0,100,0.0,0.5);
H2F h2_P_vs_ECtotP2 = new H2F("h2_P_vs_ECtotP",100,0.0,5.0,100,0.0,0.5);
H2F h2_P_vs_ECtotP_cut = new H2F("h2_P_vs_ECtotP_cut",100,0.0,5.0,100,0.0,0.5);
H2F h2_P_vs_ECtot2 = new H2F("h2_P_vs_ECtotP",100,0.0,5.0,100,0.0,0.5);
H2F h2_P_vs_ECtot2cut = new H2F("h2_P_vs_ECtotP",100,0.0,5.0,100,0.0,0.5);


h1_cc_nphe.setTitle("eg2 -- cc_nphe");
h1_cc_nphe.setTitleX("Number of Photoelectrons");
h1_cc_nphe.setTitleY("Counts");
h1_cc_nphe.setFillColor(42);
h1_cc_nphe_cut.setTitle("eg2 -- cc_nphe_cut");
h1_cc_nphe_cut.setTitleX("Number of Photoelectrons");
h1_cc_nphe_cut.setTitleY("Counts");
h1_cc_nphe_cut.setFillColor(43);
h1_cc_nphe_withEC.setTitle("Experiment: eg2");
h1_cc_nphe_withEC.setTitleX("Number of Photoelectrons");
h1_cc_nphe_withEC.setTitleY("Counts");
h1_cc_nphe_withEC_cut.setTitle("Experiment: eg2");
h1_cc_nphe_withEC_cut.setTitleX("Number of Photoelectrons");
h1_cc_nphe_withEC_cut.setTitleY("Counts");
h1_cc_nphe_withEC_cut.setFillColor(43);
h2_ECin_vs_ECout.setTitle("Experiment: eg2");
h2_ECin_vs_ECout.setTitleX("ECin (GeV)");
h2_ECin_vs_ECout.setTitleY("ECout (GeV)");
//h2_ECin_vs_ECout.setFillColor(41);
h2_ECin_vs_ECout_cut.setTitle("Experiment: eg2");
h2_ECin_vs_ECout_cut.setTitleX("ECin (GeV)");
h2_ECin_vs_ECout_cut.setTitleY("ECout (GeV)");
//h2_ECin_vs_ECout_cut.setFillColor(44);
h2_P_vs_ECtotP.setTitle("Experiment: eg2");
h2_P_vs_ECtotP.setTitleX("Momentum(e^-) (GeV)");
h2_P_vs_ECtotP.setTitleY("ECtot (GeV)");
h1_dtECSC.setTitle("Experiment: eg2");
h1_dtECSC.setTitleX("t(EC)-t(SC) (ns)");
h1_dtECSC.setTitleY("Counts");
h1_dtECSC.setFillColor(43);
h2_P_vs_ECtotP2.setTitle("Experiment: eg2");
h2_P_vs_ECtotP2.setTitleX("Momentum(e^-) (GeV)");
h2_P_vs_ECtotP2.setTitleY("ECtot (GeV)");
h2_P_vs_ECtotP_cut.setTitle("Experiment: eg2");
h2_P_vs_ECtotP_cut.setTitleX("Momentum(e^-) (GeV)");
h2_P_vs_ECtotP_cut.setTitleY("ECtot (GeV)");
h2_P_vs_ECtot2.setTitle("Experiment: eg2");
h2_P_vs_ECtot2.setTitleX("Momentum(e^-) (GeV)");
h2_P_vs_ECtot2.setTitleY("ECtot (GeV)");
h2_P_vs_ECtot2cut.setTitle("Experiment: eg2");
h2_P_vs_ECtot2cut.setTitleX("Momentum(e^-) (GeV)");
h2_P_vs_ECtot2cut.setTitleY("ECtot (GeV)");





  
TCanvas ec = new TCanvas("ec",900,900);
 
// Loop over the events and count how many events we have where at least one electron and at least two pos and neg pions are detected.

int counter = 0;
int elec = 0;
int pos_pion = 0;
int neg_pion = 0;
  
//while(reader.hasNext()==true){
//     reader.nextEvent(event);
//     event.read(particles);
//     event.read(ccpb);
//     event.read(ecpb);
//     event.read(scpb);
//     if(particles.getRows()>0){
//         int pid = particles.getInt("pid",0);
//              if(pid == 11) elec++;
//              if(pid == 211) pos_pion++;
//              if(pid == -211) neg_pion++;
        // }
 //    }
//     if(elec>=1&&pos_pion>=2&&neg_pion>=2){
//          counter++;
//     }
//}
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
     event.read(ecpb);
     event.read(scpb);
     if(particles.getRows()>0){
	for (int row=0; row<particles.getRows(); row++){
         int pid = particles.getInt("pid",row);
	 int charge = particles.getInt("charge",row);
         float cc_nphe = ccpb.getFloat("nphe", row);
	 float ecin = ecpb.getFloat("ein", row);
	 int ECsector = ecpb.getInt("sector", row);
 	 float ecout = ecpb.getFloat("eout", row);
	 float ectot = ecpb.getFloat("etot", row);
	 float ecTime = ecpb.getFloat("time", row);
	 float scTime = scpb.getFloat("time", row);
	 int sector = ecpb.getInt("sector", row);
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
	    if(bank.getInt("ecstat",row)>0 && ecpb.getRows()>0){
              cutECstat = true;
              ECsector = ecpb.getInt("sector",bank.getInt("ecstat",row)-1);
              ecin = ecpb.getFloat("ein",bank.getInt("ecstat",row)-1);
              ecout = ecpb.getFloat("eout",bank.getInt("ecstat",row)-1);
              ectot = ecpb.getFloat("etot",bank.getInt("ecstat",row)-1);
              ecTime = ecpb.getFloat("time",bank.getInt("ecstat",row)-1);
	    }
	    if(bank.getInt("scstat",row)>0 && scpb.getRows()>0){
              cutSCstat = true;
              scTime = scpb.getFloat("time",bank.getInt("scstat",row)-1);
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
     	   
//         }
	 h1_cc_nphe.fill(cc_nphe);
         if(cc_nphe>=NPHE_MIN){
             cutCCnphe = true;
             h1_cc_nphe_cut.fill(cc_nphe);
         } 
	 h2_ECin_vs_ECout.fill(ecin,ecout);
         if(ecin >= ECIN_MIN){
           cutECin = true;
           h2_ECin_vs_ECout_cut.fill(ecin,ecout);
         }
	  h1_dtECSC.fill(ecTime-scTime);
	  if(electron.p()>0.0){
             h2_P_vs_ECtotP.fill(electron.p(),ectot/electron.p());
	     h2_P_vs_ECtot2.fill(electron.p(),ectot);
	     if(cc_nphe>=NPHE_MIN){
		h2_P_vs_ECtotP2.fill(electron.p(),ectot/electron.p());
	     }
	     if(ECsector>=1 && ECsector<=6){
	      if(coeff>=0 && coeff<5){ 
  	       double a = EC_SamplingFrac_C[ECsector-1][0];
	       double b = EC_SamplingFrac_C[ECsector-1][1];
	       double c = EC_SamplingFrac_C[ECsector-1][2];
	       double d = EC_SamplingFrac_C[ECsector-1][3];
	       double f = EC_SamplingFrac_C[ECsector-1][4];

	       double mom = electron.p();
	       double centroid = a + b*mom + c*mom*mom;
	       double sigma = Math.sqrt(d*d + f*f/Math.sqrt(mom));
	       double Nsigma = 2.5;
	       double diff = Math.abs(ectot/mom - centroid);

	       cutECoverP = (diff < Nsigma*sigma) ? true : false;
	      }
	     }
//	     cutECoverP = EC_SamplingFraction_Cut(electron.p(),ectot,ECsector,12);
	      if(cutECoverP) h2_P_vs_ECtotP_cut.fill(electron.p(),ectot/electron.p());
	      if(cutECoverP) h2_P_vs_ECtot2cut.fill(electron.p(),ectot);

//	     }
	  }
	 }
	}
     }
}
  
ec.divide(3,4);
  
ec.cd(0);
ec.getPad().getAxisZ().setLog(true);
ec.draw(h2_P_vs_ECtot2);
//ec.cd(1).draw(hQ2);
ec.cd(1);
ec.getPad().getAxisZ().setLog(true);
ec.draw(h2_P_vs_ECtot2cut);
ec.cd(2).draw(h1_cc_nphe_cut);
ec.cd(3).draw(h1_cc_nphe);
ec.cd(4);
ec.draw(h1_cc_nphe);
ec.draw(h1_cc_nphe_cut, "same");
ec.cd(5).draw(h2_ECin_vs_ECout);
ec.cd(6).draw(h2_ECin_vs_ECout_cut);
ec.cd(8).draw(h1_dtECSC);
ec.cd(7);
ec.getPad().getAxisZ().setLog(true);
ec.draw(h2_ECin_vs_ECout);
ec.draw(h2_ECin_vs_ECout_cut, "same");

ec.cd(9);
ec.getPad().getAxisZ().setLog(true);
ec.draw(h2_P_vs_ECtotP);
ec.cd(10);
ec.getPad().getAxisZ().setLog(true);
ec.draw(h2_P_vs_ECtotP2);
ec.cd(11);
ec.getPad().getAxisZ().setLog(true);
ec.draw(h2_P_vs_ECtotP_cut);
