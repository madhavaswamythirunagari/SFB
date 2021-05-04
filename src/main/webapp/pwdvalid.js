function pwdvalid()
 {
  var p1,p2;
  p1=formpwd.pwd1.value;
  p2=formpwd.pwd2.value;
   if( p1!="" && p2!="" && p1!=null && p2!=null && p1==p2)
    {
	 return true; 
	}
   else 
   {
   alert("Field's cannot be empty OR Enter same values in both the Fields");
   return false;
   }
 }