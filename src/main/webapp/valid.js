function admnvalid()
 {
  var admnid,admnpwd;
  admnid=admnlog.adminid.value;
  admnpwd=admnlog.password.value;
   if( admnid!="" && admnpwd!="" && admnid!=null && admnpwd!=null )
    {
	 return true; 
	}
   else 
   {
   alert("Field's cannot be empty Please enter correct values ");
   return false;
   
   }
 }