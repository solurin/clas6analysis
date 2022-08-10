//---- imports for HIPO4 library
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

 
// Create a HipoReader object and load in a file.  In this case, myInput.hipo is a placeholder that really does not exist.
 
HipoReader reader = new HipoReader(); // Create a reader obejct
reader.open("SimOuts.hipo"); // open a file
 
// Create an Event class and a bank instance, in this case for particle bank.
// The event instance is used to read events from the file, and Bank instance is used to read particle bank from each event. 
// NOTE ! the file must be opened first in order to initialize Bank object since it takes the schema for the bank from file dictionary.
 
SchemaFactory factory = reader.getSchemaFactory();
factory.show();
 
Bank  particles = new Bank(factory.getSchema("REC::Particle")); 
Event     event = new Event();
 
// Loop through events and print the first bank REC::Particle with non 0 rows
//while(reader.hasNext()==true){
//     reader.nextEvent(event);
//     event.read(particles);
//     if(particles.getRows()>0){
//         particles.show();
//         break;
//     }
//}
H2F hparticle = new H2F("hparticle" ,100, 0, 4, 100, 0, 1);
H2F  hproton = new H2F("hproton" ,100, 0, 4, 100, 0, 1);
H2F helectron = new H2F("helectron",100, 0, 4, 100, 0, 1);
H2F  hpiplus = new H2F("hpiplus" ,100, 0, 4, 100, 0, 1);
H2F hpiminus = new H2F("hpiminus",100, 0, 4, 100, 0, 1);
H2F  hgamma = new H2F("hgamma" ,100, 0, 4, 100, 0, 1);
H2F hkplus = new H2F("hkplus",100, 0, 4, 100, 0, 1);
H2F  hkminus = new H2F("hkminus" ,100, 0, 4, 100, 0, 1);
H2F hunknown = new H2F("hunknown",100, 0, 4, 100, 0, 1);

H2F hdbetaelectron = new H2F("hdbetaelectron", 100, 0, 1, 100, -1, 1);
H2F hdbetaproton = new H2F("hdbetaproton", 100, 0, 1, 100, -1, 1);
H2F hdbetapiplus = new H2F("hdbetapiplus", 100, 0, 1, 100, -1, 1);
H2F hdbetapiminus = new H2F("hdbetapiminus", 100, 0, 1, 100, -1, 1);
H2F hdbetakplus = new H2F("hdbetakplus", 100, 0, 1, 100, -1, 1);
H2F hdbetakminus = new H2F("hdbetakminus", 100, 0, 1, 100, -1, 1);
H2F hdbetagamma = new H2F("hdbetagamma", 100, 0, 1, 100, -1, 1);
H2F hdbetaunknown = new H2F("hdbetaunknown", 100, 0, 1, 100, -1, 1);
H2F hnu = new H2F("hnu", 100, 0, 10, 100, 0, 20);
H2F hy = new H2F("hy", 100, 0, 10, 100, 0, 1);
H2F hxb = new H2F("hxb", 100, 0, 10, 100, -2, 2);
H2F hxbW = new H2F("hxbW", 100, 0, 1, 100, 4, 6);
//H2F hy2 = new H2F("hy2", 100, 0, 1, 100, -10000, 10000);
//H2F hxb2 = new H2F("hxb2", 100, 0, 1, 100, -10000, 10000);


H1F hmsquarede = new H1F("hmsquarede",100, -0.001, 0.003);
H1F hmsquaredp = new H1F("hmsquaredp",100, -0.1, 1.5);
H1F hmsquaredpiplus = new H1F("hmsquaredpiplus",100, -0.1, 1.5);
H1F hmsquaredpiminus = new H1F("hmsquaredpiminus",100, -0.1, 1.5);
H1F hmsquaredkplus = new H1F("hmsquaredkplus",100, -0.1, 1.5);
H1F hmsquaredkminus = new H1F("hmsquaredkminus",100, -0.1, 1.5);
H1F hmsquaredunknown = new H1F("hmsquaredunknown",100, -0.1, 1.5);
H1F hmsquaredpositive = new H1F("hmsquaredpositive",100, -0.1, 1.5);
H1F hmsquarednegative = new H1F("hmsquarednegative",100, -0.1, 1.5);
H1F hnu1 = new H1F("hnu1",100, 0, 10);
H1F hy1 = new H1F("hy1",100, 0, 1);
H1F hxb1 = new H1F("hxb1",100, 0, 0.1);
H1F hcostheta = new H1F("hcostheta",100, 0, 1);

hproton.setTitleX("beta vs p: proton");
helectron.setTitleX("beta vs p: electron");
hpiplus.setTitleX("beta vs p: piplus");
hpiminus.setTitleX("beta vs p: piminus");
hgamma.setTitleX("beta vs p: gamma");
hkplus.setTitleX("beta vs p: kplus");
hkminus.setTitleX("beta vs p: kminus");
hunknown.setTitleX("beta vs p: unknown");
hmsquarede.setTitleX("msquared (electrons)");
hmsquaredp.setTitleX("msquared (protons)");
hmsquaredpiplus.setTitleX("msquared (piplus)");
hmsquaredpiminus.setTitleX("msquared (piminus)");
hmsquaredkplus.setTitleX("msquared (kplus)");
hmsquaredunknown.setTitleX("msquared (unknown)");
hmsquaredkminus.setTitleX("msquared (kminus)");
hmsquaredpositive.setTitleX("msquared (positive particles)");
hmsquarednegative.setTitleX("msquared (negative particles)");
hdbetaelectron.setTitleX("p vs dbeta (electrons)");
hdbetaproton.setTitleX("p vs dbeta (protons)");
hdbetapiplus.setTitleX("p vs dbeta (piplus)");
hdbetapiminus.setTitleX("p vs dbeta (piminus)");
hdbetakplus.setTitleX("p vs dbeta (kplus)");
hdbetakminus.setTitleX("p vs dbeta (kminus)");
hdbetagamma.setTitleX("p vs dbeta (gamma)");
hdbetaunknown.setTitleX("p vs dbeta (unknown)");
hnu.setTitleX("E beam minus E measured vs p");
hy.setTitleX("nu / beam energy vs p");
hxb.setTitleX("(Q2 / 2*nu*Mp) vs p");
hnu1.setTitleX("e beam - e measured");
hy1.setTitleX("nu vs e beam");
hxb1.setTitleX("Q2 vs 2*nu*Mp");
hxbW.setTitleX("xb vs W");
hcostheta.setTitleX("cos theta");


LorentzVector   vparticle = new LorentzVector();
LorentzVector  vBeam   = new LorentzVector(0.0,0.0,10.6,10.6);
LorentzVector      vQ2 = new LorentzVector();
LorentzVector  vTarget = new LorentzVector(0.0,0.0,0.0,0.938);
LorentzVector electron = new LorentzVector(); 
LorentzVector proton   = new LorentzVector();
LorentzVector       vW = new LorentzVector();
LorentzVector vVirtPhoton = new LorentzVector();



double mass = 0.0;
double beta = 0.0;
reader.getEvent(event,0);
while(reader.hasNext()==true){
 reader.nextEvent(event);
 event.read(particles);
 if(particles.getRows()>0){
	for (int row=0; row<particles.getRows(); row++){
         float beta = particles.getFloat("beta",row);
         int pid = particles.getInt("pid",row);
	 int charge = particles.getInt("charge",row); 	
	 if (pid==0){
	  mass = 0.0;
	 }else{
	  mass =  PDGDatabase.getParticleById(pid).mass();
	  }
	 
           vparticle.setPxPyPzM(
           particles.getFloat("px",row), 
           particles.getFloat("py",row),
           particles.getFloat("pz",row),
	   mass    
           );
	 hparticle.fill(vparticle.p(), beta);
	 if(pid==2212) hproton.fill(vparticle.p(), beta);
	 if(pid==11) helectron.fill(vparticle.p(), beta);
	 if(pid==211) hpiplus.fill(vparticle.p(), beta);
	 if(pid==-211) hpiminus.fill(vparticle.p(), beta);
	 if(pid==321) hkplus.fill(vparticle.p(), beta);
	 if(pid==-321) hkminus.fill(vparticle.p(), beta);
	 if(pid==22) hgamma.fill(vparticle.p(), beta);
	 if(pid==0) hunknown.fill(vparticle.p(), beta);
	 double masssquared = Math.pow(vparticle.p(),2)*((1/Math.pow(beta,2))-1);
//	 hmsquared.fill(masssquared);
	 if(pid==11) hmsquarede.fill(masssquared);
	 if(pid==2212) hmsquaredp.fill(masssquared);
	 if(pid==211) hmsquaredpiplus.fill(masssquared);
	 if(pid==-211) hmsquaredpiminus.fill(masssquared);
	 if(pid==321) hmsquaredkplus.fill(masssquared);
	 if(pid==-321) hmsquaredkminus.fill(masssquared);
	 if(pid==0) hmsquaredunknown.fill(masssquared);
	 if(charge==1) hmsquaredpositive.fill(masssquared);
	 if(charge==-1) hmsquarednegative.fill(masssquared);
	 double betamass = Math.sqrt((Math.pow(vparticle.p(),2))/(Math.pow(vparticle.p(),2)+Math.pow(mass, 2)));	
	 double dbeta = beta - betamass;
	 if(pid==11) hdbetaelectron.fill(vparticle.p(), dbeta);
	 if(pid==2212) hdbetaproton.fill(vparticle.p(), dbeta);
	 if(pid==211) hdbetapiplus.fill(vparticle.p(), dbeta);
	 if(pid==-211) hdbetapiminus.fill(vparticle.p(), dbeta);
	 if(pid==321) hdbetakplus.fill(vparticle.p(), dbeta);
	 if(pid==321) hdbetakminus.fill(vparticle.p(), dbeta);
	 if(pid==22) hdbetagamma.fill(vparticle.p(), dbeta);
	 if(pid==0) hdbetaunknown.fill(vparticle.p(), dbeta);          
         if(pid==11){
		double nu = vBeam.e() - vparticle.e();
		double y = nu / vBeam.e();
		double xb = -vQ2.mass2() / (2*nu*.938);
		hnu.fill(vparticle.p(), nu);
          	hy.fill(vparticle.p(), y);
		vW.sub(vparticle);
		vVirtPhoton.sub(vparticle);
		hxb.fill(vparticle.p(), xb);
         	hnu1.fill(nu);
         	hy1.fill(y);
         	hxb1.fill(xb);
         	hxbW.fill(xb, vW.mass());
	 }
	 vQ2.copy(vBeam);
	 vQ2.sub(vparticle);
	 vW.copy(vBeam);
         vW.add(vTarget);
	 vVirtPhoton.copy(vBeam);
	 if(pid==2212){
		double dotprod = vVirtPhoton.vect().dot(vparticle.vect());
		double magn = vparticle.vect().mag() * vVirtPhoton.vect().mag();
		double costheta = dotprod / magn;
		double costheta2 = Math.cos(Math.toRadians(vVirtPhoton.vect().theta(vparticle.vect())));
		hcostheta.fill(costheta); 
//		double costhetameasured = 
//		System.out.println(vVirtPhoton.vect().theta(vparticle.vect()));
//		System.out.println(costheta);
//		System.out.println((vVirtPhoton.vect().theta(vparticle.vect())) / costheta);
//		System.out.println(costheta2 / costheta);
	 }
//	 if(pid==11) hy2.fill(nu, vBeam.e());
//	 if(pid==11) hxb2.fill(vQ2.mass(), (2*nu*mass));	
// change hW to electron, proton. etc
           // if(charge<0) negative_count++;

     }
 }
}
TCanvas ec = new TCanvas("ec",900,900);


ec.divide(3,3);
  
ec.cd(0).draw(hproton);
ec.cd(1).draw(helectron);
ec.cd(2).draw(hpiplus);
ec.cd(3).draw(hpiminus);
ec.cd(4).draw(hgamma);
ec.cd(5).draw(hkplus);
ec.cd(6).draw(hkminus);
//ec.cd(6)
//ec.getPad().getAxisY().setLog(true);
//ec.draw(hmsquared);
//separate into positive and negatives for ^, leave out gamma --> 9 total
//ec.cd(7).draw(hunknown);
ec.cd(7).draw(hmsquarede);
ec.cd(8).draw(hmsquaredp);

TCanvas ec2 = new TCanvas("ec2",900,900);

ec2.divide(3,3);

ec2.cd(0).draw(hmsquarede);
ec2.cd(1).draw(hmsquaredp);
ec2.cd(2).draw(hmsquaredpiplus);
ec2.cd(3).draw(hmsquaredpiminus);
ec2.cd(4).draw(hmsquaredkplus);
ec2.cd(5).draw(hmsquaredkminus);
ec2.cd(6).draw(hmsquaredunknown);
ec2.cd(7);
ec2.getPad().getAxisY().setLog(true);
ec2.draw(hmsquaredpositive);
ec2.cd(8);
ec2.getPad().getAxisY().setLog(true);
ec2.draw(hmsquarednegative);

TCanvas ec3 = new TCanvas("ec3",900,900);

ec3.divide(3,3);

ec3.cd(0).draw(hdbetaelectron);
ec3.cd(1).draw(hdbetaproton);
ec3.cd(2).draw(hdbetapiplus);
ec3.cd(3).draw(hdbetapiminus);
ec3.cd(4).draw(hdbetakplus);
ec3.cd(5).draw(hdbetakminus);
ec3.cd(6).draw(hdbetagamma);
ec3.cd(7).draw(hdbetaunknown);
 
TCanvas ec4 = new TCanvas("ec4",900,900);

ec4.divide(3,3);

ec4.cd(0).draw(hnu);
ec4.cd(1).draw(hy);
ec4.cd(2).draw(hxb);
ec4.cd(3).draw(hnu1);
ec4.cd(4).draw(hy1);
ec4.cd(5).draw(hxb1);
ec4.cd(6).draw(hxbW);
ec4.cd(7).draw(hcostheta);
//ec4.cd(3).draw(hy2);
//ec4.cd(4).draw(hxb2);

