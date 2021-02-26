function getRequest() {

    var ipRequest = new XMLHttpRequest();
    var locationRequest = new XMLHttpRequest();
    var weatherRequest = new XMLHttpRequest();

    ipRequest.withCredentials = true;
    locationRequest.withCredentials = true;
    weatherRequest.withCredentials = true;

    ipRequest.addEventListener("readystatechange", function() {
        if(this.readyState === 4) {

          var receivedIp = JSON.parse(this.responseText).ip 
          document.getElementById("ipValue").innerHTML=receivedIp;
          locationRequest.open("GET", "http://localhost/api/get/location/" + receivedIp);
          locationRequest.send();
        }
    });

    locationRequest.addEventListener("readystatechange", function() {
        if(this.readyState === 4) {

        var receivedLocation = JSON.parse(this.responseText).city
        document.getElementById("locationValue").innerHTML=receivedLocation;

        weatherRequest.open("GET", "http://localhost/api/get/weather/" + receivedLocation);
        weatherRequest.send();
        }
    });

    weatherRequest.addEventListener("readystatechange", function() {
        if(this.readyState === 4) {
          document.getElementById("weatherValue").innerHTML=JSON.parse(this.responseText).current.temperature + " degrees Celsius, "
                                                            + JSON.parse(this.responseText).current.weather_descriptions[0];
        }
    });

    ipRequest.open("GET", "http://localhost/api/get/ip");
    ipRequest.send();

}

getRequest();