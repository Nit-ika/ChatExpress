/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function regvalidate()
{
    var f1=document.getElementById("regform");
    validateregEid(f1)&
    validateEname(f1)&
    validatephone(f1)&
    validateEmail(f1)&
    validateregPassword(f1);
    }
    function validateregEid(regform){
     var error=document.getElementById("eidregError");

     var eid=regform["eid"].value;
     error.innerHTML="";
     var max_chars = 5;

    if(eid.length > max_chars) {
        error.innerHTML="EID should be less than 5 digits";
    }
      var reg = new RegExp('^[0-9]+$');

   if( eid===null || eid===""){
      error.innerHTML="Input Your EID";
    }

 else if(!eid.match(reg)){
    error.innerHTML="Invalid EID(EID should contain only digits)";
 }
 return "" === error.innerHTML;
 }
 
 function validateEmail(regform){
     var error=document.getElementById("emailError");

     var email=regform["email"].value;
     error.innerHTML="";
   //   var regx =/^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
   if( email===null || email===""){
      error.innerHTML="Input Your Email";
    }

 //else if(!email.match(regx)){
   // error.innerHTML="Invalid Email";
    //email.focus;
 //}
return "" === error.innerHTML;
 }
 function validateEname(regform)
 {
     var error=document.getElementById("enameError");

     var ename=regform["ename"].value;
     error.innerHTML="";
      var regex = /^[a-zA-Z]*$/;

   if( ename===null || ename===""){
      error.innerHTML="Input Your Name";
    }

 else if(!ename.match(regex)){
    error.innerHTML="Invalid Name(Name should contain only characters)";
 }
 return "" === error.innerHTML;
 }
 function validatephone(regform)
 {
     var error=document.getElementById("phoneError");

     var phone=regform["phoneNo"].value;
     error.innerHTML="";
     var reg = new RegExp('^[0-9]+$');

   if( phone===null || phone===""){
      error.innerHTML="Input Your Phone Number";
    }

 else if(!phone.match(reg)){
    error.innerHTML="Invalid Phone Number(Your number should contain only digits)";
 }
 return "" === error.innerHTML;
 }
 function  validateregPassword(regform){
    var error1=document.getElementById("regpasswordError");
    var error2=document.getElementById("confirmPasswordError");
    error1.innerHTML="Give Password";

    var password=regform["password"].value;

    error1.innerHTML="";
    error2.innerHTML="";

    var confirmPassword=regform["confirm_password"].value;

    if( password===null || password===""){
        error1.innerHTML="Give Password";
    }

    else if( confirmPassword===null || confirmPassword===""){
        error2.innerHTML="Confirm Password";
    }

    else if(password.length<5 || password.length>10){
        error1.innerHTML="Password has to be 5 to 10 chars";
    }

    else if(password !== confirmPassword){
        error2.innerHTML="Password Does Not Match";
    }
    return "" === error1.innerHTML;
}