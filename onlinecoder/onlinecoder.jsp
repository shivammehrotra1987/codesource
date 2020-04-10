<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">



<html>
<head>

<style type="text/css">
/* textarea {
	background-color: lightgrey;
	font-size: 1em;
	font-weight: bold;
	font-family: Verdana, Arial, Helvetica, sans-serif;
	border: 1px solid black;
} */


body{
background-color:Lavender ;

}




.compilebutton{
  background-color: #4CAF50; /* Green */
  border: none;
  color: white;
  padding: 15px 32px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
}


.submitforevaluationbutton{
  background-color: blue; /* Green */
  border: none;
  color: white;
  padding: 15px 32px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
}


span{
    font-size: 20px;
    font-family: sans-serif;
    font-weight: 700;

}

select {
   display: block;
    font-size: 16px;
    font-family: sans-serif;
    font-weight: 700;
    color: #444;
    line-height: 1.3;
    padding: .6em 1.4em .5em .8em;
     
    box-sizing: border-box;
    margin: 0;
    border: 1px solid #aaa;
    box-shadow: 0 1px 0 1px rgba(0,0,0,.04);
    border-radius: .5em;
    -moz-appearance: none;
    -webkit-appearance: none;
    appearance: none;
    background-color: #fff;
    background-image: url('data:image/svg+xml;charset=US-ASCII,%3Csvg%20xmlns%3D%22http%3A%2F%2Fwww.w3.org%2F2000%2Fsvg%22%20width%3D%22292.4%22%20height%3D%22292.4%22%3E%3Cpath%20fill%3D%22%23007CB2%22%20d%3D%22M287%2069.4a17.6%2017.6%200%200%200-13-5.4H18.4c-5%200-9.3%201.8-12.9%205.4A17.6%2017.6%200%200%200%200%2082.2c0%205%201.8%209.3%205.4%2012.9l128%20127.9c3.6%203.6%207.8%205.4%2012.8%205.4s9.2-1.8%2012.8-5.4L287%2095c3.5-3.5%205.4-7.8%205.4-12.8%200-5-1.9-9.2-5.5-12.8z%22%2F%3E%3C%2Fsvg%3E'),
      linear-gradient(to bottom, #ffffff 0%,#e5e5e5 100%);
    background-repeat: no-repeat, repeat;
    background-position: right .7em top 50%, 0 0;
    background-size: .65em auto, 100%;
}

.loader {
  position: fixed;
  left: 800px;
  top: 200px;
  border: 16px solid #f3f3f3;
  border-radius: 80%;
  border-top: 16px solid red;
  width: 20px;
  height: 20px;
  -webkit-animation: spin 2s linear infinite; /* Safari */
  animation: spin 2s linear infinite;
  
}

/* Safari */
@-webkit-keyframes spin {
  0% { -webkit-transform: rotate(0deg); }
  100% { -webkit-transform: rotate(360deg); }
}

@keyframes spin {
  0% { transform: rotate(0deg); }
  100% { transform: rotate(360deg); }
}



.divstyle{
margin-top: 50px;
}

.titlestyle{
    background-color: red ;
	font-size: 3em;
	font-weight: bold;
	font-family: "Work Sans",sans-serif;
	



}

textarea
{
 background: url(http://i.imgur.com/2cOaJ.png);
 background-attachment: local;
 background-color: black;
 background-repeat: no-repeat;
 padding-left: 35px;
 padding-top: 10px;
 border-color:#ccc;
 line-height:16px;
 font-size: 1em;
 font-weight: bold;
 color:white;
 opacity : .6;
 font-family: Verdana, Arial, Helvetica, sans-serif;
}


.pstyle{

font-size: 1em;
 font-weight: bold;
  font-family: Verdana, Arial, Helvetica, sans-serif;
 
}

.textareacompileandrun
{
 
 background-color: black;
 background-repeat: no-repeat;
 padding-left: 35px;
 padding-top: 10px;
 border-color:#ccc;
 line-height:16px;
 font-size: 1em;
 font-weight: bold;
 color:white;
 opacity: .7;
 font-family: Verdana, Arial, Helvetica, sans-serif;
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

.linedwrap {
	border: 1px solid #c0c0c0;
	padding: 3px;
}

.linedtextarea {
	padding: 0px;
	margin: 0px;
}

.linedtextarea textarea, .linedwrap .codelines .lineno {
	font-size: 10pt;
	font-family: monospace;
	line-height: normal !important;
}

.linedtextarea textarea {
	padding-right:0.3em;
	padding-top:0.3em;
	border: 0;
}

.linedwrap .lines {
	margin-top: 0px;
	width: 50px;
	float: left;
	overflow: hidden;
	border-right: 1px solid #c0c0c0;
	margin-right: 10px;
}

.linedwrap .codelines {
	padding-top: 5px;
}

.linedwrap .codelines .lineno {
	color:#AAAAAA;
	padding-right: 0.5em;
	padding-top: 0.0em;
	text-align: right;
	white-space: nowrap;
}

.linedwrap .codelines .lineselect {
	color: red;
}


</style>




<script src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script type="text/javascript">

/**
 * jQuery Lined Textarea Plugin 
 *   http://alan.blog-city.com/jquerylinedtextarea.htm
 *
 * Copyright (c) 2010 Alan Williamson
 * 
 * Version: 
 *    $Id: jquery-linedtextarea.js 464 2010-01-08 10:36:33Z alan $
 *
 * Released under the MIT License:
 *    http://www.opensource.org/licenses/mit-license.php
 * 
 * Usage:
 *   Displays a line number count column to the left of the textarea
 *   
 *   Class up your textarea with a given class, or target it directly
 *   with JQuery Selectors
 *   
 *   $(".lined").linedtextarea({
 *   	selectedLine: 10,
 *    selectedClass: 'lineselect'
 *   });
 *
 * History:
 *   - 2010.01.08: Fixed a Google Chrome layout problem
 *   - 2010.01.07: Refactored code for speed/readability; Fixed horizontal sizing
 *   - 2010.01.06: Initial Release
 *
 */
(function($) {

	$.fn.linedtextarea = function(options) {
		
		// Get the Options
		var opts = $.extend({}, $.fn.linedtextarea.defaults, options);
		
		
		/*
		 * Helper function to make sure the line numbers are always
		 * kept up to the current system
		 */
		var fillOutLines = function(codeLines, h, lineNo){
			while ( (codeLines.height() - h ) <= 0 ){
				if ( lineNo == opts.selectedLine )
					codeLines.append("<div class='lineno lineselect'>" + lineNo + "</div>");
				else
					codeLines.append("<div class='lineno'>" + lineNo + "</div>");
				
				lineNo++;
			}
			return lineNo;
		};
		
		
		/*
		 * Iterate through each of the elements are to be applied to
		 */
		return this.each(function() {
			var lineNo = 1;
			var textarea = $(this);
			
			/* Turn off the wrapping of as we don't want to screw up the line numbers */
			textarea.attr("wrap", "off");
			textarea.css({resize:'none'});
			var originalTextAreaWidth	= textarea.outerWidth();

			/* Wrap the text area in the elements we need */
			textarea.wrap("<div class='linedtextarea'></div>");
			var linedTextAreaDiv	= textarea.parent().wrap("<div class='linedwrap' style='width:" + originalTextAreaWidth + "px'></div>");
			var linedWrapDiv 			= linedTextAreaDiv.parent();
			
			linedWrapDiv.prepend("<div class='lines' style='width:50px'></div>");
			
			var linesDiv	= linedWrapDiv.find(".lines");
			linesDiv.height( textarea.height() + 6 );
			
			
			/* Draw the number bar; filling it out where necessary */
			linesDiv.append( "<div class='codelines'></div>" );
			var codeLinesDiv	= linesDiv.find(".codelines");
			lineNo = fillOutLines( codeLinesDiv, linesDiv.height(), 1 );

			/* Move the textarea to the selected line */ 
			if ( opts.selectedLine != -1 && !isNaN(opts.selectedLine) ){
				var fontSize = parseInt( textarea.height() / (lineNo-2) );
				var position = parseInt( fontSize * opts.selectedLine ) - (textarea.height()/2);
				textarea[0].scrollTop = position;
			}

			
			/* Set the width */
			var sidebarWidth					= linesDiv.outerWidth();
			var paddingHorizontal 		= parseInt( linedWrapDiv.css("border-left-width") ) + parseInt( linedWrapDiv.css("border-right-width") ) + parseInt( linedWrapDiv.css("padding-left") ) + parseInt( linedWrapDiv.css("padding-right") );
			var linedWrapDivNewWidth 	= originalTextAreaWidth - paddingHorizontal;
			var textareaNewWidth			= originalTextAreaWidth - sidebarWidth - paddingHorizontal - 20;

			textarea.width( textareaNewWidth );
			linedWrapDiv.width( linedWrapDivNewWidth );
			

			
			/* React to the scroll event */
			textarea.scroll( function(tn){
				var domTextArea		= $(this)[0];
				var scrollTop 		= domTextArea.scrollTop;
				var clientHeight 	= domTextArea.clientHeight;
				codeLinesDiv.css( {'margin-top': (-1*scrollTop) + "px"} );
				lineNo = fillOutLines( codeLinesDiv, scrollTop + clientHeight, lineNo );
			});


			/* Should the textarea get resized outside of our control */
			textarea.resize( function(tn){
				var domTextArea	= $(this)[0];
				linesDiv.height( domTextArea.clientHeight + 6 );
			});

		});
	};

  // default options
  $.fn.linedtextarea.defaults = {
  	selectedLine: -1,
  	selectedClass: 'lineselect'
  };
})(jQuery);

</script>
<!-- <link href="jsp/jquery-linedtextarea.css" type="text/css" rel="stylesheet" /> -->



<script type="text/javascript">
	$(document).ready(function() {
		$('#language').on('change', function() {

			if (this.value == 'java')

			{
				$('#javatextarea').show();
				
				
				
		
				

			} else {
				$('#javatextarea').hide();

			}

			if (this.value == 'c')

			{
				$('#ctextarea').show();
				$(function() {
					$(".lined").linedtextarea({
						selectedLine : 1
					});
				});
				

			} else {
				$('#ctextarea').hide();

			}

			if (this.value == 'python')

			{
				$(function() {
					$(".lined").linedtextarea({
						selectedLine : 1
					});
				});
				$('#pythontextarea').show();
				
				

			} else {
				$('#pythontextarea').hide();

			}

		});
	});
</script>


</head>

<body>

<div align="center" class="titlestyle">
<p> YATRA CODERPAD </div>


<div align="left" style="padding-left:255px;padding-right:500px;">
 
<p>Problem Statement: <br><br><br> ${problem}</p>
</div>
<br><br><br>
<form action="/AutomationDashboard/onlinecodermethod" method="POST">

  
<div align="right" class="divstyle" style="padding-left:255px;padding-top:20px ;">
 <div align="left" style="padding-left:10px;padding-top:10px ;">  <span>CODE EDITOR :</span> </b></div> 
   <div align="right">
	<SELECT id="language" name="language" >
		<option  value="java">Java 8(openjdk 1.8.0_121)</option>
		<option value="c">C++</option>
		<option value="python">python</option>
	</SELECT>
	</div>
</div>


<div align="right">

	<br>
	
	<textarea id="javatextarea" name="javatextarea1"   rows="50" cols="100"
		style="display: block;">
        ${javacode} 
</textarea>
	
	<br>
	<textarea id="ctextarea"  name="ctextarea1" rows="50" cols="100" style="display: none;">
	/*
// Sample code to perform I/O:

cin >> name;                            // Reading input from STDIN
cout << "Hi, " << name << ".\n";        // Writing output to STDOUT

// Warning: Printing unwanted or ill-formatted data to output will cause the test cases to fail
*/

// Write your code here

	
	</textarea>
	
	<br>
	<textarea id="pythontextarea"  name="pythontextarea1" rows="50" cols="100"
		style="display: none;">
		'''
# Sample code to perform I/O:

name = input()                  # Reading input from STDIN
print('Hi, %s.' % name)         # Writing output to STDOUT

# Warning: Printing unwanted or ill-formatted data to output will cause the test cases to fail
'''

# Write your code here
		
		</textarea>
		
</div>		
		
<!-- <script>
  $('#language').change(function(){ 
    if($(this).val() == 'java'){
    	$(function() {
			$(".lined").linedtextarea({
				selectedLine : 1
			});
		});
    	
    	
    }
    if($(this).val() == 'c'){
    	$(function() {
			$(".lined1").linedtextarea({
				selectedLine : 1
			});
		});
    }
    
    if($(this).val() == 'python'){
    	$(function() {
			$(".lined2").linedtextarea({
				selectedLine : 1
			});
		});
    }

});		
		
</script> -->
	
<script>

function showloader(){

	$("#loadericon").show();
}
</script>	
	

<div id="loadericon" class="loader" align="center" style="display:none;"></div>	
	

<br>

<div align="center">

            <button type="submit"  value="SubmitCode" name="coderun" class="compilebutton " onclick="showloader()">Compile and Test </button>
			<button type="submit"  value="Compile "
								 name="codecompile" class="submitforevaluationbutton">Submit For Evaluation</button>
			
		
									
		</div>

</form>




<c:if test="${compileresult ne ''}">



<div align="right">
<br>
<br>
<br>
 <div  align="left" style="padding-left:255px;padding-top:20px;padding-bottom:20px;"> <b>CODE OUTPUT :</b></div>
<textarea  class="textareacompileandrun" id="compliletextarea" name="compiletextarea"   rows="20" cols="100" style="display:block;">

${compileresult}

</textarea>
</div>

</c:if>

</body>
</html>