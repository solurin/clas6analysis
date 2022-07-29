//---- imports for HIPO4 library
import org.jlab.jnp.hipo4.io.*;
import org.jlab.jnp.hipo4.data.*;
 
// Create a HipoReader object and load in a file.  In this case, myInput.hipo is a placeholder that really does not exist.
 
HipoReader reader = new HipoReader(); // Create a reader obejct
reader.open("recsisD1.hipo"); // open a file
 
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
		particles.show();
		System.out.println("protons:");
		System.out.println(protons);
		System.out.println("electrons:");
                System.out.println(electrons);
		System.out.println("piplus:");
                System.out.println(piplus);
		System.out.println("piminus:");
                System.out.println(piminus);
		System.out.println("gamma:");
                System.out.println(gamma);
		System.out.println("kplus:");
                System.out.println(kplus);
		System.out.println("kminus:");
                System.out.println(kminus);
//	System.out.println(protons);
           // if(charge<0) negative_count++;
         }
        // if(positive_count>=2&&negative_count>=2){
          //   particles.show();
            // break;
        // }
     }
}
