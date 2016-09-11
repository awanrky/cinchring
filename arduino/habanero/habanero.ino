#include <Console.h>

const int ledPin = 13; // the pin that the LED is attached to
int incomingByte;      // a variable to read incoming serial data into

#include "DHT.h"

#define DHTPIN 2     // what digital pin we're connected to
#define DHTTYPE DHT22   // DHT 22  (AM2302), AM2321

DHT dht(DHTPIN, DHTTYPE);
void setup() {
  // initialize serial communication:
  Bridge.begin();
  Console.begin(); 

  while (!Console){
    ; // wait for Console port to connect.
  }
  Console.println("Connected to the Console!!!!");
  // initialize the LED pin as an output:
  pinMode(ledPin, OUTPUT);

  
  dht.begin();
}

void loop() {
  // see if there's incoming serial data:
//  if (Console.available() > 0) {
//    // read the oldest byte in the serial buffer:
//    incomingByte = Console.read();
//    // if it's a capital H (ASCII 72), turn on the LED:
//    if (incomingByte == 'H') {
//      digitalWrite(ledPin, HIGH);
//    } 
//    // if it's an L (ASCII 76) turn off the LED:
//    if (incomingByte == 'L') {
//      digitalWrite(ledPin, LOW);
//    }
//  }

  // Reading temperature or humidity takes about 250 milliseconds!
  // Sensor readings may also be up to 2 seconds 'old' (its a very slow sensor)
  float h = dht.readHumidity();
  // Read temperature as Celsius (the default)
  float t = dht.readTemperature();
  // Read temperature as Fahrenheit (isFahrenheit = true)
  float f = dht.readTemperature(true);

  // Check if any reads failed and exit early (to try again).
  if (isnan(h) || isnan(t) || isnan(f)) {
    Serial.println("Failed to read from DHT sensor!");
    return;
  }

  // Compute heat index in Fahrenheit (the default)
  float hif = dht.computeHeatIndex(f, h);
  // Compute heat index in Celsius (isFahreheit = false)
  float hic = dht.computeHeatIndex(t, h, false);

  Console.println("Humidity: ");
  Console.println(h);
  Console.println(" %\t");
  Console.println("Temperature: ");
  Console.println(t);
  Console.println(" *C ");
  Console.println(f);
  Console.println(" *F\t");
  Console.println("Heat index: ");
  Console.println(hic);
  Console.println(" *C ");
  Console.println(hif);
  Console.println(" *F");

  delay(10000);
}
