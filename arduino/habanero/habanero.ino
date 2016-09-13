
#include <Process.h>

const int ledPin = 13; // the pin that the LED is attached to

#include "DHT.h"

#define ARDUINO_NAME "habanero"
#define POST_READING_URL "http://192.168.1.8:8080/api/readings"

#define DELAY 60000

#define DHTPIN 2     // what digital pin we're connected to
#define DHTTYPE DHT22   // DHT 22  (AM2302), AM2321

DHT dht(DHTPIN, DHTTYPE);


void setup() {

  Bridge.begin();

  // initialize the LED pin as an output:
  pinMode(ledPin, OUTPUT);
  
  dht.begin();
}

void loop() {

    readDht();

    delay(DELAY);

}

void ledOn() {
    digitalWrite(ledPin, HIGH);
}

void ledOff() {
    digitalWrite(ledPin, LOW);
}

void readDht() {
    ledOn();

    // Reading temperature or humidity takes about 250 milliseconds!
    // Sensor readings may also be up to 2 seconds 'old' (its a very slow sensor)
    float h = dht.readHumidity();
    // Read temperature as Celsius (the default)
    float t = dht.readTemperature();
    // Read temperature as Fahrenheit (isFahrenheit = true)
    //  float f = dht.readTemperature(true);

    // Check if any reads failed and exit early (to try again).
    if (isnan(h) || isnan(t) /* || isnan(f) */) {
        Serial.println("Failed to read from DHT sensor!");
        return;
    }

    postReading(h, "percent-humidity", "DHT22");

    postReading(t, "degrees-celsius", "DHT22");

  // Compute heat index in Fahrenheit (the default)
//  float hif = dht.computeHeatIndex(f, h);
  // Compute heat index in Celsius (isFahreheit = false)
//  float hic = dht.computeHeatIndex(t, h, false);

    ledOff();
}

void postReading(float value, String uom, String component) {
    // curl -X POST -H "Content-Type: application/json" -H "Cache-Control: no-cache" -H "Postman-Token: ba8099b2-fa56-74a7-0487-299f9f222c29"
    // -d '{"value":"59", "device": "mark", "component": "door", "uom": {"name":"foot", "description":"Foot"}}' "http://gnomefly.local:8080/api/readings"

    Process p;

    String json = "{\"value\":\"" + String(value) + "\",\"device\":\"" + ARDUINO_NAME + "\",\"component\":\"" + component + "\",\"uom\":\"" + uom + "\"}";

    Serial.print("Post Reading: ");
    Serial.println(json);

    p.begin("curl");

    p.addParameter("-X");
    p.addParameter("POST");
    p.addParameter("-H");
    p.addParameter("Content-Type: application/json");
    p.addParameter("-H");
    p.addParameter("Cache-Controle: no-cache");
    p.addParameter("-d");
    p.addParameter(json);
    p.addParameter(POST_READING_URL);

    p.run();

    // A process output can be read with the stream methods
    while (p.available()>0) {
        char c = p.read();
        Serial.print(c);
    }
    // Ensure the last bit of data is sent.
    Serial.flush();
}
