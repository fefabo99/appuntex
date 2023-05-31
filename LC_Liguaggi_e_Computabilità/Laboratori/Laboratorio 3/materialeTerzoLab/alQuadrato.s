// calcolare il quadrato come ciclo di somme,
// n*n = n+n+...+n, n volte sommato
// R1: numero n (<10) di cui stampare il quadrato
// R2: contatore per il ciclo di somme
// R3: risultato, da stampare
BEGIN
  read R1; // chiedo n

  if R1 < 10 then 
    R2 := R1; // numero di somme *ancora mancanti*, inizialmente n
    R3 := 0; // vi calcolo il risultato
    while R2 > 0 do
      R3 := R3+R1; 
      R2 := R2-1; 
    done;

    write R1; write R3;

  else
    skip; // in questo caso non calcolo nulla, ma:
    write 999999999; // stampo una segnalazione "di errore"

  fi;
END
