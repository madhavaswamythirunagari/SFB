
<script language="JavaScript"> 
function validate(form1)
{
 var c=0,cc;
 
	cc=sfeedback.ccount.value;

	//alert("total column count is "+cc);
    for (i = cc; i < form1.length-1; i++) 
	{
		c=c+1;
	   
   if( (form1.elements[i].value == "") || isNaN(parseInt(form1.elements[i].value)) || parseInt(form1.elements[i].value)<1 || parseInt(form1.elements[i].value)>5 )
		   {

           alert("You must provide a values between 1 to 5 only For Grade : " + c+" values is   "+form1.elements[i].value)
			
           return false
           }
		     
				
		}
	
	
		    
			//sbm=sfeedback.sb.value
			//sfeedback.sb.disabled=true
			//alert("Button values is"+sbm);
			return true
	
}


</script>
