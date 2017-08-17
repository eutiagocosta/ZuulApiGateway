# ZuulApiGateway

#Payload for example

You need to create an endpoint that will reply to http://localhost:8010/card-store because cros-domain problem

1- Pull https://github.com/eutiagocosta/CardDemo
2- Start application in port 8010
3- Start Zuul application in port 8080
4 - Open Postman </br>
5 - Insert POST -> http://localhost:8080/api/wallet/card-manager/ </br>
6 - Insert Body </br>
	{
	"param":"FipWvHnuO3tWco8t57vAZC416t0Sl4LDi7HtYLf/hsRPNQ5VZt8N6E3ftozbmFA7Po93QgoXPwSL\nElEPYST/H601J7DNTIWvmKNUgWFKoxIgG08Spxaioi/50Rh3SXOVL43BbllGdS0JR1g9T7WYdMjp\nH6Fzn5CT8TBCKO5K0GDXW2qQ9n0wkAXctTie//QzUxEmqL+/Jn9OgtjOGhXTZM3IusKUXXsuMhDw\nj7pz4BBtP0Sff6ciXqg2lOqxzTnIDcyKMRL/5OkS9m4ghGHxkQ\u003d\u003d\n","param1":"ozOOCyUfp00ZnBYGDiYcJulxtggYVrYHyVtTX4zm8eU0LMFGARwqu6hV6gm5rqk8SQHOzuAyQxeW\nd59Ig7klD7e8dTqOOF4vf6bgsSDzcNO8wIjV11Zt4WkcZ11lJwsD6Kz5BPSTt7Y+IJI9QoaKdqjF\nwZoz9B9b1mLysm06eVmxK8czYUzW2ebFWCOiMmNLi8aG8Eq/VpFFF1p/qEjNSWjVtlveDtbEZfyX\nlqhg/s1nnfwrxEpaZ73+v/4Nqo+lT/WMI+HA+BR/4We3ey2D/K/fdSVsovSbdMyGOcDwNQlGPrSp\n2vA0XuSMhZojHFlfwZm5gkE5GUkIVjsq8V691g\u003d\u003d\n"
	} </br>

7- Send post

Zuul will create a new request with a new body using HttpServletRequestWrapper class. </br>

{
	"card"{
		"cvv":"123",
		"expMonth":12,
		"expYear":2031,
		"favorite":false,
		"holderName":"AAAAAA",
		"isSelected":false,
		"cardNumber":"1111111111111117"
	},
	"document":"12345678909",
	"email":"beblueteste@beblueteste.com.br" </br>
}

The first request returns 200, but if i try again the same operation returns 400, then 200, then 400, then 200, then 400 and so on.

