# clas6analysis
Java files to be run in jshell to analyze and plot histograms of clas6 simulation data (Summer 2022)

***all files are meant for clas6 analysis unless they have "c12", "CLAS12" etc. in their file name***

#particlecount*
counts numbers of each type of particle found in each event and prints to the screen 

#particlegraph*
creates H1F histograms for each type of particle counting the number of events with 0,1,2,(and so on) counts of that particle 

#QW2D* 
creates H2F histogram comparing Q^2 vs W 

#betavsp*
creates H2F histograms comparing beta vs momentum (p) for each particle type

#c12betavsp* (CLAS12)
creates 3 canvases:
1. H2F beta vs p historgrams similar to betavsp for clas6
2. H1F historgrams for the measured mass squared values for each particle
3. H2F histograms for dBeta (BetaBank - BetaCalculated) vs p for each particle

#ccinfo3*
plots H1f histograms of total and cut photoelectron counts and an additional graph which overlays them onto each other


