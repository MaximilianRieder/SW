->Wenn man nicht bei Dream Schufa angemeldet ist(gleicher Name und Geburtstag) bekommt man laut Dennis Fitzeck normal KEINRISIKO zurück -> da keine Schufa Einträge
->man kann Schufa durch Dummy ersetzen (siehe yml)
->muss nichts löschen, da ich alle daten behalten möchte (rechtlich gesehen) (kein Orphan removal)
->Beträge bitte in Cent eingeben
-> Monatliche Kreditrückzahlung auf 30 sekündlich erhöht zu Demo Zwecken -> scheduled chron job
->yml Datein neben der .jar bietet konfigurationsmöglichkeiten:

testWithouthSchufa: false				->wenn true-> Schufa Service wird durch Dummy Klassen ersetzt -> Zufallswerte
schufaId: c3da5d02-4829-45a8-b631-0b68221e57e8		->fest generiert von Dream Schufa (Dennis Fitzeck) zur authentifizierung
bankName: m26						->bank name
standardRepaymentTime: 12				->kredite auf 12 monate zurück
standardInterestRate: 50				->50 -> = 5% Kreditzinsen (/1000)
historyNumber: 30					->wie viele Transactions angezeigt werden





Änderungen:
Adresse kunde -> 1 zu 1
Adresse ohne id (embaddable)
user bekommt username
kein timestamp
account rückzahlungsrate
evtl kredit entity
added repayment rate
auch in dtos tid -> ID
key bei customer



