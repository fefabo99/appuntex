# Appunti presi a una lezione

## Covarianza

Forumla per la covarianza

## Proprietà della covarianza

Per ogni v.a. X: VAR[X] = Cov[X, X]
*Simmetrica* 
**Bilinearità** -> Per ciascun argomento la funziona è lineare (se tengo l'altro fissato)

### Corollario

La varianza di Var[aX+b]=a^2 Var[X], basta sviluppare la Varianza scrivendola come covarianza e tendendo fissato un argomento. La costante b si elimina perchè nella covarianza le costanti si annullano.

### Formula della somma

Var[X+Y] = VAR[X]+VAR[Y] + 2COV[X,Y]

### Osservazione

- Se X e Y sono indipendenti la covarianza è zero -> in questo caso Var[X+Y] = Var[X] + Var[Y]

Supponiamo che Var[X] > 0 e Var[Y] > 0

Si definisce coefficiente di correalzione lineare (RIPORTA FORUMLA)
E assume valori tra -1 e +1

Riporta caso specifico.
Questo spiega perchè la Covarianza è una misura della dipendeza lineare che c'è tra X e Y.
Infatti se rho = 1 o -1 la funzione è una trasformazione lineare in funzione di x.

Affermare che la covarianza sia grande o piccola non ha molto senso, va fatto considerando la standard deviation

### Osservazione 2

Quando Cov[X,Y] = 0 si dice che X, Y sono **Scorrelate**
Due variabili aleatorie indipendenti X e Y sono anche scorrelate
**NON vale viceversa**.
Se ottengo che due variabili X e Y sono scorrelate posso solo dire che non c'è una diretta correalazione lineare, ma non posso affermare che sono anche indipendenti.
**Dimostrazione** tramite lancio dei dati. Viene richiesto che il secondo lancio abbia 2 risultati diversi contemporaneamente, questo è impossibile, Prob = 0.

*Fine panoramica variabili aleatorie*
*Fine panoramica probabilità*

## Statistica

Modello probabilistico fondamentale per la Statistica:
La domanda fondamentale è: A partire dai dati x1, x2, x3. Cosa possiamo dedurre sulla distrubuzione comune delle v.a Xi?

## Teorema (Legge dei grandi numeri)

**Dimostrazione**

---

## Tip per l'esame

Stampare formulario e studiarlo, conoscere le formule (non a memoria) e sapere cosa significa. Cosa andare a cercare, conoscere esempi e contro esempi, sapere. Lo scopo del Quiz non è chiedere la dimostrazione.

### Anagramma

Significa disporre le lettere in modo diverso, disposizione semplice, permutazioni delle 4 lettere C,I,A,O. Ci sono dunque N! anagrammi.

### Eventi indipendenti

Eventi A e B indipendenti con P(A) = P(B) = 1/2

Quanto vale P(A unito B)?

Se fossero stati disgiunti l'interesezione era nulla. 
**Essere indipendenti != da essere disgiunti!**
