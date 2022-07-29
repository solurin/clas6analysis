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
while(reader.hasNext()==true){
     reader.nextEvent(event);
     event.read(particles);
     if(particles.getRows()>0){
         particles.show();
         break;
     }
}

H2F  hproton = new H2F("hproton" ,7,0, 7, 7, 0, 7);
H2F helectron = new H2F("helectron",7, 0, 7, 7, 0, 7);
H2F  hpiplus = new H2F("hpiplus" ,7, 0, 7, 7, 0, 7);
H2F hpiminus = new H2F("hpiminus",7, 0, 7, 7, 0, 7);
H2F  hgamma = new H2F("hgamma" ,7, 0, 7, 7, 0, 7);
H2F hkplus = new H2F("hkplus",7, 0, 7, 7, 0, 7);
H2F  hkminus = new H2F("hkminus" ,7, 0, 7, 7, 0, 7);
H2F hunknown = new H2F("hunknown",7, 0, 7, 7, 0, 7);

hproton.setTitleX("protons per event");
hproton.setTitleY("piplus per event");
helectron.setTitleX("electrons per event");
helectron.setTitleY("piminus per event");
hpiplus.setTitleX("piplus per event");
hpiplus.setTitleY("kplus per event");
hpiminus.setTitleX("piminus per event");
hpiminus.setTitleY("kminus per event");
hgamma.setTitleX("gammas per event");
hgamma.setTitleY("unknown per event");
//hkplus.setTitleX("kpluses per event");
//hkminus.setTitleX("kminuses per event");
//hunknown.setTitleX("unknowns per event");

reader.getEvent(event,0);
while(reader.hasNext()==true){
     reader.nextEvent(event);
     event.read(particles);
      
     int protons = 0;
     int  electrons = 0;
     int piplus = 0;
     int piminus = 0;
     int gamma = 0;
     int kplus = 0;
     int kminus = 0;
     int unknown = 0;
    // int negative_count = 0;
        
     if(particles.getRows()>0){
         for(int row = 0; row < particles.getRows(); row++){
             int pid = particles.getInt("pid",row);
             if(pid==2212) protons++;
//		particles.show();
//		System.out.println("protons:");
//		System.out.println(protons);
	     if(pid==11) electrons++;
//                particles.show();
//                System.out.println("electrons:");
//                System.out.println(electrons);
	     if(pid==211) piplus++;
//                particles.show();
//                System.out.println("pi+:");
//                System.out.println(pi+);
	     if(pid==-211) piminus++;
//                particles.show();
//                System.out.println("pi-:");
//                System.out.println(pi-);
	     if(pid==22) gamma++;
//                particles.show();
 //               System.out.println("gamma:");
  //              System.out.println(gamma);
	     if(pid==321) kplus++;
	     if(pid==-321) kminus++;
	     if(pid==0) unknown++;
	    }
	    hproton.fill(protons, piplus);
	    helectron.fill(electrons, piminus);
	    hpiplus.fill(piplus, kplus);
	    hpiminus.fill(piminus, kminus);
	    hgamma.fill(gamma, unknown);
//	    hkplus.fill(kplus);
//	    hkminus.fill(kminus);
//	    hunknown.fill(unknown);

//		particles.show();
//		System.out.println("protons:");
//		System.out.println(protons);
//		System.out.println("electrons:");
//                System.out.println(electrons);
//		System.out.println("piplus:");
//                System.out.println(piplus);
/*		System.out.println("piminus:");
                System.out.println(piminus);
		System.out.println("gamma:");
                System.out.println(gamma);
		System.out.println("kplus:");
                System.out.println(kplus);
		System.out.println("kminus:");
                System.out.println(kminus);
		System.out.println("unknown:");
                System.out.println(unknown);*/
// make histograms for each particles
// 		LorentzVector       vprotons = new LorentzVector();
//		LorentzVector	    velectrons = new LorentzVector();
//		LorentzVector	    vpiplus = new LorentzVector();
//		LorentzVector	    vpiminus = new LorentzVector();
//		LorentzVector	    vgamma = new LorentzVector();
//		LorentzVector	    vkplus = new LorentzVector();
//		LorentzVector	    vkminus = new LorentzVector();
//		LorentzVector	    vunknown = new LorentzVector( 
//		while(reader.hasNext()==true){
//    		 reader.nextEvent(event);
//    		 event.read(particles);
//    		 if(particles.getRows()>0){
//        		 int pid = particles.getInt("pid",0);
//        		 if(pid==11){
//           		 electron.setPxPyPzM(
//               		 particles.getFloat("px",0), 
//               		 particles.getFloat("py",0),
//               		 particles.getFloat("pz",0),
//               		 0.0005
//               		 );


// change hW to electron, proton. etc
           // if(charge<0) negative_count++;

     }
}
// draw statement at end
//Tcanvas 3x3
//number of particles per event
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
