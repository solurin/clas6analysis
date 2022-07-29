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


 
// Create a HipoReader object and load in a file.  In this case, myInput.hipo is a placeholder that really does not exist.
 
HipoReader reader = new HipoReader(); // Create a reader obejct
reader.open("recsis2.hipo"); // open a file
 
// Create an Event class and a bank instance, in this case for particle bank.
// The event instance is used to read events from the file, and Bank instance is used to read particle bank from each event. 
// NOTE ! the file must be opened first in order to initialize Bank object since it takes the schema for the bank from file dictionary.
 
SchemaFactory factory = reader.getSchemaFactory();
factory.show();
 
Bank  particles = new Bank(factory.getSchema("EVENT::particle")); 
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

hproton.setTitleX("beta vs p: proton");
helectron.setTitleX("beta vs p: electron");
hpiplus.setTitleX("beta vs p: piplus");
hpiminus.setTitleX("beta vs p: piminus");
hgamma.setTitleX("beta vs p: gamma");
hkplus.setTitleX("beta vs p: kplus");
hkminus.setTitleX("beta vs p: kminus");
hunknown.setTitleX("beta vs p: unknown");

LorentzVector       vparticle = new LorentzVector();
//		LorentzVector	    velectrons = new LorentzVector();
//		LorentzVector	    vpiplus = new LorentzVector();
//		LorentzVector	    vpiminus = new LorentzVector();
//		LorentzVector	    vgamma = new LorentzVector();
//		LorentzVector	    vkplus = new LorentzVector();
//		LorentzVector	    vkminus = new LorentzVector();
//		LorentzVector	    vunknown = new LorentzVector( 

reader.getEvent(event,0);
while(reader.hasNext()==true){
 reader.nextEvent(event);
 event.read(particles);
 if(particles.getRows()>0){
	for (int row=0; row<particles.getRows(); row++){
        float beta = particles.getFloat("beta",row);
        int pid = particles.getInt("pid",row);		 
           vparticle.setPxPyPzM(
           particles.getFloat("px",row), 
           particles.getFloat("py",row),
           particles.getFloat("pz",row),
           particles.getFloat("mass",row)
           );
	 hparticle.fill(vparticle.p(), beta);
	 if(pid==2212) hproton.fill(vparticle.p(), beta);
	 if(pid==11 || pid==0) helectron.fill(vparticle.p(), beta);
	 if(pid==211) hpiplus.fill(vparticle.p(), beta);
	 if(pid==-211) hpiminus.fill(vparticle.p(), beta);
	 if(pid==321) hkplus.fill(vparticle.p(), beta);
	 if(pid==-321) hkminus.fill(vparticle.p(), beta);
	 if(pid==22) hgamma.fill(vparticle.p(), beta);
	 if(pid==0) hunknown.fill(vparticle.p(), beta);
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
ec.cd(7).draw(hunknown);
ec.cd(8).draw(hparticle);

