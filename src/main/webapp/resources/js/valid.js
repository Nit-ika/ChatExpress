function validate(){
    var f=document.getElementById("form");
    validateEid(f)&
    validatePassword(f)&
    verifyKeyvalidate(f);
}
function validateEid(form){
     var error=document.getElementById("leidError");

     var eid=form["leid"].value;
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
function  validatePassword(form){
    var error1=document.getElementById("lpasswordError");
    //var error2=document.getElementById("confirmPasswordError");
    error1.innerHTML="Give Password";

    var password=form["lpassword"].value;

    error1.innerHTML="";
    //error2.innerHTML="";

   // var confirmPassword=form["confirm_password"].value;

    if( password===null || password===""){
        error1.innerHTML="Give Password";
    }

   // else if( confirmPassword==null || confirmPassword==""){
     //   error2.innerHTML="Confirm Password";
    //}

    else if(password.length<5 || password.length>10){
        error1.innerHTML="Password has to be 5 to 10 chars";
    }

   // else if(password != confirmPassword){
     //   error2.innerHTML="Password Does Not Match"
    //}
    return "" === error1.innerHTML;
}
function verifyKeyvalidate(form){
     var error=document.getElementById("lkeyError");

     var key=form["lverifykey"].value;
     error.innerHTML="";

   if( key===null || key===""){
      error.innerHTML="Input Your Verify Key";
    }
    return "" === error.innerHTML;
 }