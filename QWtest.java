import org.jlab.jnp.hipo4.io.*;
import org.jlab.jnp.hipo4.data.*;

import org.jlab.groot.data.*;
import org.jlab.groot.graphics.*;

import org.jlab.clas.physics.*;

HipoReader reader = new HipoReader();
reader.open("recsisD1.hipo");

Event     event = new Event();
Bank  particles = new Bank(reader.getSchemaFactory().getSchema("EVENT::particle"));

H1F  hW = new H1F("hW" ,100, 0.5, 4.0);
H1F hQ2 = new H1F("hQ2",100, 0.1, 4.0);
hW.setTitleX("W [GeV]");
hQ2.setTitleX("Q^2 [GeV/c^2]");

TCanvas ec = new TCanvas("ec",800,400);


int counter = 0;
int    elec = 0;

while(reader.hasNext()==true){
     reader.nextEvent(event);
     event.read(particles);
     if(particles.getRows()>0){
         int pid = particles.getInt("pid",0);
         if(pid==11){
            elec++;
         }
     }
     counter++;
}
System.out.println("processed # " + counter + " , electrons : " + elec);

LorentzVector  vBeam   = new LorentzVector(0.0,0.0,10.6,10.6);
LorentzVector  vTarget = new LorentzVector(0.0,0.0,0.0,0.938);
LorentzVector electron = new LorentzVector();
LorentzVector       vW = new LorentzVector();
LorentzVector      vQ2 = new LorentzVector();

reader.getEvent(event,0);

while(reader.hasNext()==true){
     reader.nextEvent(event);
     event.read(particles);
     if(particles.getRows()>0){
         int pid = particles.getInt("pid",0);
         if(pid==11){
            electron.setPxPyPzM(
                particles.getFloat("px",0),
                particles.getFloat("py",0),
                particles.getFloat("pz",0),
                0.0005
                );
            vW.copy(vBeam);
            vW.add(vTarget).sub(electron);
            vQ2.copy(vBeam);
            vQ2.sub(electron);
            hW.fill(vW.mass());
            hQ2.fill(-vQ2.mass2());
         }
     }
}

ec.divide(2,1);

ec.cd(0).draw(hW);
ec.cd(1).draw(hQ2);


