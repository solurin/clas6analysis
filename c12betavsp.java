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
H2F hdbetaunknown = new H2F("hdbetaunknown", 100, 0, 1, 100, -1, 1)



H1F hmsquarede = new H1F("hmsquarede",100, -0.001, 0.003);
H1F hmsquaredp = new H1F("hmsquaredp",100, -0.1, 1.5);
H1F hmsquaredpiplus = new H1F("hmsquaredpiplus",100, -0.1, 1.5);
H1F hmsquaredpiminus = new H1F("hmsquaredpiminus",100, -0.1, 1.5);
H1F hmsquaredkplus = new H1F("hmsquaredkplus",100, -0.1, 1.5);
H1F hmsquaredkminus = new H1F("hmsquaredkminus",100, -0.1, 1.5);
H1F hmsquaredunknown = new H1F("hmsquaredunknown",100, -0.1, 1.5);
H1F hmsquaredpositive = new H1F("hmsquaredpositive",100, -0.1, 1.5);
H1F hmsquarednegative = new H1F("hmsquarednegative",100, -0.1, 1.5);



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






LorentzVector       vparticle = new LorentzVector();
//		LorentzVector	    velectrons = new LorentzVector();
//		LorentzVector	    vpiplus = new LorentzVector();
//		LorentzVector	    vpiminus = new LorentzVector();
//		LorentzVector	    vgamma = new LorentzVector();
//		LorentzVector	    vkplus = new LorentzVector();
//		LorentzVector	    vkminus = new LorentzVector();
//		LorentzVector	    vunknown = new LorentzVector( 
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



