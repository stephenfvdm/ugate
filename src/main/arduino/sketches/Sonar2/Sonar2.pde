/*
  Blink
  Turns on an LED on for one second, then off for one second, repeatedly.
 
  This example code is in the public domain.
 */

void setup() {                
  // initialize the digital pin as an output.
  // Pin 13 has an LED connected on most Arduino boards:
  //pinMode(3, OUTPUT);
  //pinMode(4, INPUT);
  pinMode(2, INPUT);
  pinMode(9, OUTPUT);
  Serial.begin(19200);
  tone(9, 38000);
}

void loop() {
  long pulse = pulseIn(2, HIGH);
  Serial.println(pulse);
  delay(500);
  /*long pulse;
  long pulseTmp;
  unsigned int cnt;
  for (cnt=0; cnt<130; cnt++) {
    digitalWrite(3, HIGH);
    delayMicroseconds(10);
    digitalWrite(3, LOW);
    //delayMicroseconds(50);
    pulseTmp = pulseIn(4, HIGH);
    if (pulseTmp > 0 && pulseTmp < 34923) {
      pulse += pulseTmp / 147.32;
      cnt++;
    }
  }
  pulse = pulse/cnt;
  Serial.print(pulse);
  Serial.print(" inches: ");
  long sonarReadInches = pulse / 147.32;
  Serial.println(sonarReadInches);*/
}
