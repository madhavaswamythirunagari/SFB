function svalid()
 {
  var studid,studpwd;
  studid =form1.studid.value;
  studpwd=form1.password.value;
   if( studid!="" && studpwd!="" && studid!=null && studpwd!=null )
    {
	 return true; 
	}
   else 
   {
   alert("Field's cannot be empty Please enter correct values ");
   return false;
   
   }
 }