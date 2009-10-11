<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="/jsps/include.jspf" %>
<link rel="stylesheet" type="text/css" media="screen" href="css/reset.css" />
<link rel="stylesheet" type="text/css" media="screen" href="css/typography.css" />
<link rel="stylesheet" type="text/css" media="screen" href="css/layout.css">
<link rel="stylesheet" type="text/css" media="screen" href="css/style.css">
<script type="text/javascript" src="script/extras.js"></script>
<script type="text/javascript">
  $(document).ready(function() {
    onQuestionnaireLoad("bodyContent", "generated");
  });
</script>
<title>Tohu Generator Example</title>
</head>
<body>
<div id="container">
  <div id="header" class="centeredImage">
   <img src="images/example_banner.png" width="930" title="to be defined"  />
  </div>
  <div id="wrapper">
        <div id="bodyContent" class="innerPadding"></div>
		<form>
			<input type="button" value="Reset" onclick="resetDrools()" />
		</form>
  </div>
</div>
</body>
</html>
