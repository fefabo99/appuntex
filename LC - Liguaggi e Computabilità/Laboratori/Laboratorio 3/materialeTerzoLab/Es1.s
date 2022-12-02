BEGIN
  read R1;
  read R2;
  read R3;
  
  if R1 > R2 then
    if R1 > R3 then
    write R1;
    
    else
      write R3;
    fi;
  else
    if R2 > R3 then
      write R2;
    else
      write R3;
    fi;
  fi;
END