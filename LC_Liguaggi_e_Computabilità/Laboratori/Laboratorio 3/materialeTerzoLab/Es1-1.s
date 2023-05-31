// Dato n intero stampa i primi n numeri pari
BEGIN
  read R1;
  R2 := R1/2;
  R3 := R2*2;
  if R1 = R3 then
    while R1 > 0 do
      write R1;
      R1 := R1 - 2;
    done;
  else
    R1 := R1 - 1;
    while R1 > 0 do
      write R1;
      R1 := R1 - 2;
    done;
  fi;
END