<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<style type="text/css">
		
.div2 {
  width: 600px;
  height: 550px;
  padding: 50px;
  border: 1px black;
  background-color: lightgrey;
  margin-left: 400px;
  margin-top: 100px;
  text-align: left;
  opacity: .9;

}

.titlestyle{
    background-color: red ;
	font-size: 3em;
	font-weight: bold;
	font-family: "Work Sans",sans-serif;
	
}

p{
font-size: 1em;
 font-weight: bold;
 color:black;
font-family: Verdana, Arial, Helvetica, sans-serif;
border:3px; 
border-style:solid; 
border-color:black; 
padding: 1em;
}

	</style>
<script type="text/javascript">
	
var ans1="";
var ans2="";
var valueArray=[];	
function submitAnswer(valueOfSubmit){

   
   var radios0= document.getElementsByName("choice0");
   var radios1=document.getElementsByName("choice1");
   var radios=[];
   var valueOfChoice=[];
   var j=0;
   
   alert(radios0.length);
   alert(radios1.length);
   
   var radios=[];
   for(var k=0;k<radios0.length;k++){
   	   if (radios0[k].checked) {
          valueOfChoice[j]=radios0[k].value;
          j=j+1;
          break;
      }

   }    
       if(j == 0){
       	 valueOfChoice[j]="";
          j=j+1;
       }

   

//   alert(valueOfChoice[0]);   
   for(var m=0;m<radios1.length;m++){
       if (radios1[m].checked) {
          valueOfChoice[j]=radios1[m].value;
          j=j+1;
          break;
      }
   }   
   if(j==1){
       	 valueOfChoice[j]="";
          j=j+1;
       }

       
   alert(valueOfChoice.length);  
  // var radios=radios0.concat(radios1);
  for (var i = 0, length = valueOfChoice.length; i < length; i++) {
      
   //    alert(valueOfChoice[i]);
  }


  var correctAnswers = ["selenium2.0","VBScript"];



 for( var i=0; i<valueOfChoice.length;i++){
    
    //alert(valueOfChoice[i]);
   if(valueOfChoice[i] == ""){
   	//alert("choose atleast one option for " + i);
    valueArray.push(valueOfChoice);
   }
   

    else if(valueOfChoice[i] == correctAnswers[i]){
   	//alert("correct answer for" + i);
    valueArray.push(valueOfChoice[i]);
   }
   
    else{
    //alert("wrong answer for " + i);
    valueArray.push(valueOfChoice[i]);
  }
}
    
   


   storeInArray(valueArray);


  // document.getElementById("answer").innerHTML=valueArray[0];
}



function showAnswer(ans){


  
  //alert(ans);


}



function storeInArray(valueArray){

  for(var i=0;i<valueArray.length; i++)
  //	alert(valueArray[i]);



}
</script>


</head>

<body>

<div align="center" class="titlestyle">
<p> YATRA CODERPAD </div>

<div align="center" class="div2">	

      
    
  

    <form action="/AutomationDashboard/mcq" method="GET">
    <p>1.  Which one of below is correct </p>
	<input type="radio" name="choice0" value="selenium2.0"> selenium 2.0 is called webdriver </input><br><br>
	<input type="radio" name="choice0" value="selenium2.1"> selenium 1.0 is called webdriver </input><br><br>
	<input type="radio" name="choice0" value="selenium2.2"> selenium JS is called webdriver </input><br><br>
	<input type="radio" name="choice0" value="selenium2.3"> selenium 1.1 is called webdriver </input>
     <br><br>

	

      <p>2. WHich of these langauges do not have selenium bindings </p>
	<input type="radio" name="choice1" value="VBScript"> VBScript </input><br><br>
	<input type="radio" name="choice1" value="C#"> C# </input><br><br>
	<input type="radio" name="choice1" value="Java"> Java </input><br><br>
	<input type="radio" name="choice1" value="Python"> Python </input>
     <br><br>

	<button type="submit"  value="submitans2" onclick="submitAnswer(this.value)"> Submit Answer</button> 



	
</form>
</div>

<div id="answer" align="left" >
	

</div>


</body>

</html>