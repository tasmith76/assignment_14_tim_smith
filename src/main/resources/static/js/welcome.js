var user = sessionStorage.getItem("user")

if (user == null) {

	let username = prompt('Enter your name')  
		fetch("/welcome/createuser", {
		method : "POST",
		headers: {
			"Content-Type" : "application/json" 
		},
		body: username
	})
	.then((response) => response.json())
		.then(user =>
			sessionStorage.setItem("user",JSON.stringify(user)))
			
	
} else {
  user = JSON.parse(sessionStorage.getItem) 
  console.log(user)
}

			
