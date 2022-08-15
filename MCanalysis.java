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
import org.jlab.clas.pdg.*;

HipoReader reader = new HipoReader(); // Create a reader obejct
reader.open("SimOuts.hipo"); // open a file
 
// Create an Event class and a bank instance, in this case for particle bank.
// The event instance is used to read events from the file, and Bank instance is used to rea$
// NOTE ! the file must be opened first in order to initialize Bank object since it takes th$
///// add tpProton and divide REC data by MC data for theta and p 
SchemaFactory factory = reader.getSchemaFactory();
factory.show();
 
Bank  particles = new Bank(factory.getSchema("MC::Particle")); 
Bank  particles2 = new Bank(factory.getSchema("REC::Particle"));
Event     event = new Event();

LorentzVector proton = new LorentzVector();
LorentzVector proton2 = new LorentzVector();
LorentzVector  vBeam   = new LorentzVector(0.0,0.0,5.014,5.014);
LorentzVector  vTarget = new LorentzVector(0.0,0.0,0.0,0.938);
LorentzVector electron = new LorentzVector(); 
LorentzVector       vW = new LorentzVector(); 
LorentzVector	   vQ2 = new LorentzVector();

H1F  hproton = new H1F("hproton" ,7,0, 7);
H1F helectron = new H1F("helectron",7, 0, 7);
H1F  hpiplus = new H1F("hpiplus" ,7, 0, 7);
H1F hpiminus = new H1F("hpiminus",7, 0, 7);
H1F  hgamma = new H1F("hgamma" ,7, 0, 7);
H1F hkplus = new H1F("hkplus",7, 0, 7);
H1F  hkminus = new H1F("hkminus" ,7, 0, 7);
H1F hunknown = new H1F("hunknown",7, 0, 7);
H1F hphi1 = new H1F("hphi", 360, -180, 180.0);
H1F htheta1 = new H1F("htheta", 90, 0, 90.0);
H1F hphi1proton = new H1F("hphi", 360, -180, 180.0);
H1F htheta1proton = new H1F("htheta", 90, 0, 90.0);
H1F hthetaratio = new H1F("hthetaratio", 90, 0, 90.0);
H1F htheta1REC = new H1F("htheta1REC", 90, 0, 90.0);
H1F helectronpMC = new H1F("helectronpMC", 100, 0, 10);
H1F helectronpREC = new H1F("helectronpREC", 100, 0, 10);
H1F hprotonpMC = new H1F("hprotonpMC", 100, 0, 5);
H1F hprotonpREC = new H1F("hprotonpREC", 100, 0, 5);
H1F htheta1protonREC = new H1F("htheta", 90, 0, 90);


H2F htheta = new H2F("htheta", 90, 0, 90, 360, -180, 180);
H2F hphi = new H2F("hphi", 360, -180, 180, 360, -180, 180);
H2F hQ2 = new H2F("hQ2",100, 0.1, 8.0, 100, 0.5, 8.0);
H2F hW = new H2F("hW",100, 0.1, 8.0, 100, 0.5, 8.0);


hproton.setTitleX("protons per event");
helectron.setTitleX("electrons per event");
hpiplus.setTitleX("piplus per event");
hpiminus.setTitleX("piminus per event");
hgamma.setTitleX("gammas per event");
hkplus.setTitleX("kpluses per event");
hkminus.setTitleX("kminuses per event");
hunknown.setTitleX("unknowns per event");
htheta.setTitleX("theta vs phi");
hQ2.setTitleY("W [GeV]");
hQ2.setTitleX("Q^2 [GeV/c^2]");
hQ2.setTitle("Q^2 vs W");
htheta1.setTitleX("Theta [Degrees] MC = green, REC = red");
hphi1.setTitleX("Phi [Degrees]");
htheta1proton.setTitleX("Theta [Degrees] -- MC");
hphi1proton.setTitleX("Phi [Degrees]");
hthetaratio.setTitleX("ratio -- theta (REC/MC)");
helectronpMC.setTitleX("electron p, MC = green, REC = red");
helectronpREC.setTitleX("electron p -- REC");
hprotonpMC.setTitleX("proton p, MC = green, REC = red");
hprotonpREC.setTitleX("proton p, REC");
htheta1protonREC.setTitleX("theta proton -- REC");
htheta1REC.setTitleX("theta (electron) -- REC");
hprotonpMC.setFillColor(43);
hprotonpREC.setFillColor(42);
htheta1proton.setFillColor(43);
htheta1protonREC.setFillColor(42);
htheta1.setFillColor(43);
htheta1REC.setFillColor(42);
helectronpMC.setFillColor(43);
helectronpREC.setFillColor(42);

//while(reader.hasNext()==true){
//     reader.nextEvent(event);
//     event.read(particles);
//     if(particles.getRows()>0){
//         particles.show();
//         break;
//     }
//}

while(reader.hasNext()==true){
     reader.nextEvent(event);
     event.read(particles);
     event.read(particles2);
     
     int protons = 0;
     int electrons = 0;
     int piplus = 0;
     int piminus = 0;
     int gamma = 0;
     int kplus = 0;
     int kminus = 0;
     int unknown = 0;
 
     if(particles.getRows()>0){
         for(int row = 0; row < particles.getRows(); row++){
             int pid = particles.getInt("pid",row);
//             int charge = particles.getInt("charge",row);
             if(pid==2212) protons++;
	     if(pid==11){ 
		electrons++;
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
		hQ2.fill(-vQ2.mass2(), vW.mass());
		htheta.fill(Math.toDegrees(electron.theta()), Math.toDegrees(electron.phi()));
		htheta1.fill(Math.toDegrees(electron.theta()));
		hphi1.fill(Math.toDegrees(electron.phi()));
		helectronpMC.fill(electron.p());
	     }
	     if(pid==11){ 
                electrons++;
                electron.setPxPyPzM(
                        particles2.getFloat("px",row), 
                        particles2.getFloat("py",row),
                        particles2.getFloat("pz",row),
                        0.0005
                        );
                vW.copy(vBeam);
                vW.add(vTarget);
                vW.sub(electron);

                vQ2.copy(vBeam);
                vQ2.sub(electron);
                hQ2.fill(-vQ2.mass2(), vW.mass());
//                htheta.fill(Math.toDegrees(electron.theta()), Math.toDegrees(electron.phi()));
                htheta1REC.fill(Math.toDegrees(electron.theta()));
		helectronpREC.fill(electron.p());
//                hphi1.fill(Math.toDegrees(electron.phi()));
	     }
	     if(pid==2212){ 
//                protons++;
                proton.setPxPyPzM(
                        particles.getFloat("px",row), 
                        particles.getFloat("py",row),
                        particles.getFloat("pz",row),
                        0.938
                        );
                vW.copy(vBeam);
                vW.add(vTarget);
                vW.sub(electron);

                vQ2.copy(vBeam);
                vQ2.sub(electron);
                hQ2.fill(-vQ2.mass2(), vW.mass());
//                hthetaproton.fill(Math.toDegrees(proton.theta()), Math.toDegrees(proton.phi()));
                htheta1proton.fill(Math.toDegrees(proton.theta()));
		hprotonpMC.fill(proton.p());               
//		hphi1.fill(Math.toDegrees(electron.phi()));

	     } 
	     if(pid==2212){ 
//                protons++;
                proton2.setPxPyPzM(
                        particles2.getFloat("px",row), 
                        particles2.getFloat("py",row),
                        particles2.getFloat("pz",row),
                        0.938
                        );
                vW.copy(vBeam);
                vW.add(vTarget);
                vW.sub(electron);

                vQ2.copy(vBeam);
                vQ2.sub(electron);
                hQ2.fill(-vQ2.mass2(), vW.mass());
//                hthetaproton.fill(Math.toDegrees(proton.theta()), Math.toDegrees(proton.phi()));
                htheta1protonREC.fill(Math.toDegrees(proton2.theta()));
                hprotonpREC.fill(proton2.p());               
//              hphi1.fill(Math.toDegrees(electron.phi()));

             } 
	     if(pid==211) piplus++;
	     if(pid==-211) piminus++;
	     if(pid==22) gamma++;
	     if(pid==321) kplus++;
             if(pid==-321) kminus++;
             if(pid==0) unknown++;
         }
            hproton.fill(protons);
            helectron.fill(electrons);
            hpiplus.fill(piplus);
            hpiminus.fill(piminus);
            hgamma.fill(gamma);
            hkplus.fill(kplus);
            hkminus.fill(kminus);
            hunknown.fill(unknown);
     }
}

TCanvas ec = new TCanvas("ec",900,900);


ec.divide(3,3);
  
ec.cd(0).draw(hproton);
ec.cd(1).draw(helectron);
ec.cd(2).draw(hpiplus);
ec.cd(3).draw(hpiminus);
ec.cd(4).draw(hgamma);
//ec.cd(4).draw(honePoneEexact);
ec.cd(5).draw(hkplus);
ec.cd(6).draw(hkminus);
ec.cd(7).draw(hunknown);
//ec.cd(7);

TCanvas ec2 = new TCanvas("ec",900,900);

ec2.divide(3,3);

ec2.cd(0).draw(htheta);
ec2.cd(1).draw(hQ2);
ec2.cd(2).draw(htheta1);
ec2.cd(3).draw(hphi1);
ec2.cd(4).draw(htheta1proton);
//ec2.cd(5).draw(hphi1proton);
ec2.cd(5).draw(htheta1protonREC);
ec2.cd(6);
ec2.draw(htheta1proton);
ec2.draw(htheta1protonREC, "same");
ec2.cd(7);
ec2.draw(hprotonpMC);
ec2.draw(hprotonpREC, "same");
H1F hthetaprotonratio = new H1F().divide(htheta1proton, htheta1protonREC);
hthetaprotonratio.setTitleX("theta ratio, proton");
ec2.cd(8).draw(hthetaprotonratio);


TCanvas ec3 = new TCanvas("ec",900,900);

ec3.divide(3,3);

ec3.cd(0).draw(htheta1);
ec3.cd(1).draw(htheta1REC);
ec3.cd(2).draw(helectronpMC);
ec3.cd(3).draw(helectronpREC);
ec3.cd(4);
ec3.draw(helectronpMC);
ec3.draw(helectronpREC, "same");
ec3.cd(5);
ec3.draw(htheta1);
ec3.draw(htheta1REC, "same");
ec3.cd(6);
H1F hthetaeratio = new H1F().divide(htheta1REC, htheta1);
hthetaeratio.setTitleX("theta ratio, electron");
ec3.draw(hthetaeratio);
ec3.cd(7);
H1F hperatio = new H1F().divide(helectronpREC, helectronpMC);
hperatio.setTitleX("momentum ratio, electron");
ec3.draw(hperatio);
ec3.cd(8);
H1F hppratio = new H1F().divide(hprotonpREC, hprotonpMC);
hppratio.setTitleX("momentum ratio, proton");
ec3.draw(hppratio);
