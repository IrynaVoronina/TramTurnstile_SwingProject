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
