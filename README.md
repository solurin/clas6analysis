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
plots H1f histograms of total and cut photoelectron counts and an additional graph which overlays them onto each other

7. ccinfo5*
adds plots of EC in vs EC out and P vs (ECtotal * P) including cut versions
