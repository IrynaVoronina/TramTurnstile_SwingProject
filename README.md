# Tram turnstile

The project involves simulating the operation of an embedded tram
turnstile processor. The turnstile controls passenger entry onto the
platform and records transaction reports in a system file (JSON). Two types
of fare cards (cumulative/transit pass) can be used to access the
platform. The turnstile reads the card data and verifies it. If the data
cannot be read or the card is invalid, access is denied. Otherwise,
depending on the card type, a fare is deducted (either money or a
ride) and access is granted.

The presentation layer for this project was implemented using Swing (Java).

It is important to Edit Configurations:

![image](https://github.com/IrynaVoronina/TramTurnstile_SwingProject/assets/144926045/f0f79c2c-491f-4e1c-8eae-4ebc0becf4a4)

  Build and run: "Modify options (Alt + M)" -> "Add VM options" set as "--add-opens java.base/java.time=ALL-UNNAMED" 
        ![image](https://github.com/IrynaVoronina/TramTurnstile_SwingProject/assets/144926045/4e1651a8-e58d-4a9b-80fa-54b438668fb8)


    

