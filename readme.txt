->Wenn man nicht bei Dream Schufa angemeldet ist(gleicher Name und Geburtstag) bekommt man laut Dennis Fitzeck normal KEINRISIKO zur�ck -> da keine Schufa Eintr�ge
->man kann Schufa durch Dummy ersetzen (siehe yml)
->muss nichts l�schen, da ich alle daten behalten m�chte (rechtlich gesehen) (kein Orphan removal)
->Betr�ge bitte in Cent eingeben
-> Monatliche Kreditr�ckzahlung auf 30 sek�ndlich erh�ht zu Demo Zwecken -> scheduled chron job
->yml Datein neben der .jar bietet konfigurationsm�glichkeiten:

testWithouthSchufa: false				->wenn true-> Schufa Service wird durch Dummy Klassen ersetzt -> Zufallswerte
schufaId: c3da5d02-4829-45a8-b631-0b68221e57e8		->fest generiert von Dream Schufa (Dennis Fitzeck) zur authentifizierung
bankName: m26						->bank name
standardRepaymentTime: 12				->kredite auf 12 monate zur�ck
standardInterestRate: 50				->50 -> = 5% Kreditzinsen (/1000)
historyNumber: 30					->wie viele Transactions angezeigt werden





�nderungen:
Adresse kunde -> 1 zu 1
Adresse ohne id (embaddable)
user bekommt username
kein timestamp
account r�ckzahlungsrate
evtl kredit entity
added repayment rate
auch in dtos tid -> ID
key bei customer



