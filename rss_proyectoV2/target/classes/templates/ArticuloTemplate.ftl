<html>
	<head>
		<title>articulo</title>
		<meta charset="utf-8">
		<style type="text/css">
   			.centerText{
       			text-align: center;
    		}
 		</style>
	</head>
	<body>
		<h1>Mi articulo que voy a leer</h1>
		
		
		
		<#list root as portfolioStock> 
			<tr>
				<td>${portfolioStock.name}</td>
				<td class='centerText'>${portfolioStock.last}</td>
				<td class='centerText'>${portfolioStock.quantity}</td>
				<td class='centerText'>${portfolioStock.total}</td>
			</tr>
		</#list>
			
		
		
	</body>
</html>
