# clas6analysis
Java files to be run in jshell to analyze and plot histograms of clas6 simulation data (Summer 2022)

***all files are meant for clas6 analysis unless they have "c12", "CLAS12" etc. in their file name***

1. particlecount*
counts numbers of each type of particle found in each event and prints to the screen 

2. particlegraph*
creates H1F histograms for each type of particle counting the number of events with 0,1,2,(and so on) counts of that particle 

3. QW2D* 
creates H2F histogram comparing Q^2 vs W 

4. betavsp*
creates H2F histograms comparing beta vs momentum (p) for each particle type

5. c12betavsp* (CLAS12)
creates 3 canvases:

      a. H2F beta vs p historgrams similar to betavsp for clas6
  
      b. H1F historgrams for the measured mass squared values for each particle
  
      c. H2F histograms for dBeta (BetaBank - BetaCalculated) vs p for each particle

6. ccinfo3*
plots H1F histograms of total and cut photoelectron counts and an additional graph which overlays them onto each other

7. ccinfo5*
adds onto ccinfo3 with plots of EC in vs EC out and P vs (ECtotal / P) along with cut versions

8. ccinfo6*
adds onto ccinfo5 with plot of P vs ECtotal along with a cut version 

9. QWtp*
plots theta, phi, q^2, and w

10. tp2D.java
plots an H2F theta vs phi (electron)

11. c12tp2D.java
plots an H2F theta vs phi for clas12 (electron)

12. c12tp2DProton
plots an H2F theta vs phi for clas12 (proton)
