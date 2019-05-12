<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Summernote</title>
  <link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
  <script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script> 
  <script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script> 
  <link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.11/summernote.css" rel="stylesheet">
  <script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.11/summernote.js"></script>
  <style type="text/css">
  	.note-editor{
             margin : 5%;
    }
  </style>

</head>
<body>

  <form method="post">
     <textarea id="summernote" name="editordata"></textarea>
     <input type="submit" name="smt_save" id="smt_save" class="form-submit" value="Register"/>
  </form>
  
  <script>
    $(document).ready(function() {
        $('#summernote').summernote({
        	height : 500,
        	minHeight: null,
        	maxHeight: null,
        	focuts: true
        }
        );
        
        $('#smt_save').click(function(){
        	alert($('#summernote').innerHTML);
        });
    });
  </script>
</body>
</html>